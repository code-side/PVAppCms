import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { PVAppUser } from './pv-app-user.model';
import { PVAppUserService } from './pv-app-user.service';

@Injectable()
export class PVAppUserPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private pVAppUserService: PVAppUserService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.pVAppUserService.find(id).subscribe((pVAppUser) => {
                if (pVAppUser.registrationDate) {
                    pVAppUser.registrationDate = {
                        year: pVAppUser.registrationDate.getFullYear(),
                        month: pVAppUser.registrationDate.getMonth() + 1,
                        day: pVAppUser.registrationDate.getDate()
                    };
                }
                if (pVAppUser.birthday) {
                    pVAppUser.birthday = {
                        year: pVAppUser.birthday.getFullYear(),
                        month: pVAppUser.birthday.getMonth() + 1,
                        day: pVAppUser.birthday.getDate()
                    };
                }
                this.pVAppUserModalRef(component, pVAppUser);
            });
        } else {
            return this.pVAppUserModalRef(component, new PVAppUser());
        }
    }

    pVAppUserModalRef(component: Component, pVAppUser: PVAppUser): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.pVAppUser = pVAppUser;
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
