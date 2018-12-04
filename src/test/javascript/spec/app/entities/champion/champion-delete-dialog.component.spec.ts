/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ChampionSelectTestModule } from '../../../test.module';
import { ChampionDeleteDialogComponent } from 'app/entities/champion/champion-delete-dialog.component';
import { ChampionService } from 'app/entities/champion/champion.service';

describe('Component Tests', () => {
    describe('Champion Management Delete Component', () => {
        let comp: ChampionDeleteDialogComponent;
        let fixture: ComponentFixture<ChampionDeleteDialogComponent>;
        let service: ChampionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [ChampionDeleteDialogComponent]
            })
                .overrideTemplate(ChampionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChampionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChampionService);
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
