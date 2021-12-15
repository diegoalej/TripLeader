import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { Location } from 'src/app/models/location'
import { UserService } from 'src/app/services/user.service';
import { LocationService } from 'src/app/services/location.service';
import { TripService } from 'src/app/services/trip.service';
import { Trip } from 'src/app/models/trip';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trip-form',
  templateUrl: './trip-form.component.html',
  styleUrls: ['./trip-form.component.scss']
})
export class TripFormComponent implements OnInit {

  locations: Location[] = [];

  constructor(
    private auth: AuthService,
    private locSvc: LocationService,
    private tripSvc: TripService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadLocations()
  }

  //This will get all Locations for user to select
  loadLocations() {
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
    const newTrip: Trip = form.value;
    newTrip.locationEnd = this.locations[form.value.locationEnd];
    newTrip.locationStart = this.locations[form.value.locationStart];

    console.log( "inside createTrip" + (JSON.stringify(newTrip)));
    this.tripSvc.create(newTrip).subscribe(
      (yay) => {
        console.log('TripFormComponent.create(): Location created.');
        this.router.navigateByUrl('./trips');
        // this.tripSvc.show(yay.id);
        // this.load(yay.id);
      },
      (nay) => {
        console.error('TripFormComponent.create(): ERROR.');
        console.error(nay);
      }
    );
  }
}
