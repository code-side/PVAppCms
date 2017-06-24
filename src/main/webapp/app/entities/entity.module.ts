import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PvAppCmsPVAppUserModule } from './p-v-app-user/pv-app-user.module';
import { PvAppCmsTouristDestinationModule } from './tourist-destination/tourist-destination.module';
import { PvAppCmsProvinceModule } from './province/province.module';
import { PvAppCmsTicoStopModule } from './tico-stop/tico-stop.module';
import { PvAppCmsTouristicInterestModule } from './touristic-interest/touristic-interest.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        PvAppCmsPVAppUserModule,
        PvAppCmsTouristDestinationModule,
        PvAppCmsProvinceModule,
        PvAppCmsTicoStopModule,
        PvAppCmsTouristicInterestModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PvAppCmsEntityModule {}
