import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { UserService } from './user.service';
import { Trip } from '../models/trip';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TripService {
  [x: string]: any;
  private url = environment.baseUrl + 'api/trips';
  private trip: Trip[] = [];


  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  public getUserTrips(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Trip[]>(`${this.url}/creator/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('TripService: error retrieving userTrips');
      })
    )
  }

  public getTripsUserIsIn(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Trip[]>(`${this.url}/member/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('TripService: error retrieving userTrips');
      })
    )
  }

  public create(trip: Trip) {
     const httpOptions = this.getHttpOptions();
     return this.http.post<Trip>(this.url, trip, httpOptions).pipe(
       catchError((err: any) => {
         console.log(err);
         return throwError('TripService.create: error creating entry: ' + err);
       })
     );
   }

  private getHttpOptions() {
    const credentials = this.auth.getCredentials();
    let httpOptions = {};
    if (credentials) {

      httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-requested-with': 'XMLHttpRequest',
          'Authorization': `Basic ${credentials}`
        })
      };
    } else {
      httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-requested-with': 'XMLHttpRequest'
        })
      };
    }
    return httpOptions;
  }
}
