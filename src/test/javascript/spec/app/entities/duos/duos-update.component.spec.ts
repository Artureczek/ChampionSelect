/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { DuosUpdateComponent } from 'app/entities/duos/duos-update.component';
import { DuosService } from 'app/entities/duos/duos.service';
import { Duos } from 'app/shared/model/duos.model';

describe('Component Tests', () => {
    describe('Duos Management Update Component', () => {
        let comp: DuosUpdateComponent;
        let fixture: ComponentFixture<DuosUpdateComponent>;
        let service: DuosService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [DuosUpdateComponent]
            })
                .overrideTemplate(DuosUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DuosUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DuosService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Duos(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.duos = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Duos();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.duos = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
