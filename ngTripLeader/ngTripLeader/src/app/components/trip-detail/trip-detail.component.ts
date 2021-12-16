import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Trip } from 'src/app/models/trip';
import { TripService } from 'src/app/services/trip.service';

@Component({
  selector: 'app-trip-detail',
  templateUrl: './trip-detail.component.html',
  styleUrls: ['./trip-detail.component.scss']
})
export class TripDetailComponent implements OnInit {

  urlParam = Number(this.route.snapshot.paramMap.get('id'));
  trip: Trip = {} as Trip;

  constructor(
    private route: ActivatedRoute,
    private tripSvc: TripService
  ) { }

  ngOnInit(  ): void {
    this.loadTrip(this.urlParam);
  }

  loadTrip(tripId: number){
    this.tripSvc.getTripById(tripId).subscribe(
      (data) => {
        return this.trip = data;
        // this.populateReviewRatings(this.urlId);
        // this.populateReviews(this.urlId);
        console.log(this.trip);

        // this.editLoc = null;
      },
      (error) => {
        console.log('error in getting trip with id');
        console.log(error);
      }
    );
  }

}
