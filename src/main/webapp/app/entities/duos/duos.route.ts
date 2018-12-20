import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Duos } from 'app/shared/model/duos.model';
import { DuosService } from './duos.service';
import { DuosComponent } from './duos.component';
import { DuosDetailComponent } from './duos-detail.component';
import { DuosUpdateComponent } from './duos-update.component';
import { DuosDeletePopupComponent } from './duos-delete-dialog.component';
import { IDuos } from 'app/shared/model/duos.model';

@Injectable({ providedIn: 'root' })
export class DuosResolve implements Resolve<IDuos> {
    constructor(private service: DuosService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((duos: HttpResponse<Duos>) => duos.body));
        }
        return of(new Duos());
    }
}

export const duosRoute: Routes = [
    {
        path: 'duos',
        component: DuosComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.duos.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'duos/:id/view',
        component: DuosDetailComponent,
        resolve: {
            duos: DuosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.duos.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'duos/new',
        component: DuosUpdateComponent,
        resolve: {
            duos: DuosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.duos.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'duos/:id/edit',
        component: DuosUpdateComponent,
        resolve: {
            duos: DuosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.duos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const duosPopupRoute: Routes = [
    {
        path: 'duos/:id/delete',
        component: DuosDeletePopupComponent,
        resolve: {
            duos: DuosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.duos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
