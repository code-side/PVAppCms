import { BaseEntity } from './../../shared';

export class CoordinatesRef implements BaseEntity {
    constructor(
        public latitude?: string,
        public longitude?: string

    ) {
    }
}
