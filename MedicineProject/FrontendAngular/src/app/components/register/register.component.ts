import { Component, OnInit } from '@angular/core';
import { RegisterModel} from '../../models/register.model';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: RegisterModel = new RegisterModel();
  registerForm: FormGroup;
  hide = true;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      'name': [this.user.name, [
        Validators.required
      ]],
      'surname': [this.user.surname, [
        Validators.required
      ]],
      'email': [this.user.email, [
        Validators.required,
        Validators.email
      ]],
      'phone': [this.user.phoneNumber, [
        Validators.required,
        Validators.pattern('[0-9]{9}')
      ]],
      'password': [this.user.password, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30)
      ]]
    });
  }

  onRegisterSubmit() {
    alert(this.user.name + ' ' + this.user.surname + ' ' + this.user.phoneNumber + ' ' + this.user.email + ' ' + this.user.password);
  }
}
