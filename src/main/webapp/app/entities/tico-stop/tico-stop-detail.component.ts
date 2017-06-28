import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { TicoStop } from './tico-stop.model';
import { TicoStopService } from './tico-stop.service';

@Component({
    selector: 'jhi-tico-stop-detail',
    templateUrl: './tico-stop-detail.component.html'
})
export class TicoStopDetailComponent implements OnInit, OnDestroy {

    ticoStop: TicoStop;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    NO_IMAGE: String = 'http://www.jordans.com/~/media/jordans%20redesign/no-image-found.ashx?h=275&la=en&w=275&hash=F87BC23F17E37D57E2A0B1CC6E2E3EEE312AAD5B';

    constructor(
        private eventManager: JhiEventManager,
        private ticoStopService: TicoStopService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTicoStops();
    }

    load(id) {
        this.ticoStopService.find(id).subscribe((ticoStop) => {
            this.ticoStop = ticoStop;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTicoStops() {
        this.eventSubscriber = this.eventManager.subscribe(
            'ticoStopListModification',
            (response) => this.load(this.ticoStop.id)
        );
    }
}
