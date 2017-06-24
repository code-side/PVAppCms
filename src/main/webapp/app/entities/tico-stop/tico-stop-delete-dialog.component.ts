import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { TicoStop } from './tico-stop.model';
import { TicoStopPopupService } from './tico-stop-popup.service';
import { TicoStopService } from './tico-stop.service';

@Component({
    selector: 'jhi-tico-stop-delete-dialog',
    templateUrl: './tico-stop-delete-dialog.component.html'
})
export class TicoStopDeleteDialogComponent {

    ticoStop: TicoStop;

    constructor(
        private ticoStopService: TicoStopService,
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.ticoStopService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'ticoStopListModification',
                content: 'Deleted an ticoStop'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success('pvAppCmsApp.ticoStop.deleted', { param : id }, null);
    }
}

@Component({
    selector: 'jhi-tico-stop-delete-popup',
    template: ''
})
export class TicoStopDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ticoStopPopupService: TicoStopPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.ticoStopPopupService
                .open(TicoStopDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
