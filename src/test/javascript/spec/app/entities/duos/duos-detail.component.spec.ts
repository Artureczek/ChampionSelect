/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { DuosDetailComponent } from 'app/entities/duos/duos-detail.component';
import { Duos } from 'app/shared/model/duos.model';

describe('Component Tests', () => {
    describe('Duos Management Detail Component', () => {
        let comp: DuosDetailComponent;
        let fixture: ComponentFixture<DuosDetailComponent>;
        const route = ({ data: of({ duos: new Duos(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [DuosDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DuosDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DuosDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.duos).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
