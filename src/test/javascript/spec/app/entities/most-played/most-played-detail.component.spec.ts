/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { MostPlayedDetailComponent } from 'app/entities/most-played/most-played-detail.component';
import { MostPlayed } from 'app/shared/model/most-played.model';

describe('Component Tests', () => {
    describe('MostPlayed Management Detail Component', () => {
        let comp: MostPlayedDetailComponent;
        let fixture: ComponentFixture<MostPlayedDetailComponent>;
        const route = ({ data: of({ mostPlayed: new MostPlayed(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [MostPlayedDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MostPlayedDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MostPlayedDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.mostPlayed).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
