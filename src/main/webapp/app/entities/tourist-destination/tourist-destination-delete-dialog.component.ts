import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { TouristDestination } from './tourist-destination.model';
import { TouristDestinationPopupService } from './tourist-destination-popup.service';
import { TouristDestinationService } from './tourist-destination.service';

@Component({
    selector: 'jhi-tourist-destination-delete-dialog',
    templateUrl: './tourist-destination-delete-dialog.component.html'
})
export class TouristDestinationDeleteDialogComponent {

    touristDestination: TouristDestination;

    constructor(
        private touristDestinationService: TouristDestinationService,
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.touristDestinationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'touristDestinationListModification',
                content: 'Deleted an touristDestination'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success('pvAppCmsApp.touristDestination.deleted', { param : id }, null);
    }
}

@Component({
    selector: 'jhi-tourist-destination-delete-popup',
    template: ''
})
export class TouristDestinationDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private touristDestinationPopupService: TouristDestinationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.touristDestinationPopupService
                .open(TouristDestinationDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
