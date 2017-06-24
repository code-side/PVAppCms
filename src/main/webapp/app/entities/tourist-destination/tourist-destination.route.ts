import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TouristDestinationComponent } from './tourist-destination.component';
import { TouristDestinationDetailComponent } from './tourist-destination-detail.component';
import { TouristDestinationPopupComponent } from './tourist-destination-dialog.component';
import { TouristDestinationDeletePopupComponent } from './tourist-destination-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class TouristDestinationResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const touristDestinationRoute: Routes = [
    {
        path: 'tourist-destination',
        component: TouristDestinationComponent,
        resolve: {
            'pagingParams': TouristDestinationResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.touristDestination.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tourist-destination/:id',
        component: TouristDestinationDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.touristDestination.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const touristDestinationPopupRoute: Routes = [
    {
        path: 'tourist-destination-new',
        component: TouristDestinationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.touristDestination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tourist-destination/:id/edit',
        component: TouristDestinationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.touristDestination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tourist-destination/:id/delete',
        component: TouristDestinationDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.touristDestination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
