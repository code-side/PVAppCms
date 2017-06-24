import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { TouristicInterest } from './touristic-interest.model';
import { TouristicInterestService } from './touristic-interest.service';

@Component({
    selector: 'jhi-touristic-interest-detail',
    templateUrl: './touristic-interest-detail.component.html'
})
export class TouristicInterestDetailComponent implements OnInit, OnDestroy {

    touristicInterest: TouristicInterest;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private touristicInterestService: TouristicInterestService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTouristicInterests();
    }

    load(id) {
        this.touristicInterestService.find(id).subscribe((touristicInterest) => {
            this.touristicInterest = touristicInterest;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTouristicInterests() {
        this.eventSubscriber = this.eventManager.subscribe(
            'touristicInterestListModification',
            (response) => this.load(this.touristicInterest.id)
        );
    }
}
