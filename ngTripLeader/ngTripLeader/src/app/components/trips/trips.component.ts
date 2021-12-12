import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { Trip } from 'src/app/models/trip';
import { TripService } from 'src/app/services/trip.service';
import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-trips',
  templateUrl: './trips.component.html',
  styleUrls: ['./trips.component.scss']
})
export class TripsComponent implements OnInit {

  users: User[] = [];
  userTrips: Trip[] = [];
  userId: number = 0;


  constructor(
    private userSvc: UserService,
    private tripSvc: TripService,
    private auth: AuthService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.loadTrips();
  }
  //testing connection to REST api
  loadTrips() {

    this.userId = Number(this.auth.getCurrentUserId());

    this.tripSvc.getUserTrips(this.userId).subscribe(
      (success) => {
        this.userTrips = success;
        console.log(this.userTrips);
      },
      (fail) => {
        console.log('unable to get userTrips');
      }
    );
  }

}
