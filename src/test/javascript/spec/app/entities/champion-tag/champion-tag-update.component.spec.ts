/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { ChampionTagUpdateComponent } from 'app/entities/champion-tag/champion-tag-update.component';
import { ChampionTagService } from 'app/entities/champion-tag/champion-tag.service';
import { ChampionTag } from 'app/shared/model/champion-tag.model';

describe('Component Tests', () => {
    describe('ChampionTag Management Update Component', () => {
        let comp: ChampionTagUpdateComponent;
        let fixture: ComponentFixture<ChampionTagUpdateComponent>;
        let service: ChampionTagService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [ChampionTagUpdateComponent]
            })
                .overrideTemplate(ChampionTagUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChampionTagUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChampionTagService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ChampionTag(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.championTag = entity;
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
                    const entity = new ChampionTag();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.championTag = entity;
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
