import { CreateUserComponent } from './components/create-user/create-user.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { FooterComponent } from './components/footer/footer.component';
import { TripsComponent } from './components/trips/trips.component';

const routes: Routes = [
  { path: '',  redirectTo: 'home/login', pathMatch: 'full'  },
  { path: 'home', component: HomeComponent, children: [
    { path: '', component: LoginComponent },
    {path: 'login', component: LoginComponent},
    {path: 'register', component: CreateUserComponent}
   ] },
   { path: 'trips', component: TripsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
