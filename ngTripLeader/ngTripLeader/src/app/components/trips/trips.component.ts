import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-trips',
  templateUrl: './trips.component.html',
  styleUrls: ['./trips.component.scss']
})
export class TripsComponent implements OnInit {

  users!: User[];

  constructor(
    private userSvc: UserService
  ) { }

  ngOnInit(): void {
    this.loadUsers();
  }
  //testing connection to REST api
  loadUsers() {
    this.userSvc.index().subscribe(
      (success) => {
        this.users = success;
        console.log(this.users);
      },
      (fail) => {
        console.log('unable to get users');
      }
    );
  }

}
