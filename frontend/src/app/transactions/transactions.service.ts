import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import Transaction from '~/app/model/Transaction';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {

  constructor(private httpClient: HttpClient) { }

  public fetchTransactions(): Observable<Array<Transaction>> {
    return this.httpClient.get('http://localhost:8080/transactions')
        .pipe(map(response => response as Array<Transaction>));
  }
}
