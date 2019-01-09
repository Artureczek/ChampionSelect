/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ChampionSelectTestModule } from '../../../test.module';
import { MostPlayedComponent } from 'app/entities/most-played/most-played.component';
import { MostPlayedService } from 'app/entities/most-played/most-played.service';
import { MostPlayed } from 'app/shared/model/most-played.model';

describe('Component Tests', () => {
    describe('MostPlayed Management Component', () => {
        let comp: MostPlayedComponent;
        let fixture: ComponentFixture<MostPlayedComponent>;
        let service: MostPlayedService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [MostPlayedComponent],
                providers: []
            })
                .overrideTemplate(MostPlayedComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MostPlayedComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MostPlayedService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new MostPlayed(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.mostPlayeds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
