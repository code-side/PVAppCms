import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PvAppCmsTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TouristDestinationDetailComponent } from '../../../../../../main/webapp/app/entities/tourist-destination/tourist-destination-detail.component';
import { TouristDestinationService } from '../../../../../../main/webapp/app/entities/tourist-destination/tourist-destination.service';
import { TouristDestination } from '../../../../../../main/webapp/app/entities/tourist-destination/tourist-destination.model';

describe('Component Tests', () => {

    describe('TouristDestination Management Detail Component', () => {
        let comp: TouristDestinationDetailComponent;
        let fixture: ComponentFixture<TouristDestinationDetailComponent>;
        let service: TouristDestinationService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PvAppCmsTestModule],
                declarations: [TouristDestinationDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TouristDestinationService,
                    JhiEventManager
                ]
            }).overrideTemplate(TouristDestinationDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TouristDestinationDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TouristDestinationService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new TouristDestination('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.touristDestination).toEqual(jasmine.objectContaining({id:'aaa'}));
            });
        });
    });

});
