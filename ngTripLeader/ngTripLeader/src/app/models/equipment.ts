export class Equipment {

  id: number;
  name: string;
  description: string;

  constructor(
    id?: number,
    name?: string,
    description?: string
  ){
    if(!id) {
      throw new Error("Unexpected error: Equipment missing id");
    }
    this.id = id;

    if(!name) {
      throw new Error("Unexpected error: Equipment missing name");
    }
    this.name = name;

    if(!description) {
      throw new Error("Unexpected error: Equipment missing description");
    }
    this.description = description;
  }

}



