import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PvAppCmsSharedModule } from '../../shared';
import {
    TouristDestinationService,
    TouristDestinationPopupService,
    TouristDestinationComponent,
    TouristDestinationDetailComponent,
    TouristDestinationDialogComponent,
    TouristDestinationPopupComponent,
    TouristDestinationDeletePopupComponent,
    TouristDestinationDeleteDialogComponent,
    touristDestinationRoute,
    touristDestinationPopupRoute,
    TouristDestinationResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...touristDestinationRoute,
    ...touristDestinationPopupRoute,
];

@NgModule({
    imports: [
        PvAppCmsSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TouristDestinationComponent,
        TouristDestinationDetailComponent,
        TouristDestinationDialogComponent,
        TouristDestinationDeleteDialogComponent,
        TouristDestinationPopupComponent,
        TouristDestinationDeletePopupComponent,
    ],
    entryComponents: [
        TouristDestinationComponent,
        TouristDestinationDialogComponent,
        TouristDestinationPopupComponent,
        TouristDestinationDeleteDialogComponent,
        TouristDestinationDeletePopupComponent,
    ],
    providers: [
        TouristDestinationService,
        TouristDestinationPopupService,
        TouristDestinationResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PvAppCmsTouristDestinationModule {}
