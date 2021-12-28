import { Equipment } from "./equipment";

export class TripEquipment {
  id: number;
  equipment: Equipment;
  active: boolean;
  amount: number;

  constructor(
    id?: number,
    equipment?: Equipment,
    active?: boolean,
    amount?: number
  ){
    if (!id) {
      throw new Error("Unexpected error: Trip Equipment missing id");
  }
    this.id = id;

    if (!equipment) {
      throw new Error("Unexpected error: Trip Equipment missing equipment");
  }
    this.equipment = equipment;

    if (!active) {
      throw new Error("Unexpected error: Trip Equipment missing active bool");
  }
    this.active = active;

    if (!amount) {
      throw new Error("Unexpected error: Trip Equipment missing amount");
  }
    this.amount = amount;


  }

}

