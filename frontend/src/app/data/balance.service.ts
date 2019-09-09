import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from 'node_modules/@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BalanceService {

  constructor(private httpClient: HttpClient) { }

  public fetchCurrentBalance(userId: number): Observable<Number> {
    const params = new HttpParams().set('userId', userId.toString());
    return this.httpClient.get<Number>('http://localhost:8080/current-balance', {params});
  }

}
