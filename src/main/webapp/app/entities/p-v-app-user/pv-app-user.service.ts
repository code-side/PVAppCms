import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { PVAppUser } from './pv-app-user.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class PVAppUserService {

    private resourceUrl = 'api/p-v-app-users';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(pVAppUser: PVAppUser): Observable<PVAppUser> {
        const copy = this.convert(pVAppUser);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(pVAppUser: PVAppUser): Observable<PVAppUser> {
        const copy = this.convert(pVAppUser);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: string): Observable<PVAppUser> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: string): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.registrationDate = this.dateUtils
            .convertLocalDateFromServer(entity.registrationDate);
        entity.birthday = this.dateUtils
            .convertLocalDateFromServer(entity.birthday);
    }

    private convert(pVAppUser: PVAppUser): PVAppUser {
        const copy: PVAppUser = Object.assign({}, pVAppUser);
        copy.registrationDate = this.dateUtils
            .convertLocalDateToServer(pVAppUser.registrationDate);
        copy.birthday = this.dateUtils
            .convertLocalDateToServer(pVAppUser.birthday);
        return copy;
    }
}
