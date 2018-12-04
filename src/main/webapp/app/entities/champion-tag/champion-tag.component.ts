import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IChampionTag } from 'app/shared/model/champion-tag.model';
import { Principal } from 'app/core';
import { ChampionTagService } from './champion-tag.service';

@Component({
    selector: 'jhi-champion-tag',
    templateUrl: './champion-tag.component.html'
})
export class ChampionTagComponent implements OnInit, OnDestroy {
    championTags: IChampionTag[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private championTagService: ChampionTagService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.championTagService.query().subscribe(
            (res: HttpResponse<IChampionTag[]>) => {
                this.championTags = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInChampionTags();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IChampionTag) {
        return item.id;
    }

    registerChangeInChampionTags() {
        this.eventSubscriber = this.eventManager.subscribe('championTagListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
