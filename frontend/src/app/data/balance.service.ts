import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from 'node_modules/@angular/common/http';
import { Observable, ReplaySubject, Subject } from 'rxjs';
import { AuthService } from '~/app/auth/auth.service';
import { tap } from 'rxjs/operators';
import { flatMap } from 'node_modules/rxjs/internal/operators';

@Injectable({
  providedIn: 'root'
})
export class BalanceService {
  private $balance: Subject<Number> = new ReplaySubject<Number>();

  constructor(
      private authService: AuthService,
      private httpClient: HttpClient) {
    this.authService.getUserData()
        .pipe(
            flatMap(user => {
              const params = new HttpParams().set('userId', user.id.toString());
              return this.httpClient.get<Number>('http://localhost:8080/current-balance', { params });
            }),
            tap(balance => this.$balance.next(balance))
        ).subscribe();
  }

  public fetchCurrentBalance(): Observable<Number> {
    return this.$balance.asObservable();
  }

}
