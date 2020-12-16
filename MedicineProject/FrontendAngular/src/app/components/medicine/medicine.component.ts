import {Component, Input, OnInit} from '@angular/core';
import {DosageModel} from '../../models/dosage.model';
import {Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {ClientCuresService} from '../../services/client-cures.service';
import {AcceptingCureModel} from '../../enums/accepting-cure.model';
import {CureCodeModel} from '../../enums/cure-code.model';

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
    this.service.deleteDose(this.dosageModel).subscribe(response => {
      switch (response.code) {
        case CureCodeModel.CURE_DELETED:
          this.router.routeReuseStrategy.shouldReuseRoute = () => false;
          this.router.onSameUrlNavigation = 'reload';
          this.router.navigate(['/medicines']);
          break;
        case CureCodeModel.CURE_DELETE_ERROR:
          alert('Error while deleting medicine');
          break
      }
    });
  }

  acceptMedicine() {
    this.serviceCure.acceptDose(this.dosageModel).subscribe(response => {
      switch (response.code) {
        case AcceptingCureModel.ACCEPTING_CURE_DELAYED:
          alert("You took your medicine after needed time")
          break;
        case AcceptingCureModel.ACCEPTING_CURE_LATE:
          alert("You are trying to take your medicine after needed time or too fast")
          break;
        case AcceptingCureModel.ACCEPTING_CURE_IN_TIME:
          alert("You took your medicine in time")
          break;
      }
    });
  }
}
