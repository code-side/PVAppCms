import { BaseEntity } from './../../shared';

export class AttributeRef implements BaseEntity {
    constructor(
        public name?: string,
        public value?: string
    ) {
    }
}
