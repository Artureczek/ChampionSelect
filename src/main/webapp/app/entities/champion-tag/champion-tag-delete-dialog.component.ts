import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChampionTag } from 'app/shared/model/champion-tag.model';
import { ChampionTagService } from './champion-tag.service';

@Component({
    selector: 'jhi-champion-tag-delete-dialog',
    templateUrl: './champion-tag-delete-dialog.component.html'
})
export class ChampionTagDeleteDialogComponent {
    championTag: IChampionTag;

    constructor(
        private championTagService: ChampionTagService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.championTagService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'championTagListModification',
                content: 'Deleted an championTag'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-champion-tag-delete-popup',
    template: ''
})
export class ChampionTagDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ championTag }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ChampionTagDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.championTag = championTag;
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
