import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChampionSelectSharedModule } from 'app/shared';
import {
    DuosComponent,
    DuosDetailComponent,
    DuosUpdateComponent,
    DuosDeletePopupComponent,
    DuosDeleteDialogComponent,
    duosRoute,
    duosPopupRoute
} from './';

const ENTITY_STATES = [...duosRoute, ...duosPopupRoute];

@NgModule({
    imports: [ChampionSelectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [DuosComponent, DuosDetailComponent, DuosUpdateComponent, DuosDeleteDialogComponent, DuosDeletePopupComponent],
    entryComponents: [DuosComponent, DuosUpdateComponent, DuosDeleteDialogComponent, DuosDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChampionSelectDuosModule {}
