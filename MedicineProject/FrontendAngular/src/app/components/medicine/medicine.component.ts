import {Component, Input, OnInit} from '@angular/core';
import {DosageModel} from '../../models/dosage.model';
import {Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {ClientCuresService} from '../../services/client-cures.service';

@Component({
  selector: 'app-medicine',
  templateUrl: './medicine.component.html',
  styleUrls: ['./medicine.component.css']
})
export class MedicineComponent implements OnInit {

  @Input() name: string;
  @Input() dailyDose: number;
  @Input() doseTimestamp: number;
  @Input() doseNumber: number;

  dosageModel: DosageModel;

  constructor(private service: DataService, private router: Router, private serviceCure: ClientCuresService) {

  }

  ngOnInit() {
    this.dosageModel = new DosageModel();
    this.dosageModel.name = this.name;
    this.dosageModel.dailyDose = this.dailyDose;
    this.dosageModel.doseTimestamp = this.doseTimestamp;
    this.dosageModel.doseNumber = this.doseNumber;
  }

  deleteMedicine() {
    this.service.deleteDose(this.dosageModel).subscribe(() => {
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      this.router.onSameUrlNavigation = 'reload';
      this.router.navigate(['/medicines']);
    });
  }

  acceptMedicine() {
    this.serviceCure.acceptDose(this.dosageModel).subscribe(response => {
      console.log(response);
    });
  }
}
