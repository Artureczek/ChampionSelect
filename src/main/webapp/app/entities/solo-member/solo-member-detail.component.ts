import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISoloMember } from 'app/shared/model/solo-member.model';

@Component({
    selector: 'jhi-solo-member-detail',
    templateUrl: './solo-member-detail.component.html'
})
export class SoloMemberDetailComponent implements OnInit {
    soloMember: ISoloMember;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ soloMember }) => {
            this.soloMember = soloMember;
        });
    }

    previousState() {
        window.history.back();
    }
}
