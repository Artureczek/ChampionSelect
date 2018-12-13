import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChampionSelectSharedModule } from 'app/shared';
import { ChampionComponent, ChampionDetailComponent, championRoute } from './';

const ENTITY_STATES = [...championRoute];

@NgModule({
    imports: [ChampionSelectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ChampionComponent, ChampionDetailComponent],
    entryComponents: [ChampionComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChampionSelectChampionModule {}
