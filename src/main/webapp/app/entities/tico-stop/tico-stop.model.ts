import { BaseEntity } from './../../shared';

export class TicoStop implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public historicalReview?: string,
        public coordinates?: string,
        public photo?: string,
        public address?: string,
        public province?: string,
    ) {
    }
}
