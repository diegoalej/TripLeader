import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  register(form: NgForm) {
    const user: User = form.value;
    this.auth.register(user).subscribe(
      data => {
        console.log('RegisterComponent.register(): user registered.');
        this.auth.login(user.username, user.password).subscribe(
          next => {
            console.log('RegisterComponent.register(): user logged in, routing to /home.');
            this.router.navigateByUrl('/trips');
          },
          error => {
            console.error('RegisterComponent.register(): error logging in.');
          }
        );
      },
      err => {
        console.error('RegisterComponent.register(): error registering.');
        console.error(err);
      }
    );
  }

}
