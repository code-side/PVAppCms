import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TicoStop } from './tico-stop.model';
import { TicoStopService } from './tico-stop.service';

@Injectable()
export class TicoStopPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private ticoStopService: TicoStopService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.ticoStopService.find(id).subscribe((ticoStop) => {
                this.ticoStopModalRef(component, ticoStop);
            });
        } else {
            return this.ticoStopModalRef(component, new TicoStop());
        }
    }

    ticoStopModalRef(component: Component, ticoStop: TicoStop): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.ticoStop = ticoStop;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
