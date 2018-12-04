import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IChampion } from 'app/shared/model/champion.model';
import { Principal } from 'app/core';
import { ChampionService } from './champion.service';

@Component({
    selector: 'jhi-champion',
    templateUrl: './champion.component.html'
})
export class ChampionComponent implements OnInit, OnDestroy {
    champions: IChampion[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private championService: ChampionService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.championService.query().subscribe(
            (res: HttpResponse<IChampion[]>) => {
                this.champions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInChampions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IChampion) {
        return item.id;
    }

    registerChangeInChampions() {
        this.eventSubscriber = this.eventManager.subscribe('championListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
