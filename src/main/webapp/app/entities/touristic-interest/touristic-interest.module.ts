import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PvAppCmsSharedModule } from '../../shared';
import {
    TouristicInterestService,
    TouristicInterestPopupService,
    TouristicInterestComponent,
    TouristicInterestDetailComponent,
    TouristicInterestDialogComponent,
    TouristicInterestPopupComponent,
    TouristicInterestDeletePopupComponent,
    TouristicInterestDeleteDialogComponent,
    touristicInterestRoute,
    touristicInterestPopupRoute,
    TouristicInterestResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...touristicInterestRoute,
    ...touristicInterestPopupRoute,
];

@NgModule({
    imports: [
        PvAppCmsSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TouristicInterestComponent,
        TouristicInterestDetailComponent,
        TouristicInterestDialogComponent,
        TouristicInterestDeleteDialogComponent,
        TouristicInterestPopupComponent,
        TouristicInterestDeletePopupComponent,
    ],
    entryComponents: [
        TouristicInterestComponent,
        TouristicInterestDialogComponent,
        TouristicInterestPopupComponent,
        TouristicInterestDeleteDialogComponent,
        TouristicInterestDeletePopupComponent,
    ],
    providers: [
        TouristicInterestService,
        TouristicInterestPopupService,
        TouristicInterestResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PvAppCmsTouristicInterestModule {}
