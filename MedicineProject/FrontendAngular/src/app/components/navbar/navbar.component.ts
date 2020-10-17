import { Component, OnInit } from '@angular/core';
import {UserAccessService} from '../../services/user-access.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public authService: UserAccessService,
              public router: Router) { }

  ngOnInit() {
  }

  logOut() {
    this.authService.logOut().subscribe(() => {
      this.router.navigate(['/']);
    });
  }
}
