import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PVAppUserComponent } from './pv-app-user.component';
import { PVAppUserDetailComponent } from './pv-app-user-detail.component';
import { PVAppUserPopupComponent } from './pv-app-user-dialog.component';
import { PVAppUserDeletePopupComponent } from './pv-app-user-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class PVAppUserResolvePagingParams implements Resolve<any> {

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

export const pVAppUserRoute: Routes = [
    {
        path: 'pv-app-user',
        component: PVAppUserComponent,
        resolve: {
            'pagingParams': PVAppUserResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.pVAppUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'pv-app-user/:id',
        component: PVAppUserDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.pVAppUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pVAppUserPopupRoute: Routes = [
    {
        path: 'pv-app-user-new',
        component: PVAppUserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.pVAppUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pv-app-user/:id/edit',
        component: PVAppUserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.pVAppUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pv-app-user/:id/delete',
        component: PVAppUserDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.pVAppUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
