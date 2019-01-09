import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChampionSelectSharedModule } from 'app/shared';
import {
    QuoteComponent,
    QuoteDetailComponent,
    QuoteUpdateComponent,
    QuoteDeletePopupComponent,
    QuoteDeleteDialogComponent,
    quoteRoute,
    quotePopupRoute
} from './';

const ENTITY_STATES = [...quoteRoute, ...quotePopupRoute];

@NgModule({
    imports: [ChampionSelectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [QuoteComponent, QuoteDetailComponent, QuoteUpdateComponent, QuoteDeleteDialogComponent, QuoteDeletePopupComponent],
    entryComponents: [QuoteComponent, QuoteUpdateComponent, QuoteDeleteDialogComponent, QuoteDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChampionSelectQuoteModule {}
