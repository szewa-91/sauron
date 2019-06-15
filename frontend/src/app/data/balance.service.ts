import { Injectable } from '@angular/core';
import { HttpClient } from 'node_modules/@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BalanceService {

  constructor(private httpClient: HttpClient) { }

  public fetchCurrentBalance(): Observable<Number> {
    return this.httpClient.get<Number>('http://localhost:8080/current-balance');
  }

}
