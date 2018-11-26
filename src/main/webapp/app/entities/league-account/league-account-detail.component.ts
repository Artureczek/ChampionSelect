import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILeagueAccount } from 'app/shared/model/league-account.model';

@Component({
    selector: 'jhi-league-account-detail',
    templateUrl: './league-account-detail.component.html'
})
export class LeagueAccountDetailComponent implements OnInit {
    leagueAccount: ILeagueAccount;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ leagueAccount }) => {
            this.leagueAccount = leagueAccount;
        });
    }

    previousState() {
        window.history.back();
    }
}
