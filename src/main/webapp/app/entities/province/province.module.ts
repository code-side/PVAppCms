import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PvAppCmsSharedModule } from '../../shared';
import {
    ProvinceService,
    ProvincePopupService,
    ProvinceComponent,
    ProvinceDetailComponent,
    ProvinceDialogComponent,
    ProvincePopupComponent,
    ProvinceDeletePopupComponent,
    ProvinceDeleteDialogComponent,
    provinceRoute,
    provincePopupRoute,
    ProvinceResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...provinceRoute,
    ...provincePopupRoute,
];

@NgModule({
    imports: [
        PvAppCmsSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ProvinceComponent,
        ProvinceDetailComponent,
        ProvinceDialogComponent,
        ProvinceDeleteDialogComponent,
        ProvincePopupComponent,
        ProvinceDeletePopupComponent,
    ],
    entryComponents: [
        ProvinceComponent,
        ProvinceDialogComponent,
        ProvincePopupComponent,
        ProvinceDeleteDialogComponent,
        ProvinceDeletePopupComponent,
    ],
    providers: [
        ProvinceService,
        ProvincePopupService,
        ProvinceResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PvAppCmsProvinceModule {}
