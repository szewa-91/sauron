import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import Transaction from 'src/app/model/Transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {

  constructor(private httpClient: HttpClient) { }

  public fetchTransactions(): Observable<Array<Transaction>> {
    return this.httpClient.get<Array<Transaction>>('http://localhost:8080/transactions');
  }
}
