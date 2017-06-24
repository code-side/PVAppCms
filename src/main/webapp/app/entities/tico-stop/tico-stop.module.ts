import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PvAppCmsSharedModule } from '../../shared';
import {
    TicoStopService,
    TicoStopPopupService,
    TicoStopComponent,
    TicoStopDetailComponent,
    TicoStopDialogComponent,
    TicoStopPopupComponent,
    TicoStopDeletePopupComponent,
    TicoStopDeleteDialogComponent,
    ticoStopRoute,
    ticoStopPopupRoute,
    TicoStopResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...ticoStopRoute,
    ...ticoStopPopupRoute,
];

@NgModule({
    imports: [
        PvAppCmsSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TicoStopComponent,
        TicoStopDetailComponent,
        TicoStopDialogComponent,
        TicoStopDeleteDialogComponent,
        TicoStopPopupComponent,
        TicoStopDeletePopupComponent,
    ],
    entryComponents: [
        TicoStopComponent,
        TicoStopDialogComponent,
        TicoStopPopupComponent,
        TicoStopDeleteDialogComponent,
        TicoStopDeletePopupComponent,
    ],
    providers: [
        TicoStopService,
        TicoStopPopupService,
        TicoStopResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PvAppCmsTicoStopModule {}
