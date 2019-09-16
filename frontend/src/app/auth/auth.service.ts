import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from 'node_modules/@angular/common/http';
import { User } from '~/app/model/User';
import { Observable, ReplaySubject, Subject } from 'rxjs';
import { map, tap } from 'rxjs/operators';

const USER_ID_KEY = 'userId';
const LOGIN_KEY = 'login';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public redirectedFrom: string;

  private userData$: Subject<User> = new ReplaySubject<User>();

  constructor(private router: Router, private httpClient: HttpClient) {
    this.fetchUserDataIfNeeded();
  }

  public login(login: string, password: string): void {
    this.httpClient.post<User>('http://localhost:8080/users/login', { login, password })
        .subscribe(
            userData => {
              localStorage.setItem(LOGIN_KEY, userData.username);
              localStorage.setItem(USER_ID_KEY, userData.id.toString());
              this.router.navigate(this.redirectedFrom ? [this.redirectedFrom] : ['/']);
              this.userData$.next(userData);
            });
  }

  public logout(): void {
    localStorage.removeItem(LOGIN_KEY);
    localStorage.removeItem(USER_ID_KEY);
    this.router.navigate(['/login']);
  }

  public getUserData(): Observable<User> {
    return this.userData$.asObservable();
  }

  public isLoggedIn(): boolean {
    return !!localStorage.getItem(USER_ID_KEY);
  }

  private fetchUserDataIfNeeded() {
    const userId = localStorage.getItem(USER_ID_KEY);
    if (userId) {
      this.httpClient.get<User>(`http://localhost:8080/users/${userId}`)
          .pipe(
              map(User.instantiate),
                  tap(userData => this.userData$.next(userData))
          ).subscribe();
    }
  }
}
