import { Component, OnInit } from '@angular/core';
import {DataService} from '../../services/data.service';

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
  }

  getMedicines() {
    this.service.getMedicines().subscribe(response => {
      this.medicines = response;
    });
  }
}
