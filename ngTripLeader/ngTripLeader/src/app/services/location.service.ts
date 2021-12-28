import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Location } from '../models/location';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  [x: string]: any;
  private url = environment.baseUrl + 'api/locations';
  private Location: Location[] = [];

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  public getAllLocations() {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Location[]>(`${this.url}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Location Service: error retrieveing locations')
      })
    )
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
