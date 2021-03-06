import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {DosageModel} from '../../models/dosage.model';
import {DataService} from '../../services/data.service';
import {Router} from '@angular/router';
import {CureCodeModel} from '../../enums/cure-code.model';


@Component({
  selector: 'app-dosage',
  templateUrl: './dosage.component.html',
  styleUrls: ['./dosage.component.css']
})
export class DosageComponent implements OnInit {

  dose: DosageModel = new DosageModel();
  doseForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private service: DataService,
              private router: Router) { }

  ngOnInit() {
    this.doseForm = this.formBuilder.group({
      'name': [this.dose.name, [
        Validators.required,
      ]],
      'dailyDose': [this.dose.dailyDose, [
        Validators.required,
      ]],
      'doseTimestamp': [this.dose.doseTimestamp, [
        Validators.required,
      ]],
      'doseNumber': [this.dose.doseNumber, [
        Validators.required
      ]]
    });
  }

  onDoseSubmit() {
    this.service.createClientDose(this.dose).subscribe(response => {
      switch (response.code) {
        case CureCodeModel.CURE_CREATED:
          alert('Medicine added');
          this.router.routeReuseStrategy.shouldReuseRoute = () => false;
          this.router.onSameUrlNavigation = 'reload';
          this.router.navigate(['/dose']);
          break;
        case CureCodeModel.ADDING_CURE_ERROR:
          alert('Error while adding cure')
          break;
      }
    });
  }
}
