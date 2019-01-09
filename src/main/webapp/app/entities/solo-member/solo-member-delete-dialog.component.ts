import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISoloMember } from 'app/shared/model/solo-member.model';
import { SoloMemberService } from './solo-member.service';

@Component({
    selector: 'jhi-solo-member-delete-dialog',
    templateUrl: './solo-member-delete-dialog.component.html'
})
export class SoloMemberDeleteDialogComponent {
    soloMember: ISoloMember;

    constructor(private soloMemberService: SoloMemberService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.soloMemberService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'soloMemberListModification',
                content: 'Deleted an soloMember'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-solo-member-delete-popup',
    template: ''
})
export class SoloMemberDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ soloMember }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SoloMemberDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.soloMember = soloMember;
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
