import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TouristicInterestComponent } from './touristic-interest.component';
import { TouristicInterestDetailComponent } from './touristic-interest-detail.component';
import { TouristicInterestPopupComponent } from './touristic-interest-dialog.component';
import { TouristicInterestDeletePopupComponent } from './touristic-interest-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class TouristicInterestResolvePagingParams implements Resolve<any> {

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

export const touristicInterestRoute: Routes = [
    {
        path: 'touristic-interest',
        component: TouristicInterestComponent,
        resolve: {
            'pagingParams': TouristicInterestResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.touristicInterest.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'touristic-interest/:id',
        component: TouristicInterestDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.touristicInterest.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const touristicInterestPopupRoute: Routes = [
    {
        path: 'touristic-interest-new',
        component: TouristicInterestPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.touristicInterest.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'touristic-interest/:id/edit',
        component: TouristicInterestPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.touristicInterest.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'touristic-interest/:id/delete',
        component: TouristicInterestDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.touristicInterest.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
