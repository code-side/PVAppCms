import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TouristDestination } from './tourist-destination.model';
import { TouristDestinationPopupService } from './tourist-destination-popup.service';
import { TouristDestinationService } from './tourist-destination.service';

@Component({
    selector: 'jhi-tourist-destination-dialog',
    templateUrl: './tourist-destination-dialog.component.html'
})
export class TouristDestinationDialogComponent implements OnInit {

    touristDestination: TouristDestination;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private touristDestinationService: TouristDestinationService,
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
        if (this.touristDestination.id !== undefined) {
            this.subscribeToSaveResponse(
                this.touristDestinationService.update(this.touristDestination), false);
        } else {
            this.subscribeToSaveResponse(
                this.touristDestinationService.create(this.touristDestination), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<TouristDestination>, isCreated: boolean) {
        result.subscribe((res: TouristDestination) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: TouristDestination, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'pvAppCmsApp.touristDestination.created'
            : 'pvAppCmsApp.touristDestination.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'touristDestinationListModification', content: 'OK'});
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
    selector: 'jhi-tourist-destination-popup',
    template: ''
})
export class TouristDestinationPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private touristDestinationPopupService: TouristDestinationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.touristDestinationPopupService
                    .open(TouristDestinationDialogComponent, params['id']);
            } else {
                this.modalRef = this.touristDestinationPopupService
                    .open(TouristDestinationDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
