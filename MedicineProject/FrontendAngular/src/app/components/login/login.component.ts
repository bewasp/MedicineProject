import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import {LoginModel} from '../../models/login.model';
import {UserAccessService} from '../../services/user-access.service';
import {Router} from '@angular/router';
import {MessageCodeModel} from '../../enums/message-code.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: LoginModel = new LoginModel();
  loginForm: FormGroup;
  result: boolean;
  hide = true;

  constructor(private formBuilder: FormBuilder,
              private service: UserAccessService,
              public router: Router) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      'email': [this.user.email, [
        Validators.required,
        Validators.email
      ]],
      'password': [this.user.password, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30)
      ]]
    });
  }

  onLoginSubmit() {
    this.service.loginMethod(this.user).subscribe(result => {
      console.log(result)
      switch (result) {
        case MessageCodeModel.LOGIN_SUCCESS:
          this.router.navigate(['/']);
          break;
        case MessageCodeModel.EMAIL_NOT_REGISTERED:
          alert("This email is not exists");
          break;
        case MessageCodeModel.NOT_ACTIVATED_EMAIL:
          alert("This email is not confirmed");
          break;
        case MessageCodeModel.UNEXPECTED_ERROR:
          alert("There is some error");
          break;
      }
    });
  }
}
