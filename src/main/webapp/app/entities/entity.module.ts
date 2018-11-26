import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ChampionSelectSoloMemberModule } from './solo-member/solo-member.module';
import { ChampionSelectQuoteModule } from './quote/quote.module';
import { ChampionSelectLeagueAccountModule } from './league-account/league-account.module';
import { ChampionSelectImageModule } from './image/image.module';
import { ChampionSelectChampionModule } from './champion/champion.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ChampionSelectSoloMemberModule,
        ChampionSelectQuoteModule,
        ChampionSelectLeagueAccountModule,
        ChampionSelectImageModule,
        ChampionSelectChampionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChampionSelectEntityModule {}
