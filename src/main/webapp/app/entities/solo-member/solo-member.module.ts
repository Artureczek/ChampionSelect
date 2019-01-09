import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChampionSelectSharedModule } from 'app/shared';
import {
    SoloMemberComponent,
    SoloMemberDetailComponent,
    SoloMemberUpdateComponent,
    SoloMemberDeletePopupComponent,
    SoloMemberDeleteDialogComponent,
    soloMemberRoute,
    soloMemberPopupRoute
} from './';

const ENTITY_STATES = [...soloMemberRoute, ...soloMemberPopupRoute];

@NgModule({
    imports: [ChampionSelectSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SoloMemberComponent,
        SoloMemberDetailComponent,
        SoloMemberUpdateComponent,
        SoloMemberDeleteDialogComponent,
        SoloMemberDeletePopupComponent
    ],
    entryComponents: [SoloMemberComponent, SoloMemberUpdateComponent, SoloMemberDeleteDialogComponent, SoloMemberDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChampionSelectSoloMemberModule {}
