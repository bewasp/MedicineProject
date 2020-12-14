import { Component, OnInit } from '@angular/core';
import {UserAccessService} from '../../services/user-access.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MessageCodeModel} from '../../enums/message-code.model';

@Component({
  selector: 'app-confirm-email',
  templateUrl: './confirm-email.component.html',
  styleUrls: ['./confirm-email.component.css']
})
export class ConfirmEmailComponent implements OnInit {
  result: boolean;

  constructor(private service: UserAccessService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    let id: string;
    this.route.paramMap
      .subscribe(params => {
        id = params.get('id');
      });

    this.service.confirmRegistration(id).subscribe(result => {
      switch (result.code) {
        case MessageCodeModel.EMAIL_CONFIRMATION_SUCCESS:
          this.router.navigate(['/login']);
          alert('E-mail confirmed, now you can login!');
          break;
        case MessageCodeModel.EMAIL_CONFIRMATION_ERROR:
          alert('There is some problem with confirmation your email');
          break;
      }
    });
  }
}
