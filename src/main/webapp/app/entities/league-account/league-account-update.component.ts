import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ILeagueAccount } from 'app/shared/model/league-account.model';
import { LeagueAccountService } from './league-account.service';

@Component({
    selector: 'jhi-league-account-update',
    templateUrl: './league-account-update.component.html'
})
export class LeagueAccountUpdateComponent implements OnInit {
    private _leagueAccount: ILeagueAccount;
    isSaving: boolean;
    lastUpdateDp: any;

    constructor(private leagueAccountService: LeagueAccountService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ leagueAccount }) => {
            this.leagueAccount = leagueAccount;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.leagueAccount.id !== undefined) {
            this.subscribeToSaveResponse(this.leagueAccountService.update(this.leagueAccount));
        } else {
            this.subscribeToSaveResponse(this.leagueAccountService.create(this.leagueAccount));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILeagueAccount>>) {
        result.subscribe((res: HttpResponse<ILeagueAccount>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get leagueAccount() {
        return this._leagueAccount;
    }

    set leagueAccount(leagueAccount: ILeagueAccount) {
        this._leagueAccount = leagueAccount;
    }
}
