/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { SoloMemberUpdateComponent } from 'app/entities/solo-member/solo-member-update.component';
import { SoloMemberService } from 'app/entities/solo-member/solo-member.service';
import { SoloMember } from 'app/shared/model/solo-member.model';

describe('Component Tests', () => {
    describe('SoloMember Management Update Component', () => {
        let comp: SoloMemberUpdateComponent;
        let fixture: ComponentFixture<SoloMemberUpdateComponent>;
        let service: SoloMemberService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [SoloMemberUpdateComponent]
            })
                .overrideTemplate(SoloMemberUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SoloMemberUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SoloMemberService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SoloMember(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.soloMember = entity;
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
                    const entity = new SoloMember();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.soloMember = entity;
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
