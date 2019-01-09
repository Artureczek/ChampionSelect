/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { MostPlayedUpdateComponent } from 'app/entities/most-played/most-played-update.component';
import { MostPlayedService } from 'app/entities/most-played/most-played.service';
import { MostPlayed } from 'app/shared/model/most-played.model';

describe('Component Tests', () => {
    describe('MostPlayed Management Update Component', () => {
        let comp: MostPlayedUpdateComponent;
        let fixture: ComponentFixture<MostPlayedUpdateComponent>;
        let service: MostPlayedService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [MostPlayedUpdateComponent]
            })
                .overrideTemplate(MostPlayedUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MostPlayedUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MostPlayedService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MostPlayed(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mostPlayed = entity;
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
                    const entity = new MostPlayed();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mostPlayed = entity;
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
