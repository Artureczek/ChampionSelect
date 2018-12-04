import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ChampionTag } from 'app/shared/model/champion-tag.model';
import { ChampionTagService } from './champion-tag.service';
import { ChampionTagComponent } from './champion-tag.component';
import { ChampionTagDetailComponent } from './champion-tag-detail.component';
import { ChampionTagUpdateComponent } from './champion-tag-update.component';
import { ChampionTagDeletePopupComponent } from './champion-tag-delete-dialog.component';
import { IChampionTag } from 'app/shared/model/champion-tag.model';

@Injectable({ providedIn: 'root' })
export class ChampionTagResolve implements Resolve<IChampionTag> {
    constructor(private service: ChampionTagService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((championTag: HttpResponse<ChampionTag>) => championTag.body));
        }
        return of(new ChampionTag());
    }
}

export const championTagRoute: Routes = [
    {
        path: 'champion-tag',
        component: ChampionTagComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.championTag.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'champion-tag/:id/view',
        component: ChampionTagDetailComponent,
        resolve: {
            championTag: ChampionTagResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.championTag.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'champion-tag/new',
        component: ChampionTagUpdateComponent,
        resolve: {
            championTag: ChampionTagResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.championTag.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'champion-tag/:id/edit',
        component: ChampionTagUpdateComponent,
        resolve: {
            championTag: ChampionTagResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.championTag.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const championTagPopupRoute: Routes = [
    {
        path: 'champion-tag/:id/delete',
        component: ChampionTagDeletePopupComponent,
        resolve: {
            championTag: ChampionTagResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.championTag.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
