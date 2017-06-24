import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { PVAppUser } from './pv-app-user.model';
import { PVAppUserPopupService } from './pv-app-user-popup.service';
import { PVAppUserService } from './pv-app-user.service';

@Component({
    selector: 'jhi-pv-app-user-delete-dialog',
    templateUrl: './pv-app-user-delete-dialog.component.html'
})
export class PVAppUserDeleteDialogComponent {

    pVAppUser: PVAppUser;

    constructor(
        private pVAppUserService: PVAppUserService,
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.pVAppUserService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'pVAppUserListModification',
                content: 'Deleted an pVAppUser'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success('pvAppCmsApp.pVAppUser.deleted', { param : id }, null);
    }
}

@Component({
    selector: 'jhi-pv-app-user-delete-popup',
    template: ''
})
export class PVAppUserDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private pVAppUserPopupService: PVAppUserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.pVAppUserPopupService
                .open(PVAppUserDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
