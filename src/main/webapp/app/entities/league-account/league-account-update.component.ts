import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILeagueAccount } from 'app/shared/model/league-account.model';
import { LeagueAccountService } from './league-account.service';

@Component({
    selector: 'jhi-league-account-update',
    templateUrl: './league-account-update.component.html'
})
export class LeagueAccountUpdateComponent implements OnInit {
    private _leagueAccount: ILeagueAccount;
    isSaving: boolean;
    lastUpdate: string;

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
        this.leagueAccount.lastUpdate = moment(this.lastUpdate, DATE_TIME_FORMAT);
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
        this.lastUpdate = moment(leagueAccount.lastUpdate).format(DATE_TIME_FORMAT);
    }
}
