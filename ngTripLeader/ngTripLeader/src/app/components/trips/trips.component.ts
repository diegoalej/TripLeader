import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { Trip } from 'src/app/models/trip';
import { TripService } from 'src/app/services/trip.service';
import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-trips',
  templateUrl: './trips.component.html',
  styleUrls: ['./trips.component.scss']
})
export class TripsComponent implements OnInit {

  users: User[] = [];
  tripsCreated: Trip[] = [];
  tripsMemberOf: Trip[] = [];
  userId: number = 0;
  createTripForm = false;

  constructor(
    private tripSvc: TripService,
    private auth: AuthService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.loadTrips();
  }
  //This will subscribe to get Users' created and trips
  //they are members of
  loadTrips() {
    //Get user Id
    this.userId = Number(this.auth.getCurrentUserId());
    //Get trips created
    this.tripSvc.getUserTrips(this.userId).subscribe(
      (success) => {
        this.tripsCreated = success;
        console.log(this.tripsCreated);
      },
      (fail) => {
        console.log('unable to get userTrips');
      }
    );
    //Get Trips user is member of
    this.tripSvc.getTripsUserIsIn(this.userId).subscribe(
      (success) => {
        this.tripsMemberOf = success;
        console.log(this.tripsMemberOf);
      },
      (fail) => {
        console.log('unable to get userTrips');
      }
    );
  }

  showTripForms() {
    this.createTripForm = !this.createTripForm;
  }

}
