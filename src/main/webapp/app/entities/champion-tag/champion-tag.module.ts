import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChampionSelectSharedModule } from 'app/shared';
import {
    ChampionTagComponent,
    ChampionTagDetailComponent,
    ChampionTagUpdateComponent,
    ChampionTagDeletePopupComponent,
    ChampionTagDeleteDialogComponent,
    championTagRoute,
    championTagPopupRoute
} from './';

const ENTITY_STATES = [...championTagRoute, ...championTagPopupRoute];

@NgModule({
    imports: [ChampionSelectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ChampionTagComponent,
        ChampionTagDetailComponent,
        ChampionTagUpdateComponent,
        ChampionTagDeleteDialogComponent,
        ChampionTagDeletePopupComponent
    ],
    entryComponents: [ChampionTagComponent, ChampionTagUpdateComponent, ChampionTagDeleteDialogComponent, ChampionTagDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChampionSelectChampionTagModule {}
