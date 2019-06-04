import { Component, OnInit } from '@angular/core';
import { AuthService } from '~/app/auth/auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit() {
  }
}
