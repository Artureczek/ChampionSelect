/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { LeagueAccountDetailComponent } from 'app/entities/league-account/league-account-detail.component';
import { LeagueAccount } from 'app/shared/model/league-account.model';

describe('Component Tests', () => {
    describe('LeagueAccount Management Detail Component', () => {
        let comp: LeagueAccountDetailComponent;
        let fixture: ComponentFixture<LeagueAccountDetailComponent>;
        const route = ({ data: of({ leagueAccount: new LeagueAccount(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [LeagueAccountDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LeagueAccountDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LeagueAccountDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.leagueAccount).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
