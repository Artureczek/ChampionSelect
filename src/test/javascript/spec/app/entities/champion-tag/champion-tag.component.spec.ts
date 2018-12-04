/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ChampionSelectTestModule } from '../../../test.module';
import { ChampionTagComponent } from 'app/entities/champion-tag/champion-tag.component';
import { ChampionTagService } from 'app/entities/champion-tag/champion-tag.service';
import { ChampionTag } from 'app/shared/model/champion-tag.model';

describe('Component Tests', () => {
    describe('ChampionTag Management Component', () => {
        let comp: ChampionTagComponent;
        let fixture: ComponentFixture<ChampionTagComponent>;
        let service: ChampionTagService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChampionSelectTestModule],
                declarations: [ChampionTagComponent],
                providers: []
            })
                .overrideTemplate(ChampionTagComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChampionTagComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChampionTagService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ChampionTag(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.championTags[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
