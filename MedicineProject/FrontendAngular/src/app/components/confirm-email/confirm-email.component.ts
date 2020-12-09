import { Component, OnInit } from '@angular/core';
import {UserAccessService} from '../../services/user-access.service';
import {ActivatedRoute, Router} from '@angular/router';

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

    this.service.confirmRegistration(id).subscribe((res: boolean) => {
      this.result = res;
      if (res) {
        this.router.navigate(['/login']);
        alert('E-mail confirmed, now you can login!');
      }
    });
  }
}
