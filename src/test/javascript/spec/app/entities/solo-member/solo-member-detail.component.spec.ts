/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { SoloMemberDetailComponent } from 'app/entities/solo-member/solo-member-detail.component';
import { SoloMember } from 'app/shared/model/solo-member.model';

describe('Component Tests', () => {
    describe('SoloMember Management Detail Component', () => {
        let comp: SoloMemberDetailComponent;
        let fixture: ComponentFixture<SoloMemberDetailComponent>;
        const route = ({ data: of({ soloMember: new SoloMember(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [SoloMemberDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SoloMemberDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SoloMemberDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.soloMember).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
