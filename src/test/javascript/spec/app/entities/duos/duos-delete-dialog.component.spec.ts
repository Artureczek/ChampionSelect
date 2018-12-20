/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ChampionSelectTestModule } from '../../../test.module';
import { DuosDeleteDialogComponent } from 'app/entities/duos/duos-delete-dialog.component';
import { DuosService } from 'app/entities/duos/duos.service';

describe('Component Tests', () => {
    describe('Duos Management Delete Component', () => {
        let comp: DuosDeleteDialogComponent;
        let fixture: ComponentFixture<DuosDeleteDialogComponent>;
        let service: DuosService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [DuosDeleteDialogComponent]
            })
                .overrideTemplate(DuosDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DuosDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DuosService);
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
