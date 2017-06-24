import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PvAppCmsTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TicoStopDetailComponent } from '../../../../../../main/webapp/app/entities/tico-stop/tico-stop-detail.component';
import { TicoStopService } from '../../../../../../main/webapp/app/entities/tico-stop/tico-stop.service';
import { TicoStop } from '../../../../../../main/webapp/app/entities/tico-stop/tico-stop.model';

describe('Component Tests', () => {

    describe('TicoStop Management Detail Component', () => {
        let comp: TicoStopDetailComponent;
        let fixture: ComponentFixture<TicoStopDetailComponent>;
        let service: TicoStopService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PvAppCmsTestModule],
                declarations: [TicoStopDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TicoStopService,
                    JhiEventManager
                ]
            }).overrideTemplate(TicoStopDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TicoStopDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TicoStopService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new TicoStop('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.ticoStop).toEqual(jasmine.objectContaining({id:'aaa'}));
            });
        });
    });

});
