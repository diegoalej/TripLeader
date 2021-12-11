import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  isCollapsed = true;

  constructor(
    private auth: AuthService
  ) { }

  ngOnInit(): void {
  }

  checkLogin(): boolean {
    return this.auth.checkLogin();
  }

}
