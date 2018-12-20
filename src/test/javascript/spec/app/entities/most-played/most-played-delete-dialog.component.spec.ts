/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ChampionSelectTestModule } from '../../../test.module';
import { MostPlayedDeleteDialogComponent } from 'app/entities/most-played/most-played-delete-dialog.component';
import { MostPlayedService } from 'app/entities/most-played/most-played.service';

describe('Component Tests', () => {
    describe('MostPlayed Management Delete Component', () => {
        let comp: MostPlayedDeleteDialogComponent;
        let fixture: ComponentFixture<MostPlayedDeleteDialogComponent>;
        let service: MostPlayedService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [MostPlayedDeleteDialogComponent]
            })
                .overrideTemplate(MostPlayedDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MostPlayedDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MostPlayedService);
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
