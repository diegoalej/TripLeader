import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  accessDenied = false;
  constructor(
    private router: Router,
    private auth: AuthService
  ) { }

  ngOnInit(): void {
  }

  login(form: NgForm) {
    const user: User = form.value;

    this.auth.login(user.username, user.password).subscribe(
      success => {
        console.log('LoginComponent.login(): Logged in');

        this.router.navigateByUrl('/trips');
      },
      failed => {
        console.log('LoginComponent.login(): Login Failed');
        this.accessDenied = true;
        this.router.navigateByUrl('home/login');
      }
    );
  }

}
