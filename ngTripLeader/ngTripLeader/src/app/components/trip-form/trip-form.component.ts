import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { Location } from 'src/app/models/location'
import { UserService } from 'src/app/services/user.service';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-trip-form',
  templateUrl: './trip-form.component.html',
  styleUrls: ['./trip-form.component.scss']
})
export class TripFormComponent implements OnInit {

  locations: Location[] = [];
  // userId: number = 0;

  constructor(
    private auth: AuthService,
    private locSvc: LocationService
  ) { }

  ngOnInit(): void {
    this.loadLocations()
  }

   //This will subscribe to get Users' created and trips
  //they are members of
  loadLocations() {
    // //Get user Id
    // this.userId = Number(this.auth.getCurrentUserId());
    //Get trips created
    this.locSvc.getAllLocations().subscribe(
      (success) => {
        this.locations = success;
        console.log(this.locations);
      },
      (fail) => {
        console.log('unable to get locations');
      }
    );

  }

  createTrip(form: NgForm){
    console.log( "inside createTrip" + form );
  }
}
