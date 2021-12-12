import { User } from "./user";
import { Location } from "./location";
export class Trip {

  id: number;
  dateStart: string;
  dateEnd: string;
  name: string;
  creator: User;
  description: string;
  locationStart: Location;
  locationEnd: Location;

  constructor(
    id?: number,
    dateStart?: string,
    dateEnd?: string,
    name?: string,
    creator?: User,
    description?: string,
    locationStart?: Location,
    locationEnd?: Location
  ){

    if (!id) {
      throw new Error("Unexpected error: Trip missing id");
  }
    this.id = id;

    if(!dateStart) {
      throw new Error("Unexpecter error: Trip missing dateStart")
    }
    this.dateStart = dateStart;

    if(!dateEnd) {
      throw new Error("Unexpecter error: Trip missing dateEnd")
    }
    this.dateEnd = dateEnd;

    if(!name) {
      throw new Error("Unexpecter error: Trip missing name")
    }
    this.name = name;

    if(!creator) {
      throw new Error("Unexpecter error: Trip missing creator")
    }
    this.creator = creator;

    if(!description) {
      throw new Error("Unexpecter error: Trip missing description")
    }
    this.description = description;

    if(!locationStart) {
      throw new Error("Unexpecter error: Trip missing description")
    }
    this.locationStart = locationStart;

    if(!locationEnd) {
      throw new Error("Unexpecter error: Trip missing description")
    }
    this.locationEnd = locationEnd;

  }

}

