import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChampionSelectSharedModule } from 'app/shared';
import {
    LeagueAccountComponent,
    LeagueAccountDetailComponent,
    LeagueAccountUpdateComponent,
    LeagueAccountDeletePopupComponent,
    LeagueAccountDeleteDialogComponent,
    leagueAccountRoute,
    leagueAccountPopupRoute
} from './';

const ENTITY_STATES = [...leagueAccountRoute, ...leagueAccountPopupRoute];

@NgModule({
    imports: [ChampionSelectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LeagueAccountComponent,
        LeagueAccountDetailComponent,
        LeagueAccountUpdateComponent,
        LeagueAccountDeleteDialogComponent,
        LeagueAccountDeletePopupComponent
    ],
    entryComponents: [
        LeagueAccountComponent,
        LeagueAccountUpdateComponent,
        LeagueAccountDeleteDialogComponent,
        LeagueAccountDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChampionSelectLeagueAccountModule {}
