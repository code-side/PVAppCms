import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { PVAppUser } from './pv-app-user.model';
import { PVAppUserService } from './pv-app-user.service';

@Component({
    selector: 'jhi-pv-app-user-detail',
    templateUrl: './pv-app-user-detail.component.html'
})
export class PVAppUserDetailComponent implements OnInit, OnDestroy {

    pVAppUser: PVAppUser;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private pVAppUserService: PVAppUserService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPVAppUsers();
    }

    load(id) {
        this.pVAppUserService.find(id).subscribe((pVAppUser) => {
            this.pVAppUser = pVAppUser;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPVAppUsers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'pVAppUserListModification',
            (response) => this.load(this.pVAppUser.id)
        );
    }
}
