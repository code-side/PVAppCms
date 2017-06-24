import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TicoStopComponent } from './tico-stop.component';
import { TicoStopDetailComponent } from './tico-stop-detail.component';
import { TicoStopPopupComponent } from './tico-stop-dialog.component';
import { TicoStopDeletePopupComponent } from './tico-stop-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class TicoStopResolvePagingParams implements Resolve<any> {

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

export const ticoStopRoute: Routes = [
    {
        path: 'tico-stop',
        component: TicoStopComponent,
        resolve: {
            'pagingParams': TicoStopResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.ticoStop.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tico-stop/:id',
        component: TicoStopDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.ticoStop.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ticoStopPopupRoute: Routes = [
    {
        path: 'tico-stop-new',
        component: TicoStopPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.ticoStop.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tico-stop/:id/edit',
        component: TicoStopPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.ticoStop.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tico-stop/:id/delete',
        component: TicoStopDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.ticoStop.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
