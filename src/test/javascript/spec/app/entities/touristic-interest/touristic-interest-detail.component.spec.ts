import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PvAppCmsTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TouristicInterestDetailComponent } from '../../../../../../main/webapp/app/entities/touristic-interest/touristic-interest-detail.component';
import { TouristicInterestService } from '../../../../../../main/webapp/app/entities/touristic-interest/touristic-interest.service';
import { TouristicInterest } from '../../../../../../main/webapp/app/entities/touristic-interest/touristic-interest.model';

describe('Component Tests', () => {

    describe('TouristicInterest Management Detail Component', () => {
        let comp: TouristicInterestDetailComponent;
        let fixture: ComponentFixture<TouristicInterestDetailComponent>;
        let service: TouristicInterestService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PvAppCmsTestModule],
                declarations: [TouristicInterestDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TouristicInterestService,
                    JhiEventManager
                ]
            }).overrideTemplate(TouristicInterestDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TouristicInterestDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TouristicInterestService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new TouristicInterest('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.touristicInterest).toEqual(jasmine.objectContaining({id:'aaa'}));
            });
        });
    });

});
