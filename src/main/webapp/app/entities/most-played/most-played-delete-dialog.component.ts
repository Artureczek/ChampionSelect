import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMostPlayed } from 'app/shared/model/most-played.model';
import { MostPlayedService } from './most-played.service';

@Component({
    selector: 'jhi-most-played-delete-dialog',
    templateUrl: './most-played-delete-dialog.component.html'
})
export class MostPlayedDeleteDialogComponent {
    mostPlayed: IMostPlayed;

    constructor(private mostPlayedService: MostPlayedService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mostPlayedService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mostPlayedListModification',
                content: 'Deleted an mostPlayed'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-most-played-delete-popup',
    template: ''
})
export class MostPlayedDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mostPlayed }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MostPlayedDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.mostPlayed = mostPlayed;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
