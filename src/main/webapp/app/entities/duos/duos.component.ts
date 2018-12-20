import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDuos } from 'app/shared/model/duos.model';
import { Principal } from 'app/core';
import { DuosService } from './duos.service';

@Component({
    selector: 'jhi-duos',
    templateUrl: './duos.component.html'
})
export class DuosComponent implements OnInit, OnDestroy {
    duos: IDuos[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private duosService: DuosService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.duosService.query().subscribe(
            (res: HttpResponse<IDuos[]>) => {
                this.duos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDuos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDuos) {
        return item.id;
    }

    registerChangeInDuos() {
        this.eventSubscriber = this.eventManager.subscribe('duosListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
