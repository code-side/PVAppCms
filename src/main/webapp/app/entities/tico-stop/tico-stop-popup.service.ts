import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TicoStop } from './tico-stop.model';
import { TicoStopService } from './tico-stop.service';
import { ProvinceService } from './../province/province.service';
import { TicoStopDialogComponent } from './';

@Injectable()
export class TicoStopPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private ticoStopService: TicoStopService,
        private provinceService: ProvinceService
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

        if (modalRef.componentInstance instanceof TicoStopDialogComponent) {
            let modal = (<TicoStopDialogComponent>modalRef.componentInstance);
            //console.log(ticoStop);
            this.provinceService.find(ticoStop.province.id).subscribe(val => {
                //console.log(val);
                modal.province = val;
                modal.cantons = val.cantons;
                modal.canton = ticoStop.province.canton;
            });
        }

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
