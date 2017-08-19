import { BaseEntity } from './../../shared';
import { ProvinceRef } from './../province/province-ref.model';
import { CoordinatesRef } from '../tourist-destination/coordinates-ref.model';

export class TicoStop implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public historicalReview?: string,
        public coordinates?: CoordinatesRef,
        public photo?: string,
        public address?: string,
        public province?: ProvinceRef,
    ) {
      this.province = new ProvinceRef();
    }
}
