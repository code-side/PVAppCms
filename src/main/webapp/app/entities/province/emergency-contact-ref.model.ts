import { BaseEntity } from './../../shared';

export class EmergencyContactRef implements BaseEntity {

  constructor(
    public name?: string,
    public type?: string,
    public contact?: string,
    public workingHours?: string,
    public coordinates?: string
  ) { }
}
