/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ChampionSelectTestModule } from '../../../test.module';
import { ChampionComponent } from 'app/entities/champion/champion.component';
import { ChampionService } from 'app/entities/champion/champion.service';
import { Champion } from 'app/shared/model/champion.model';

describe('Component Tests', () => {
    describe('Champion Management Component', () => {
        let comp: ChampionComponent;
        let fixture: ComponentFixture<ChampionComponent>;
        let service: ChampionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [ChampionComponent],
                providers: []
            })
                .overrideTemplate(ChampionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChampionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChampionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Champion(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.champions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
