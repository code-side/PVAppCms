import { BaseEntity } from './../../shared';

export class TouristDestination implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public coordinates?: string,
        public description?: string,
        public photos?: string,
        public address?: string,
        public province?: string,
        public attributes?: string,
        public reviews?: string,
    ) {
    }
}
