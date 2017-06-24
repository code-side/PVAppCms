import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PvAppCmsTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PVAppUserDetailComponent } from '../../../../../../main/webapp/app/entities/p-v-app-user/pv-app-user-detail.component';
import { PVAppUserService } from '../../../../../../main/webapp/app/entities/p-v-app-user/pv-app-user.service';
import { PVAppUser } from '../../../../../../main/webapp/app/entities/p-v-app-user/pv-app-user.model';

describe('Component Tests', () => {

    describe('PVAppUser Management Detail Component', () => {
        let comp: PVAppUserDetailComponent;
        let fixture: ComponentFixture<PVAppUserDetailComponent>;
        let service: PVAppUserService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PvAppCmsTestModule],
                declarations: [PVAppUserDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PVAppUserService,
                    JhiEventManager
                ]
            }).overrideTemplate(PVAppUserDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PVAppUserDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PVAppUserService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new PVAppUser('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.pVAppUser).toEqual(jasmine.objectContaining({id:'aaa'}));
            });
        });
    });

});
