import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { ResponseWrapper } from '../../shared';

import { TouristicInterest } from './touristic-interest.model';
import { TouristicInterestPopupService } from './touristic-interest-popup.service';
import { TouristicInterestService } from './touristic-interest.service';

import { Province } from '../province/province.model';
import { ProvinceRef } from '../province/province-ref.model';
import { ProvinceService } from '../province/province.service';

@Component({
    selector: 'jhi-touristic-interest-dialog',
    templateUrl: './touristic-interest-dialog.component.html'
})
export class TouristicInterestDialogComponent implements OnInit {

    touristicInterest: TouristicInterest;
    authorities: any[];
    isSaving: boolean;
    provinces: Province[];
    selectedProvince: Province;
    selectedCanton: string;
    typeOfTouristInterest: any[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private touristicInterestService: TouristicInterestService,
        private eventManager: JhiEventManager,
        private provinceService: ProvinceService
    ) {
    }

    ngOnInit() {
        this.typeOfTouristInterest = this.initArrayOfTypes();
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.provinceService.query().subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    private onSuccess(data, headers) {
        this.provinces = data;
        if (this.touristicInterest.id === undefined) {
            this.touristicInterest.province = new ProvinceRef();
        } else {
          this.findProvince();
        }
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.touristicInterest.id !== undefined) {
            this.subscribeToSaveResponse(
                this.touristicInterestService.update(this.touristicInterest), false);
        } else {
            this.subscribeToSaveResponse(
                this.touristicInterestService.create(this.touristicInterest), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<TouristicInterest>, isCreated: boolean) {
        result.subscribe((res: TouristicInterest) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: TouristicInterest, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'pvAppCmsApp.touristicInterest.created'
            : 'pvAppCmsApp.touristicInterest.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'touristicInterestListModification', content: 'OK'});
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

    saveProvinceRef(action: any) {
        if (action === 'province') {
            this.touristicInterest.province.name = this.selectedProvince.name;
            this.touristicInterest.province.id = this.selectedProvince.id;
        } else {
            this.touristicInterest.province.canton =  this.selectedCanton;
        }
    }

    findProvince() {
        for (const province of this.provinces) {
            if (province.id === this.touristicInterest.province.id) {
                this.selectedProvince = province;
                for (const canton of province.cantons) {
                    if (canton === this.touristicInterest.province.canton) {
                         this.selectedCanton = canton;
                         break;
                     }
                }
                break;
            }
        }
    }

    initArrayOfTypes() {
      return [{type: 'Agencia de viajes'}, {type: 'Rent a car'}, {type: 'Tours'}, {type: 'Hospedaje'}, {type: 'Restaurante'}];
    }
}

@Component({
    selector: 'jhi-touristic-interest-popup',
    template: ''
})
export class TouristicInterestPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private touristicInterestPopupService: TouristicInterestPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.touristicInterestPopupService
                    .open(TouristicInterestDialogComponent, params['id']);
            } else {
                this.modalRef = this.touristicInterestPopupService
                    .open(TouristicInterestDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
