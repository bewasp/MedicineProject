import { Component, OnInit } from '@angular/core';
import {DataService} from '../../services/data.service';
import * as AOS from 'aos';

@Component({
  selector: 'app-medicines',
  templateUrl: './medicines.component.html',
  styleUrls: ['./medicines.component.css']
})
export class MedicinesComponent implements OnInit {
  public medicines: any;

  constructor(private service: DataService) { }

  ngOnInit() {
    this.getMedicines();
    AOS.init();
  }

  getMedicines() {
    this.service.getMedicines().subscribe(response => {
      this.medicines = response;
    });
  }
}
