import { BaseEntity } from './../../shared';

export class PVAppUser implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
        public email?: string,
        public password?: string,
        public registrationDate?: any,
        public birthday?: any,
        public nationality?: string,
        public gender?: string,
        public photo?: string,
        public status?: number,
        public favoriteList?: string,
        public achievements?: string,
    ) {
    }
}
