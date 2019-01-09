import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDuos } from 'app/shared/model/duos.model';
import { DuosService } from './duos.service';
import { ISoloMember } from 'app/shared/model/solo-member.model';
import { SoloMemberService } from 'app/entities/solo-member';

@Component({
    selector: 'jhi-duos-update',
    templateUrl: './duos-update.component.html'
})
export class DuosUpdateComponent implements OnInit {
    private _duos: IDuos;
    isSaving: boolean;

    solomembers: ISoloMember[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private duosService: DuosService,
        private soloMemberService: SoloMemberService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ duos }) => {
            this.duos = duos;
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
        if (this.duos.id !== undefined) {
            this.subscribeToSaveResponse(this.duosService.update(this.duos));
        } else {
            this.subscribeToSaveResponse(this.duosService.create(this.duos));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDuos>>) {
        result.subscribe((res: HttpResponse<IDuos>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get duos() {
        return this._duos;
    }

    set duos(duos: IDuos) {
        this._duos = duos;
    }
}
