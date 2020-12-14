import { Injectable } from '@angular/core';
import {DosageModel} from '../models/dosage.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserAccessService} from './user-access.service';
import {Observable} from 'rxjs';
import {ResponseModel} from '../models/response.model';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  url = 'http://127.0.0.1:8080/api/cure/';

  constructor(private http: HttpClient, private auth: UserAccessService) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  createClientDose(dose: DosageModel): Observable<ResponseModel<any>>{
    return this.http.post<ResponseModel<any>>(this.url + this.auth.currentUser.userId + '/create', dose, this.httpOptions);
  }

  getDose() {
    return this.http.get(this.url + this.auth.currentUser.userId + '/getAll', this.httpOptions);
  }

  deleteDose(dose: DosageModel): Observable<ResponseModel<any>> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      }),
      body: dose
    };
    return this.http.delete<ResponseModel<any>>(this.url + this.auth.currentUser.userId + '/delete', options);
  }

  getMedicines() {
    return this.http.get(this.url + this.auth.currentUser.userId + '/allCures');
  }
}
