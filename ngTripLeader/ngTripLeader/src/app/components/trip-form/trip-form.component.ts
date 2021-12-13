import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-trip-form',
  templateUrl: './trip-form.component.html',
  styleUrls: ['./trip-form.component.scss']
})
export class TripFormComponent implements OnInit {


  constructor(
    private auth: AuthService

  ) { }

  ngOnInit(): void {
  }

  createTrip(form: NgForm){
    console.log( "inside createTrip" + form );
  }
}
