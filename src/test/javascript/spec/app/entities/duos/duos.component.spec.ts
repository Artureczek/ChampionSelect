/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ChampionSelectTestModule } from '../../../test.module';
import { DuosComponent } from 'app/entities/duos/duos.component';
import { DuosService } from 'app/entities/duos/duos.service';
import { Duos } from 'app/shared/model/duos.model';

describe('Component Tests', () => {
    describe('Duos Management Component', () => {
        let comp: DuosComponent;
        let fixture: ComponentFixture<DuosComponent>;
        let service: DuosService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [DuosComponent],
                providers: []
            })
                .overrideTemplate(DuosComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DuosComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DuosService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Duos(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.duos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
