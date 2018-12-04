/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { ChampionTagDetailComponent } from 'app/entities/champion-tag/champion-tag-detail.component';
import { ChampionTag } from 'app/shared/model/champion-tag.model';

describe('Component Tests', () => {
    describe('ChampionTag Management Detail Component', () => {
        let comp: ChampionTagDetailComponent;
        let fixture: ComponentFixture<ChampionTagDetailComponent>;
        const route = ({ data: of({ championTag: new ChampionTag(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [ChampionTagDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ChampionTagDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChampionTagDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.championTag).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
