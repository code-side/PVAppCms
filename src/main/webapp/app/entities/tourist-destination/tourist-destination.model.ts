import { BaseEntity } from './../../shared';
import { ProvinceRef } from './../province/province-ref.model';
import { AttributeRef } from './attribute-ref.model';
import { CoordinatesRef } from './coordinates-ref.model';
import { PhotoRef } from './photo-ref.model';

export class TouristDestination implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public coordinates?: CoordinatesRef,
        public description?: string,
        public photos?: PhotoRef[],
        public address?: string,
        public province?: ProvinceRef,
        public attributes?: AttributeRef[],
        public reviews?: string,
    ) {
    }
}
