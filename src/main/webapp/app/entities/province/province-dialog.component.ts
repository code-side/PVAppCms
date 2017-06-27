import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Province } from './province.model';
import { ProvincePopupService } from './province-popup.service';
import { ProvinceService } from './province.service';
import {EmergencyContactRef} from './emergency-contact-ref.model';

@Component({
    selector: 'jhi-province-dialog',
    templateUrl: './province-dialog.component.html'
})
export class ProvinceDialogComponent implements OnInit {

    province: Province;
    cantons: String;
    authorities: any[];
    isSaving: boolean;
    isEditEC: boolean;
    emergencyContact:EmergencyContactRef;
    indexToEdit: number;

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
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        if(this.province.id === undefined)
            this.province.emergencyContacts = []
    }

    clear() {
        this.cantons = "";
        this.activeModal.dismiss('cancel');
    }

    save() {

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
    addEmergencyContact(){
        if(!this.isEditEC)
            this.province.emergencyContacts.push(this.emergencyContact)
        else{
            this.province.emergencyContacts[this.indexToEdit].name = this.emergencyContact.name;
            this.province.emergencyContacts[this.indexToEdit].type = this.emergencyContact.type;
            this.province.emergencyContacts[this.indexToEdit].contact = this.emergencyContact.contact;
            this.province.emergencyContacts[this.indexToEdit].working_hours = this.emergencyContact.working_hours;
        }
        this.emergencyContact = new EmergencyContactRef();
        this.isEditEC = false;

    }
    editEmergencyContact(ec: EmergencyContactRef, i: number){
        this.emergencyContact.name = ec.name;
        this.emergencyContact.type = ec.type;
        this.emergencyContact.contact = ec.contact;
        this.emergencyContact.working_hours = ec.working_hours;
        this.isEditEC = true;
        this.indexToEdit = i;
    }
    removeEmergencyContact(i: number){
        this.province.emergencyContacts.splice(i,1);
        if(this.isEditEC){
            this.emergencyContact = new EmergencyContactRef();
            this.isEditEC = false;
        }

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
