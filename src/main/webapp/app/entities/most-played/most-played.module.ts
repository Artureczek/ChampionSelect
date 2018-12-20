import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChampionSelectSharedModule } from 'app/shared';
import {
    MostPlayedComponent,
    MostPlayedDetailComponent,
    MostPlayedUpdateComponent,
    MostPlayedDeletePopupComponent,
    MostPlayedDeleteDialogComponent,
    mostPlayedRoute,
    mostPlayedPopupRoute
} from './';

const ENTITY_STATES = [...mostPlayedRoute, ...mostPlayedPopupRoute];

@NgModule({
    imports: [ChampionSelectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MostPlayedComponent,
        MostPlayedDetailComponent,
        MostPlayedUpdateComponent,
        MostPlayedDeleteDialogComponent,
        MostPlayedDeletePopupComponent
    ],
    entryComponents: [MostPlayedComponent, MostPlayedUpdateComponent, MostPlayedDeleteDialogComponent, MostPlayedDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChampionSelectMostPlayedModule {}
