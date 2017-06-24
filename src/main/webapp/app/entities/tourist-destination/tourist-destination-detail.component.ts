import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { TouristDestination } from './tourist-destination.model';
import { TouristDestinationService } from './tourist-destination.service';

@Component({
    selector: 'jhi-tourist-destination-detail',
    templateUrl: './tourist-destination-detail.component.html'
})
export class TouristDestinationDetailComponent implements OnInit, OnDestroy {

    touristDestination: TouristDestination;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private touristDestinationService: TouristDestinationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTouristDestinations();
    }

    load(id) {
        this.touristDestinationService.find(id).subscribe((touristDestination) => {
            this.touristDestination = touristDestination;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTouristDestinations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'touristDestinationListModification',
            (response) => this.load(this.touristDestination.id)
        );
    }
}
