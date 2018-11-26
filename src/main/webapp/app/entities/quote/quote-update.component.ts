import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IQuote } from 'app/shared/model/quote.model';
import { QuoteService } from './quote.service';
import { ISoloMember } from 'app/shared/model/solo-member.model';
import { SoloMemberService } from 'app/entities/solo-member';

@Component({
    selector: 'jhi-quote-update',
    templateUrl: './quote-update.component.html'
})
export class QuoteUpdateComponent implements OnInit {
    private _quote: IQuote;
    isSaving: boolean;

    solomembers: ISoloMember[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private quoteService: QuoteService,
        private soloMemberService: SoloMemberService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ quote }) => {
            this.quote = quote;
        });
        this.soloMemberService.query().subscribe(
            (res: HttpResponse<ISoloMember[]>) => {
                this.solomembers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.quote.id !== undefined) {
            this.subscribeToSaveResponse(this.quoteService.update(this.quote));
        } else {
            this.subscribeToSaveResponse(this.quoteService.create(this.quote));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IQuote>>) {
        result.subscribe((res: HttpResponse<IQuote>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSoloMemberById(index: number, item: ISoloMember) {
        return item.id;
    }
    get quote() {
        return this._quote;
    }

    set quote(quote: IQuote) {
        this._quote = quote;
    }
}
