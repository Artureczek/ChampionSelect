import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ChampionSelectChampionTagModule } from './champion-tag/champion-tag.module';
import { ChampionSelectChampionModule } from './champion/champion.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ChampionSelectChampionTagModule,
        ChampionSelectChampionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChampionSelectEntityModule {}
