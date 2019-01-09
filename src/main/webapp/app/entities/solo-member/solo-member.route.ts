import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SoloMember } from 'app/shared/model/solo-member.model';
import { SoloMemberService } from './solo-member.service';
import { SoloMemberComponent } from './solo-member.component';
import { SoloMemberDetailComponent } from './solo-member-detail.component';
import { SoloMemberUpdateComponent } from './solo-member-update.component';
import { SoloMemberDeletePopupComponent } from './solo-member-delete-dialog.component';
import { ISoloMember } from 'app/shared/model/solo-member.model';

@Injectable({ providedIn: 'root' })
export class SoloMemberResolve implements Resolve<ISoloMember> {
    constructor(private service: SoloMemberService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((soloMember: HttpResponse<SoloMember>) => soloMember.body));
        }
        return of(new SoloMember());
    }
}

export const soloMemberRoute: Routes = [
    {
        path: 'solo-member',
        component: SoloMemberComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.soloMember.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'solo-member/:id/view',
        component: SoloMemberDetailComponent,
        resolve: {
            soloMember: SoloMemberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.soloMember.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'solo-member/new',
        component: SoloMemberUpdateComponent,
        resolve: {
            soloMember: SoloMemberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.soloMember.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'solo-member/:id/edit',
        component: SoloMemberUpdateComponent,
        resolve: {
            soloMember: SoloMemberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.soloMember.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const soloMemberPopupRoute: Routes = [
    {
        path: 'solo-member/:id/delete',
        component: SoloMemberDeletePopupComponent,
        resolve: {
            soloMember: SoloMemberResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'championSelectApp.soloMember.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
