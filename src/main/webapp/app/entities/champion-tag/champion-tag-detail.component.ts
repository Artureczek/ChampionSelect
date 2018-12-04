import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChampionTag } from 'app/shared/model/champion-tag.model';

@Component({
    selector: 'jhi-champion-tag-detail',
    templateUrl: './champion-tag-detail.component.html'
})
export class ChampionTagDetailComponent implements OnInit {
    championTag: IChampionTag;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ championTag }) => {
            this.championTag = championTag;
        });
    }

    previousState() {
        window.history.back();
    }
}
