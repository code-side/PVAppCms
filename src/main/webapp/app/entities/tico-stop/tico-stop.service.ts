import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { TicoStop } from './tico-stop.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TicoStopService {

    private resourceUrl = 'api/tico-stops';

    constructor(private http: Http) { }

    create(ticoStop: TicoStop): Observable<TicoStop> {
        const copy = this.convert(ticoStop);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(ticoStop: TicoStop): Observable<TicoStop> {
        const copy = this.convert(ticoStop);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: string): Observable<TicoStop> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
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
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(ticoStop: TicoStop): TicoStop {
        const copy: TicoStop = Object.assign({}, ticoStop);
        return copy;
    }
}
