import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChampionSelectSharedModule } from 'app/shared';
import {
    ChampionComponent,
    ChampionDetailComponent,
    ChampionUpdateComponent,
    ChampionDeletePopupComponent,
    ChampionDeleteDialogComponent,
    championRoute,
    championPopupRoute
} from './';

const ENTITY_STATES = [...championRoute, ...championPopupRoute];

@NgModule({
    imports: [ChampionSelectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ChampionComponent,
        ChampionDetailComponent,
        ChampionUpdateComponent,
        ChampionDeleteDialogComponent,
        ChampionDeletePopupComponent
    ],
    entryComponents: [ChampionComponent, ChampionUpdateComponent, ChampionDeleteDialogComponent, ChampionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChampionSelectChampionModule {}
