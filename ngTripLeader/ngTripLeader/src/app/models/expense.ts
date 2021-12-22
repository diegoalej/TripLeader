import { Trip } from "./trip";
import { User } from "./user";

export class Expense {
  id: number;
  name: string;
  description: string;
  cost: number;
  date: string;
  trip: Trip;
  user: User;

  constructor(
    id?: number,
    name?: string,
    description?: string,
    cost?: number,
    date?: string,
    trip?: Trip,
    user?: User
  ){

    if (!id) {
      throw new Error("Unexpected error: Expense missing id");
    }
    this.id = id;

    if (!name) {
      throw new Error("Unexpected error: Expense missing name");
    }
    this.name = name;

    if (!description) {
      throw new Error("Unexpected error: Expense missing description");
    }
    this.description = description;

    if (!cost) {
      throw new Error("Unexpected error: Expense missing cost");
    }
    this.cost = cost;

    if (!date) {
      throw new Error("Unexpected error: Expense missing date");
    }
    this.date = date;

    if (!trip) {
      throw new Error("Unexpected error: Expense missing trip");
    }
    this.trip = trip;

    if (!user) {
      throw new Error("Unexpected error: Expense missing user");
    }
    this.user = user;

  }
}
