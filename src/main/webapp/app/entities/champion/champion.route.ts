import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Champion } from 'app/shared/model/champion.model';
import { ChampionService } from './champion.service';
import { ChampionComponent } from './champion.component';
import { ChampionDetailComponent } from './champion-detail.component';
import { ChampionUpdateComponent } from './champion-update.component';
import { ChampionDeletePopupComponent } from './champion-delete-dialog.component';
import { IChampion } from 'app/shared/model/champion.model';

@Injectable({ providedIn: 'root' })
export class ChampionResolve implements Resolve<IChampion> {
    constructor(private service: ChampionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((champion: HttpResponse<Champion>) => champion.body));
        }
        return of(new Champion());
    }
}

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
        resolve: {
            champion: ChampionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.champion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'champion/new',
        component: ChampionUpdateComponent,
        resolve: {
            champion: ChampionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.champion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'champion/:id/edit',
        component: ChampionUpdateComponent,
        resolve: {
            champion: ChampionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.champion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const championPopupRoute: Routes = [
    {
        path: 'champion/:id/delete',
        component: ChampionDeletePopupComponent,
        resolve: {
            champion: ChampionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.champion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
