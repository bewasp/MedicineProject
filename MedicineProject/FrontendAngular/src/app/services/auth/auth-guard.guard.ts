import { Injectable } from '@angular/core';
import { CanActivate} from '@angular/router';
import {UserAccessService} from '../user-access.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: UserAccessService) {
  }

  canActivate() {
    if (this.authService.isLoggedIn()) {
      return true;
    }
  }
}
