import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ProvinceComponent } from './province.component';
import { ProvinceDetailComponent } from './province-detail.component';
import { ProvincePopupComponent } from './province-dialog.component';
import { ProvinceDeletePopupComponent } from './province-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ProvinceResolvePagingParams implements Resolve<any> {

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

export const provinceRoute: Routes = [
    {
        path: 'province',
        component: ProvinceComponent,
        resolve: {
            'pagingParams': ProvinceResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.province.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'province/:id',
        component: ProvinceDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.province.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const provincePopupRoute: Routes = [
    {
        path: 'province-new',
        component: ProvincePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.province.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'province/:id/edit',
        component: ProvincePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.province.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'province/:id/delete',
        component: ProvinceDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pvAppCmsApp.province.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
