/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ChampionSelectTestModule } from '../../../test.module';
import { LeagueAccountDeleteDialogComponent } from 'app/entities/league-account/league-account-delete-dialog.component';
import { LeagueAccountService } from 'app/entities/league-account/league-account.service';

describe('Component Tests', () => {
    describe('LeagueAccount Management Delete Component', () => {
        let comp: LeagueAccountDeleteDialogComponent;
        let fixture: ComponentFixture<LeagueAccountDeleteDialogComponent>;
        let service: LeagueAccountService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [LeagueAccountDeleteDialogComponent]
            })
                .overrideTemplate(LeagueAccountDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LeagueAccountDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LeagueAccountService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
