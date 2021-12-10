export class User {

  id: number;
  username: string;
  password: string;
  email: string;
  active: boolean;
  role: string;
  dateCreated: string;
  imgUrl: string;
  dateStart: string;

  constructor(
    id?: number,
    username?: string,
    password?: string,
    email?: string,
    active?: boolean,
    role?: string,
    dateCreated?: string,
    imgUrl?: string,
    dateStart?: string
  ){
    if (!id) {
      throw new Error("Unexpected error: User missing id");
  }
    this.id = id;

    if (!username) {
      throw new Error("Unexpected error: User missing username");
  }
  this.username = username;

  if (!password) {
    throw new Error("Unexpected error: User missing password");
  }
  this.password = password;

  if (!email) {
    throw new Error("Unexpected error: User missing email");
  }
  this.email = email;

  if (!active) {
    throw new Error("Unexpected error: User missing active bool");
  }
  this.active = active;

  if (!role) {
    throw new Error("Unexpected error: User missing role");
  }
  this.role = role;

  if (!dateCreated) {
    throw new Error("Unexpected error: User missing date created");
  }
  this.dateCreated = dateCreated;

  if (!imgUrl) {
    throw new Error("Unexpected error: User missing img url");
  }
  this.imgUrl = imgUrl;

  if (!dateStart) {
    throw new Error("Unexpected error: User missing date start");
  }
  this.dateStart = dateStart;
  }
}
