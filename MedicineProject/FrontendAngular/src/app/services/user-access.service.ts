import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {RegisterModel} from '../models/register.model';
import {LoginModel} from '../models/login.model';
import {map} from 'rxjs/operators';
import {JwtHelper} from 'angular2-jwt';
import {TokenModel} from '../models/token.model';

@Injectable({
  providedIn: 'root'
})
export class UserAccessService {

  url = 'http://127.0.0.1:8080/api/user';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  register(client: RegisterModel) {
    return this.http.post<boolean>(this.url + '/register' , client , this.httpOptions);
  }

  loginMethod(client: LoginModel) {
    return this.http.post(this.url + '/login' , client , this.httpOptions)
      .pipe(
        map ((result: TokenModel) => {
          if (result && result.token) {
            localStorage.setItem('token', result.token);
            console.log(result.token);
            return true;
          }
          return false;
        })
      );
  }
  logOut() {
    return this.http.delete(this.url + '/logout/' + this.currentUser.userId)
      .pipe(
        map(() => {
          localStorage.removeItem('token');
        })
      );
  }

  isLoggedIn() {
    const jwtHelper = new JwtHelper();
    const token = localStorage.getItem('token');
    if (!token) {
      return false;
    }
    return !(jwtHelper.isTokenExpired(token));
  }

  get currentUser() {
    const token = this.getToken();
    if (!token) {
      return null;
    }
    return new JwtHelper().decodeToken(token);
  }

  getToken() {
    return localStorage.getItem('token');
  }
}
