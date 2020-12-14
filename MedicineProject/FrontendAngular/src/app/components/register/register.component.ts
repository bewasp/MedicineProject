import { Component, OnInit } from '@angular/core';
import { RegisterModel} from '../../models/register.model';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import {UserAccessService} from '../../services/user-access.service';
import {Router} from "@angular/router";
import {MessageCodeModel} from '../../enums/message-code.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: RegisterModel = new RegisterModel();
  registerForm: FormGroup;
  hide = true;

  constructor(private formBuilder: FormBuilder,
              private service: UserAccessService,
              private router: Router) { }

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
      ]],
      'confirm_pass': [[] , [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(20)
      ]],
    }, {validator: this.checkPasswords});
  }

  checkPasswords(group: FormGroup) {
    const pass = group.get('password').value;
    const confirmPass = group.get('confirm_pass').value;

    return pass === confirmPass ? null : { notSame: true };
  }

  onRegisterSubmit() {
    this.service.register(this.user).subscribe(result => {
      switch (result.code) {
        case MessageCodeModel.EMAIL_IN_USE:
          alert('This email is already used');
          break;
        case MessageCodeModel.REGISTRATION_SUCCESS:
          alert('Registration success, now you can login!');
          break;
        case MessageCodeModel.UNEXPECTED_ERROR:
          alert('There is some problem');
          break;
      }
    });
  }
}
