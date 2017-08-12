import { BaseEntity } from './../../shared';

export class PhotoRef implements BaseEntity {
    constructor(
        public id?: string,
        public url?: string,

    ) {
    }
}
