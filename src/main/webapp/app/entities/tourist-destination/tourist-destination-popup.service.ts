import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TouristDestination } from './tourist-destination.model';
import { TouristDestinationService } from './tourist-destination.service';

@Injectable()
export class TouristDestinationPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private touristDestinationService: TouristDestinationService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.touristDestinationService.find(id).subscribe((touristDestination) => {
                this.touristDestinationModalRef(component, touristDestination);
            });
        } else {
            return this.touristDestinationModalRef(component, new TouristDestination());
        }
    }

    touristDestinationModalRef(component: Component, touristDestination: TouristDestination): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.touristDestination = touristDestination;
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
