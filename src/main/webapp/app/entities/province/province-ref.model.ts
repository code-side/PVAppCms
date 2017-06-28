import { BaseEntity } from './../../shared';

export class ProvinceRef implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public canton?: string
    ) {
    }
}
