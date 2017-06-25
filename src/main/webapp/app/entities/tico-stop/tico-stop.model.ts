import { BaseEntity } from './../../shared';
import { ProvinceRef } from './../province/province-ref.model';

export class TicoStop implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public historicalReview?: string,
        public coordinates?: string,
        public photo?: string,
        public address?: string,
        public province?: ProvinceRef,
    ) {
    }
}
