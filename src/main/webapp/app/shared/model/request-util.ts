import { URLSearchParams, BaseRequestOptions } from '@angular/http';

export const createRequestOption = (req?: any): BaseRequestOptions => {
    const options: BaseRequestOptions = new BaseRequestOptions();
    if (req) {
        const params: URLSearchParams = new URLSearchParams();
        params.set('page', req.page);
        params.set('size', req.size);
        if (req.sort) {
            params.paramsMap.set('sort', req.sort);
        }
        params.set('query', req.query);

        options.params = params;
    }
    return options;
};

export const createRequestOptionLang = (req?: any): BaseRequestOptions => {

    const options: BaseRequestOptions = new BaseRequestOptions();
    console.log(options);
    if (req) {
        const params: URLSearchParams = new URLSearchParams();
        params.set('lang', req.lang);
        params.set('query', req.query);

        options.params = params;
    }
    return options;
};
