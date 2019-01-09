import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISoloMember } from 'app/shared/model/solo-member.model';
import { SoloMemberService } from './solo-member.service';
import { ILeagueAccount } from 'app/shared/model/league-account.model';
import { LeagueAccountService } from 'app/entities/league-account';

@Component({
    selector: 'jhi-solo-member-update',
    templateUrl: './solo-member-update.component.html'
})
export class SoloMemberUpdateComponent implements OnInit {
    private _soloMember: ISoloMember;
    isSaving: boolean;

    accounts: ILeagueAccount[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private soloMemberService: SoloMemberService,
        private leagueAccountService: LeagueAccountService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ soloMember }) => {
            this.soloMember = soloMember;
        });
        this.leagueAccountService.query({ filter: 'solomember-is-null' }).subscribe(
            (res: HttpResponse<ILeagueAccount[]>) => {
                if (!this.soloMember.account || !this.soloMember.account.id) {
                    this.accounts = res.body;
                } else {
                    this.leagueAccountService.find(this.soloMember.account.id).subscribe(
                        (subRes: HttpResponse<ILeagueAccount>) => {
                            this.accounts = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.soloMember.id !== undefined) {
            this.subscribeToSaveResponse(this.soloMemberService.update(this.soloMember));
        } else {
            this.subscribeToSaveResponse(this.soloMemberService.create(this.soloMember));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISoloMember>>) {
        result.subscribe((res: HttpResponse<ISoloMember>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackLeagueAccountById(index: number, item: ILeagueAccount) {
        return item.id;
    }
    get soloMember() {
        return this._soloMember;
    }

    set soloMember(soloMember: ISoloMember) {
        this._soloMember = soloMember;
    }
}
