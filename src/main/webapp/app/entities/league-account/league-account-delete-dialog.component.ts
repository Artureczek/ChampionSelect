import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILeagueAccount } from 'app/shared/model/league-account.model';
import { LeagueAccountService } from './league-account.service';

@Component({
    selector: 'jhi-league-account-delete-dialog',
    templateUrl: './league-account-delete-dialog.component.html'
})
export class LeagueAccountDeleteDialogComponent {
    leagueAccount: ILeagueAccount;

    constructor(
        private leagueAccountService: LeagueAccountService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.leagueAccountService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'leagueAccountListModification',
                content: 'Deleted an leagueAccount'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-league-account-delete-popup',
    template: ''
})
export class LeagueAccountDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ leagueAccount }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LeagueAccountDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.leagueAccount = leagueAccount;
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
