import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { TripsComponent } from './components/trips/trips.component';
import { HttpClientModule } from '@angular/common/http';
import { TripService } from './services/trip.service';
import { FormsModule } from '@angular/forms';
import { UserService } from './services/user.service';
import { AuthService } from './services/auth.service';
import { LogoutComponent } from './components/logout/logout.component';
import { DatePipe } from '@angular/common';
import { TripFormComponent } from './components/trip-form/trip-form.component';
import { TripDetailComponent } from './components/trip-detail/trip-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooterComponent,
    LoginComponent,
    NavigationComponent,
    CreateUserComponent,
    TripsComponent,
    LogoutComponent,
    TripFormComponent,
    TripDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    UserService,
    TripService,
    AuthService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
