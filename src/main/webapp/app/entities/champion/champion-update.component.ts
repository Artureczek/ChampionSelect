import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IChampion } from 'app/shared/model/champion.model';
import { ChampionService } from './champion.service';
import { ISoloMember } from 'app/shared/model/solo-member.model';
import { SoloMemberService } from 'app/entities/solo-member';

@Component({
    selector: 'jhi-champion-update',
    templateUrl: './champion-update.component.html'
})
export class ChampionUpdateComponent implements OnInit {
    private _champion: IChampion;
    isSaving: boolean;

    solomembers: ISoloMember[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private championService: ChampionService,
        private soloMemberService: SoloMemberService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ champion }) => {
            this.champion = champion;
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSoloMemberById(index: number, item: ISoloMember) {
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
    get champion() {
        return this._champion;
    }

    set champion(champion: IChampion) {
        this._champion = champion;
    }
}
