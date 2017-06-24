import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PvAppCmsSharedModule } from '../../shared';
import {
    PVAppUserService,
    PVAppUserPopupService,
    PVAppUserComponent,
    PVAppUserDetailComponent,
    PVAppUserDialogComponent,
    PVAppUserPopupComponent,
    PVAppUserDeletePopupComponent,
    PVAppUserDeleteDialogComponent,
    pVAppUserRoute,
    pVAppUserPopupRoute,
    PVAppUserResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...pVAppUserRoute,
    ...pVAppUserPopupRoute,
];

@NgModule({
    imports: [
        PvAppCmsSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PVAppUserComponent,
        PVAppUserDetailComponent,
        PVAppUserDialogComponent,
        PVAppUserDeleteDialogComponent,
        PVAppUserPopupComponent,
        PVAppUserDeletePopupComponent,
    ],
    entryComponents: [
        PVAppUserComponent,
        PVAppUserDialogComponent,
        PVAppUserPopupComponent,
        PVAppUserDeleteDialogComponent,
        PVAppUserDeletePopupComponent,
    ],
    providers: [
        PVAppUserService,
        PVAppUserPopupService,
        PVAppUserResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PvAppCmsPVAppUserModule {}
