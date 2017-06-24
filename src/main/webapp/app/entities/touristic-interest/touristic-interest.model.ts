import { BaseEntity } from './../../shared';

export class TouristicInterest implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public type?: string,
        public workingHours?: string,
        public contact?: string,
        public address?: string,
        public province?: string,
        public reviews?: string,
    ) {
    }
}
