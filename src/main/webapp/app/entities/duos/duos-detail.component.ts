import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDuos } from 'app/shared/model/duos.model';

@Component({
    selector: 'jhi-duos-detail',
    templateUrl: './duos-detail.component.html'
})
export class DuosDetailComponent implements OnInit {
    duos: IDuos;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ duos }) => {
            this.duos = duos;
        });
    }

    previousState() {
        window.history.back();
    }
}
