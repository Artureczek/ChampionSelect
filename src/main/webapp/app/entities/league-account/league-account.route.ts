import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LeagueAccount } from 'app/shared/model/league-account.model';
import { LeagueAccountService } from './league-account.service';
import { LeagueAccountComponent } from './league-account.component';
import { LeagueAccountDetailComponent } from './league-account-detail.component';
import { LeagueAccountUpdateComponent } from './league-account-update.component';
import { LeagueAccountDeletePopupComponent } from './league-account-delete-dialog.component';
import { ILeagueAccount } from 'app/shared/model/league-account.model';

@Injectable({ providedIn: 'root' })
export class LeagueAccountResolve implements Resolve<ILeagueAccount> {
    constructor(private service: LeagueAccountService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((leagueAccount: HttpResponse<LeagueAccount>) => leagueAccount.body));
        }
        return of(new LeagueAccount());
    }
}

export const leagueAccountRoute: Routes = [
    {
        path: 'league-account',
        component: LeagueAccountComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.leagueAccount.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'league-account/:id/view',
        component: LeagueAccountDetailComponent,
        resolve: {
            leagueAccount: LeagueAccountResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.leagueAccount.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'league-account/new',
        component: LeagueAccountUpdateComponent,
        resolve: {
            leagueAccount: LeagueAccountResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.leagueAccount.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'league-account/:id/edit',
        component: LeagueAccountUpdateComponent,
        resolve: {
            leagueAccount: LeagueAccountResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.leagueAccount.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const leagueAccountPopupRoute: Routes = [
    {
        path: 'league-account/:id/delete',
        component: LeagueAccountDeletePopupComponent,
        resolve: {
            leagueAccount: LeagueAccountResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.leagueAccount.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
