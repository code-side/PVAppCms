import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Province } from './province.model';
import { ProvincePopupService } from './province-popup.service';
import { ProvinceService } from './province.service';
import {EmergencyContactRef} from './emergency-contacts-ref.model';

@Component({
    selector: 'jhi-province-dialog',
    templateUrl: './province-dialog.component.html'
})
export class ProvinceDialogComponent implements OnInit {

    province: Province;
    cantons: String;
    authorities: any[];
    isSaving: boolean;
    emergencyContact:EmergencyContactRef;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private provinceService: ProvinceService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.emergencyContact = new EmergencyContactRef();
        this.province.emergencyContacts = [];
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    clear() {
        this.cantons = "";
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        this.province.cantons = this.cantons.split(',');

        if (this.province.id !== undefined) {
            this.subscribeToSaveResponse(
                this.provinceService.update(this.province), false);
        } else {
            this.subscribeToSaveResponse(
                this.provinceService.create(this.province), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Province>, isCreated: boolean) {
        result.subscribe((res: Province) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Province, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'pvAppCmsApp.province.created'
            : 'pvAppCmsApp.province.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'provinceListModification', content: 'OK'});
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
    addEmergencyContacts(){
      this.province.emergencyContacts.push(this.emergencyContact);
      this.emergencyContact = new EmergencyContactRef();

    }
}

@Component({
    selector: 'jhi-province-popup',
    template: ''
})
export class ProvincePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private provincePopupService: ProvincePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.provincePopupService
                    .open(ProvinceDialogComponent, params['id']);
            } else {
                this.modalRef = this.provincePopupService
                    .open(ProvinceDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
