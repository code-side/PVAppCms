import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { TouristicInterest } from './touristic-interest.model';
import { TouristicInterestPopupService } from './touristic-interest-popup.service';
import { TouristicInterestService } from './touristic-interest.service';

@Component({
    selector: 'jhi-touristic-interest-delete-dialog',
    templateUrl: './touristic-interest-delete-dialog.component.html'
})
export class TouristicInterestDeleteDialogComponent {

    touristicInterest: TouristicInterest;

    constructor(
        private touristicInterestService: TouristicInterestService,
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.touristicInterestService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'touristicInterestListModification',
                content: 'Deleted an touristicInterest'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success('pvAppCmsApp.touristicInterest.deleted', { param : id }, null);
    }
}

@Component({
    selector: 'jhi-touristic-interest-delete-popup',
    template: ''
})
export class TouristicInterestDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private touristicInterestPopupService: TouristicInterestPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.touristicInterestPopupService
                .open(TouristicInterestDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
