import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IChampion } from 'app/shared/model/champion.model';
import { ChampionService } from './champion.service';

@Component({
    selector: 'jhi-champion-update',
    templateUrl: './champion-update.component.html'
})
export class ChampionUpdateComponent implements OnInit {
    private _champion: IChampion;
    isSaving: boolean;

    constructor(private championService: ChampionService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ champion }) => {
            this.champion = champion;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.champion.id !== undefined) {
            this.subscribeToSaveResponse(this.championService.update(this.champion));
        } else {
            this.subscribeToSaveResponse(this.championService.create(this.champion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IChampion>>) {
        result.subscribe((res: HttpResponse<IChampion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get champion() {
        return this._champion;
    }

    set champion(champion: IChampion) {
        this._champion = champion;
    }
}
