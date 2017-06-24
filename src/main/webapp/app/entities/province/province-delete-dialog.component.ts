import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { Province } from './province.model';
import { ProvincePopupService } from './province-popup.service';
import { ProvinceService } from './province.service';

@Component({
    selector: 'jhi-province-delete-dialog',
    templateUrl: './province-delete-dialog.component.html'
})
export class ProvinceDeleteDialogComponent {

    province: Province;

    constructor(
        private provinceService: ProvinceService,
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.provinceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'provinceListModification',
                content: 'Deleted an province'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success('pvAppCmsApp.province.deleted', { param : id }, null);
    }
}

@Component({
    selector: 'jhi-province-delete-popup',
    template: ''
})
export class ProvinceDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private provincePopupService: ProvincePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.provincePopupService
                .open(ProvinceDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
