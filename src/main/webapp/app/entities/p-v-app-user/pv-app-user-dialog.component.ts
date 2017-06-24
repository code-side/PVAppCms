import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { PVAppUser } from './pv-app-user.model';
import { PVAppUserPopupService } from './pv-app-user-popup.service';
import { PVAppUserService } from './pv-app-user.service';

@Component({
    selector: 'jhi-pv-app-user-dialog',
    templateUrl: './pv-app-user-dialog.component.html'
})
export class PVAppUserDialogComponent implements OnInit {

    pVAppUser: PVAppUser;
    authorities: any[];
    isSaving: boolean;
    registrationDateDp: any;
    birthdayDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private pVAppUserService: PVAppUserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.pVAppUser.id !== undefined) {
            this.subscribeToSaveResponse(
                this.pVAppUserService.update(this.pVAppUser), false);
        } else {
            this.subscribeToSaveResponse(
                this.pVAppUserService.create(this.pVAppUser), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<PVAppUser>, isCreated: boolean) {
        result.subscribe((res: PVAppUser) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: PVAppUser, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'pvAppCmsApp.pVAppUser.created'
            : 'pvAppCmsApp.pVAppUser.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'pVAppUserListModification', content: 'OK'});
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
    selector: 'jhi-pv-app-user-popup',
    template: ''
})
export class PVAppUserPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private pVAppUserPopupService: PVAppUserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.pVAppUserPopupService
                    .open(PVAppUserDialogComponent, params['id']);
            } else {
                this.modalRef = this.pVAppUserPopupService
                    .open(PVAppUserDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
