/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ChampionSelectTestModule } from '../../../test.module';
import { LeagueAccountUpdateComponent } from 'app/entities/league-account/league-account-update.component';
import { LeagueAccountService } from 'app/entities/league-account/league-account.service';
import { LeagueAccount } from 'app/shared/model/league-account.model';

describe('Component Tests', () => {
    describe('LeagueAccount Management Update Component', () => {
        let comp: LeagueAccountUpdateComponent;
        let fixture: ComponentFixture<LeagueAccountUpdateComponent>;
        let service: LeagueAccountService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [LeagueAccountUpdateComponent]
            })
                .overrideTemplate(LeagueAccountUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LeagueAccountUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LeagueAccountService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new LeagueAccount(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.leagueAccount = entity;
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
                    const entity = new LeagueAccount();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.leagueAccount = entity;
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
