import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PvAppCmsTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProvinceDetailComponent } from '../../../../../../main/webapp/app/entities/province/province-detail.component';
import { ProvinceService } from '../../../../../../main/webapp/app/entities/province/province.service';
import { Province } from '../../../../../../main/webapp/app/entities/province/province.model';

describe('Component Tests', () => {

    describe('Province Management Detail Component', () => {
        let comp: ProvinceDetailComponent;
        let fixture: ComponentFixture<ProvinceDetailComponent>;
        let service: ProvinceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PvAppCmsTestModule],
                declarations: [ProvinceDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProvinceService,
                    JhiEventManager
                ]
            }).overrideTemplate(ProvinceDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProvinceDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvinceService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Province('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.province).toEqual(jasmine.objectContaining({id:'aaa'}));
            });
        });
    });

});
