import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import Transaction from '~/app/model/Transaction';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  constructor(private httpClient: HttpClient) {
  }

  public fetchTransactions(userId: number): Observable<Array<Transaction>> {
    const params = new HttpParams().set('userId', userId.toString());
    return this.httpClient.get<Array<Transaction>>('http://localhost:8080/transactions', {params})
        .pipe(
            map(this.transformEveryTransactionToClassInstance) // without this mapping a transaction is not a class' instance
        );
  }

  private transformEveryTransactionToClassInstance(transactions) {
    return transactions.map(transactionObject => new Transaction(transactionObject));
  }
}
