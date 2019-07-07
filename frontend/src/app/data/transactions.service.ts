import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import Transaction from 'src/app/model/Transaction';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  constructor(private httpClient: HttpClient) {
  }

  public fetchTransactions(): Observable<Array<Transaction>> {
    return this.httpClient.get<Array<Transaction>>('http://localhost:8080/transactions').pipe(
        map(this.transformEveryTransactionToClassInstance()) // without this mapping a transaction is not a class' instance
    );
  }

  private transformEveryTransactionToClassInstance() {
    return transactions => transactions.map(transactionObject => new Transaction(transactionObject));
  }
}
