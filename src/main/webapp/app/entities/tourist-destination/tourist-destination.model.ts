import { BaseEntity } from './../../shared';
import { ProvinceRef } from './../province/province-ref.model';
import { AttributeRef } from './attribute-ref.model';

export class TouristDestination implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public coordinates?: string,
        public description?: string,
        public photos?: string,
        public address?: string,
        public province?: ProvinceRef,
        public attributes?: AttributeRef[],
        public reviews?: string,
    ) {
    }
}
