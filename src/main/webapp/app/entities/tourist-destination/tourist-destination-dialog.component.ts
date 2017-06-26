import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { ResponseWrapper } from '../../shared';

import { TouristDestination } from './tourist-destination.model';
import { TouristDestinationPopupService } from './tourist-destination-popup.service';
import { TouristDestinationService } from './tourist-destination.service';

import { Province } from '../province/province.model';
import { ProvinceRef } from '../province/province-ref.model';
import { ProvinceService } from '../province/province.service'
import { AttributeRef } from './attribute-ref.model';


@Component({
    selector: 'jhi-tourist-destination-dialog',
    templateUrl: './tourist-destination-dialog.component.html'
})
export class TouristDestinationDialogComponent implements OnInit {

    touristDestination: TouristDestination;
    authorities: any[];
    isSaving: boolean;
    provinces: Province[];
    selectedProvince: Province;
    selectedCanton: string;
    attribute: AttributeRef;
    isEditAtt: boolean;
    indexToEdit: number;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private touristDestinationService: TouristDestinationService,
        private eventManager: JhiEventManager,
        private provinceService: ProvinceService
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.isEditAtt = false;
        this.attribute = new AttributeRef();
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.provinceService.query().subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    private onSuccess(data, headers) {
        this.provinces = data;  
        if(this.touristDestination.id === undefined){
            this.touristDestination.province = new ProvinceRef();
            this.touristDestination.attributes = [];
            }
        else
            this.findProvince();

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
    saveProvinceRef(action:any){
        if(action === 'province'){
            this.touristDestination.province.name = this.selectedProvince.name;
            this.touristDestination.province.id = this.selectedProvince.id;
            console.log('change')
        }else{
            this.touristDestination.province.canton =  this.selectedCanton;
        }
    }
    findProvince(){
        for(let province of this.provinces){
            if(province.id === this.touristDestination.province.id){
                this.selectedProvince = province;
                for(let canton of province.cantons){
                    if(canton === this.touristDestination.province.canton){
                         this.selectedCanton = canton;
                         break;
                     }
                break;
                }
            }
        }
    }
    addAtribute(){
        if(!this.isEditAtt)
            this.touristDestination.attributes.push(this.attribute)
        else{
            this.touristDestination.attributes[this.indexToEdit].name = this.attribute.name;
            this.touristDestination.attributes[this.indexToEdit].value = this.attribute.value;
        }
        this.attribute = new AttributeRef();
        this.isEditAtt = false;

    }
    editAttribute(att: AttributeRef, i: number){
        this.attribute.name = att.name;
        this.attribute.value = att.value;
        this.isEditAtt = true;
        this.indexToEdit = i;
        console.log(this.attribute)
    }
    removeAttribute(i: number){
        this.touristDestination.attributes.splice(i,1);
    }
    cancelEdit(){
        this.attribute = new AttributeRef();
        this.isEditAtt = !this.isEditAtt;
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
