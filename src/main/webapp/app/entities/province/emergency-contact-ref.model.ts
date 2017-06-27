import { BaseEntity } from './../../shared';

export class EmergencyContactRef implements BaseEntity{
  constructor(

    public name?: string,
    public type?: string,
    public contact?: string,
    public working_hours?: string
  ){

  }
}
