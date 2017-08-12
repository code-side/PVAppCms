import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Province } from './province.model';
import { ResponseWrapper, createRequestOption, createRequestOptionLang } from '../../shared';

@Injectable()
export class ProvinceService {

    private resourceUrl = 'api/provinces';

    constructor(private http: Http) { }

    create(province: Province): Observable<Province> {
        const copy = this.convert(province);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(province: Province): Observable<Province> {
        const copy = this.convert(province);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: string): Observable<Province> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }
    findAll(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOptionLang(req);
        console.log(options);
        return this.http.get(`${this.resourceUrl}`).map((res: Response) =>
            this.convertResponse(res));

    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        console.log(options);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: string): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        console.log( new ResponseWrapper(res.headers, jsonResponse, res.status));
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(province: Province): Province {
        const copy: Province = Object.assign({}, province);
        return copy;
    }
}
