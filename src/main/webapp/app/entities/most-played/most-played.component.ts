import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMostPlayed } from 'app/shared/model/most-played.model';
import { Principal } from 'app/core';
import { MostPlayedService } from './most-played.service';

@Component({
    selector: 'jhi-most-played',
    templateUrl: './most-played.component.html'
})
export class MostPlayedComponent implements OnInit, OnDestroy {
    mostPlayeds: IMostPlayed[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private mostPlayedService: MostPlayedService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.mostPlayedService.query().subscribe(
            (res: HttpResponse<IMostPlayed[]>) => {
                this.mostPlayeds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMostPlayeds();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMostPlayed) {
        return item.id;
    }

    registerChangeInMostPlayeds() {
        this.eventSubscriber = this.eventManager.subscribe('mostPlayedListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
