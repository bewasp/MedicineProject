import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {RegisterModel} from '../models/register.model';
import {LoginModel} from '../models/login.model';
import {map} from 'rxjs/operators';
import {JwtHelper} from 'angular2-jwt';
import {TokenModel} from '../models/token.model';
import {Observable} from 'rxjs';
import {ResponseModel} from '../models/response.model';

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

  register(client: RegisterModel): Observable<ResponseModel<any>> {
    return this.http.post<ResponseModel<any>>(this.url + '/register' , client , this.httpOptions);
  }

  loginMethod(client: LoginModel) {
    return this.http.post(this.url + '/login' , client , this.httpOptions)
      .pipe(
        map ((result: ResponseModel<TokenModel>) => {
          if (result.dto) {
            localStorage.setItem('token', result.dto.token);
            console.log(result.dto.token);
            return result.code;
          }
          return result.code;
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

  confirmRegistration(id: string): Observable<ResponseModel<any>> {
    return this.http.get<ResponseModel<any>>(this.url + '/register/confirm-email/' + id);
  }
}
