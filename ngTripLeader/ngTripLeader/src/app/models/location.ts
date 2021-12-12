
export class Location {

  id: number;
  name: string;
  description: string;
  address: string;
  city: string;
  zip: number;
  latitude: string;
  longitude: string;

  constructor(
    id?: number,
    name?: string,
    description?: string,
    address?: string,
    city?: string,
    zip?: number,
    latitude?: string,
    longitude?: string

  ){

    if (!id) {
      throw new Error("Unexpected error: Location missing id");
  }
    this.id = id;

    if (!name) {
      throw new Error("Unexpected error: Location missing name");
  }
    this.name = name;

    if (!description) {
      throw new Error("Unexpected error: Location missing description");
  }
    this.description = description;

    if (!address) {
      throw new Error("Unexpected error: Location missing address");
  }
    this.address = address;

    if (!city) {
      throw new Error("Unexpected error: Location missing city");
  }
    this.city = city;

    if (!zip) {
      throw new Error("Unexpected error: Location missing zip");
  }
    this.zip = zip;

    if (!latitude) {
      throw new Error("Unexpected error: Location missing latitude");
  }
    this.latitude = latitude;

    if (!longitude) {
      throw new Error("Unexpected error: Location missing longitude");
  }
    this.longitude= longitude;
  }



}
