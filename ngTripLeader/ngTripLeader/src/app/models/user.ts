
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
    id: number,
    username: string,
    password: string,
    email: string,
    active: boolean,
    role: string,
    dateCreated: string,
    imgUrl: string,
    dateStart: string
  ){
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.active = active;
    this.role = role;
    this.dateCreated = dateCreated;
    this.imgUrl = imgUrl;
    this.dateStart = dateStart;
  }
}
