import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { ChampionComponent } from './champion.component';
import { ChampionDetailComponent } from './champion-detail.component';

export const championRoute: Routes = [
    {
        path: 'champion',
        component: ChampionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.champion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'champion/:id/view',
        component: ChampionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.champion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
