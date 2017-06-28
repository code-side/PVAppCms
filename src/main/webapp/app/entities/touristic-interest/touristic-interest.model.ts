import { BaseEntity } from './../../shared';
import { ProvinceRef } from '../province/province-ref.model'

export class TouristicInterest implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public type?: string,
        public workingHours?: string,
        public contact?: string,
        public address?: string,
        public province?: ProvinceRef,
        public reviews?: string,
    ) {
    }
}
