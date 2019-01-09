import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MostPlayed } from 'app/shared/model/most-played.model';
import { MostPlayedService } from './most-played.service';
import { MostPlayedComponent } from './most-played.component';
import { MostPlayedDetailComponent } from './most-played-detail.component';
import { MostPlayedUpdateComponent } from './most-played-update.component';
import { MostPlayedDeletePopupComponent } from './most-played-delete-dialog.component';
import { IMostPlayed } from 'app/shared/model/most-played.model';

@Injectable({ providedIn: 'root' })
export class MostPlayedResolve implements Resolve<IMostPlayed> {
    constructor(private service: MostPlayedService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((mostPlayed: HttpResponse<MostPlayed>) => mostPlayed.body));
        }
        return of(new MostPlayed());
    }
}

export const mostPlayedRoute: Routes = [
    {
        path: 'most-played',
        component: MostPlayedComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.mostPlayed.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'most-played/:id/view',
        component: MostPlayedDetailComponent,
        resolve: {
            mostPlayed: MostPlayedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.mostPlayed.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'most-played/new',
        component: MostPlayedUpdateComponent,
        resolve: {
            mostPlayed: MostPlayedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.mostPlayed.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'most-played/:id/edit',
        component: MostPlayedUpdateComponent,
        resolve: {
            mostPlayed: MostPlayedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.mostPlayed.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mostPlayedPopupRoute: Routes = [
    {
        path: 'most-played/:id/delete',
        component: MostPlayedDeletePopupComponent,
        resolve: {
            mostPlayed: MostPlayedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.mostPlayed.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
