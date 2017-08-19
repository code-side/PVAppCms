import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { ResponseWrapper } from '../../shared';

import { TicoStop } from './tico-stop.model';
import { TicoStopPopupService } from './tico-stop-popup.service';
import { TicoStopService } from './tico-stop.service';

import { Province } from '../province/province.model';
import { ProvinceRef } from '../province/province-ref.model';
import { ProvinceService } from '../province/province.service';

import { CoordinatesRef } from '../tourist-destination/coordinates-ref.model';

@Component({
    selector: 'jhi-tico-stop-dialog',
    templateUrl: './tico-stop-dialog.component.html'
})
export class TicoStopDialogComponent implements OnInit {

    ticoStop: TicoStop;
    provinces: Province[];
    provincesName: String[];
    selectedProvince: String;
    cantons: String[];
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private provinceService: ProvinceService,
        private alertService: JhiAlertService,
        private ticoStopService: TicoStopService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.provinceService.query().subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    private onSuccess(data, headers) {
        this.provinces = data;
        this.provincesName = [];
        this.provinces.forEach((x) => this.provincesName.push(x.name));
        if (this.ticoStop.id !== undefined) {
            this.selectedProvince = this.ticoStop.province.name;
            this.cantons = this.provinces.find((x) => x.name === this.selectedProvince).cantons;
        }else if(this.ticoStop.id === undefined){
            this.ticoStop.coordinates = new CoordinatesRef();
        }
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    updateCantons() {
      const province = this.provinces.find((x) => x.name === this.selectedProvince);
      this.ticoStop.province = new ProvinceRef(province.id, province.name);
      this.cantons = province.cantons;
    }

    save() {
        this.isSaving = true;
        if (this.ticoStop.id !== undefined) {
            this.subscribeToSaveResponse(
                this.ticoStopService.update(this.ticoStop), false);
        } else {
            this.subscribeToSaveResponse(
                this.ticoStopService.create(this.ticoStop), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<TicoStop>, isCreated: boolean) {
        result.subscribe((res: TicoStop) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: TicoStop, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'pvAppCmsApp.ticoStop.created'
            : 'pvAppCmsApp.ticoStop.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'ticoStopListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-tico-stop-popup',
    template: ''
})
export class TicoStopPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ticoStopPopupService: TicoStopPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.ticoStopPopupService
                    .open(TicoStopDialogComponent, params['id']);
            } else {
                this.modalRef = this.ticoStopPopupService
                    .open(TicoStopDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
