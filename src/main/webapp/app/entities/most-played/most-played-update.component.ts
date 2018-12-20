import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMostPlayed } from 'app/shared/model/most-played.model';
import { MostPlayedService } from './most-played.service';
import { ISoloMember } from 'app/shared/model/solo-member.model';
import { SoloMemberService } from 'app/entities/solo-member';
import { IChampion } from 'app/shared/model/champion.model';
import { ChampionService } from 'app/entities/champion';

@Component({
    selector: 'jhi-most-played-update',
    templateUrl: './most-played-update.component.html'
})
export class MostPlayedUpdateComponent implements OnInit {
    private _mostPlayed: IMostPlayed;
    isSaving: boolean;

    solomembers: ISoloMember[];

    champions: IChampion[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private mostPlayedService: MostPlayedService,
        private soloMemberService: SoloMemberService,
        private championService: ChampionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mostPlayed }) => {
            this.mostPlayed = mostPlayed;
        });
        this.soloMemberService.query().subscribe(
            (res: HttpResponse<ISoloMember[]>) => {
                this.solomembers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        if (this.mostPlayed.id !== undefined) {
            this.subscribeToSaveResponse(this.mostPlayedService.update(this.mostPlayed));
        } else {
            this.subscribeToSaveResponse(this.mostPlayedService.create(this.mostPlayed));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMostPlayed>>) {
        result.subscribe((res: HttpResponse<IMostPlayed>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackChampionById(index: number, item: IChampion) {
        return item.id;
    }
    get mostPlayed() {
        return this._mostPlayed;
    }

    set mostPlayed(mostPlayed: IMostPlayed) {
        this._mostPlayed = mostPlayed;
    }
}
