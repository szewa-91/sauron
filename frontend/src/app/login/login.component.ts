import { Component, OnInit } from '@angular/core';
import { AuthService } from '~/app/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public mail: string;
  public password: string;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
  }

  login() {
    this.authService.login(this.mail, this.password);
  }

  logout() {
    this.authService.logout();
  }

}
