import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IChampionTag } from 'app/shared/model/champion-tag.model';
import { ChampionTagService } from './champion-tag.service';
import { IChampion } from 'app/shared/model/champion.model';
import { ChampionService } from 'app/entities/champion';

@Component({
    selector: 'jhi-champion-tag-update',
    templateUrl: './champion-tag-update.component.html'
})
export class ChampionTagUpdateComponent implements OnInit {
    private _championTag: IChampionTag;
    isSaving: boolean;

    champions: IChampion[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private championTagService: ChampionTagService,
        private championService: ChampionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ championTag }) => {
            this.championTag = championTag;
        });
        this.championService.query().subscribe(
            (res: HttpResponse<IChampion[]>) => {
                this.champions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.championTag.id !== undefined) {
            this.subscribeToSaveResponse(this.championTagService.update(this.championTag));
        } else {
            this.subscribeToSaveResponse(this.championTagService.create(this.championTag));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IChampionTag>>) {
        result.subscribe((res: HttpResponse<IChampionTag>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackChampionById(index: number, item: IChampion) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get championTag() {
        return this._championTag;
    }

    set championTag(championTag: IChampionTag) {
        this._championTag = championTag;
    }
}
