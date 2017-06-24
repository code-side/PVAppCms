import { BaseEntity } from './../../shared';

export class Province implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public coordinates?: string,
        public history?: string,
        public culture?: string,
        public photo?: string,
        public cantons?: string,
        public emergencyContacts?: string,
    ) {
    }
}
