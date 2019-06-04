import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public redirectedFrom: string;

  constructor(private router: Router) {
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('user');
  }

  login(mail: string, password: string): void {
    localStorage.setItem('user', mail);
    this.router.navigate(this.redirectedFrom ? [this.redirectedFrom] : ['/']);
  }

  logout(): void {
    localStorage.removeItem('user');
  }
}
