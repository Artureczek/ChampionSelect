/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ChampionSelectTestModule } from '../../../test.module';
import { SoloMemberDeleteDialogComponent } from 'app/entities/solo-member/solo-member-delete-dialog.component';
import { SoloMemberService } from 'app/entities/solo-member/solo-member.service';

describe('Component Tests', () => {
    describe('SoloMember Management Delete Component', () => {
        let comp: SoloMemberDeleteDialogComponent;
        let fixture: ComponentFixture<SoloMemberDeleteDialogComponent>;
        let service: SoloMemberService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [SoloMemberDeleteDialogComponent]
            })
                .overrideTemplate(SoloMemberDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SoloMemberDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SoloMemberService);
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
