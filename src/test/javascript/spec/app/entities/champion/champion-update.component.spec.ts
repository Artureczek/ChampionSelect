/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { ChampionUpdateComponent } from 'app/entities/champion/champion-update.component';
import { ChampionService } from 'app/entities/champion/champion.service';
import { Champion } from 'app/shared/model/champion.model';

describe('Component Tests', () => {
    describe('Champion Management Update Component', () => {
        let comp: ChampionUpdateComponent;
        let fixture: ComponentFixture<ChampionUpdateComponent>;
        let service: ChampionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [ChampionUpdateComponent]
            })
                .overrideTemplate(ChampionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChampionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChampionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Champion(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.champion = entity;
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
                    const entity = new Champion();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.champion = entity;
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
