import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMostPlayed } from 'app/shared/model/most-played.model';

@Component({
    selector: 'jhi-most-played-detail',
    templateUrl: './most-played-detail.component.html'
})
export class MostPlayedDetailComponent implements OnInit {
    mostPlayed: IMostPlayed;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mostPlayed }) => {
            this.mostPlayed = mostPlayed;
        });
    }

    previousState() {
        window.history.back();
    }
}
