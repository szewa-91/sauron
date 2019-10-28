import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, ReplaySubject, Subject } from 'rxjs';
import Transaction from '~/app/model/Transaction';
import { flatMap, map, tap } from 'rxjs/operators';
import { AuthService } from '~/app/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  private $transactions: Subject<Array<Transaction>> = new ReplaySubject<Array<Transaction>>();

  constructor(
      private authService: AuthService,
      private httpClient: HttpClient) {
    this.authService.getUserData()
        .pipe(
            flatMap(
                user => {
                  const params = new HttpParams().set('userId', user.id.toString());
                  return this.httpClient.get<Array<Transaction>>('http://localhost:8080/transactions', { params });
                }),
            tap(transactions => this.$transactions.next(transactions))
        ).subscribe();
  }

  public getTransactions(): Observable<Array<Transaction>> {
    return this.$transactions.asObservable()
        .pipe(
            map(this.transformEveryTransactionToClassInstance) // without this mapping a transaction is not a class' instance
        );
  }

  public getTransaction(uuid: string): Observable<Transaction> {
    return this.$transactions.asObservable().pipe(
        map(transactions => transactions.find(transaction => transaction.uuid === uuid)),
        map(Transaction.instantiate)
    );
  }

  private transformEveryTransactionToClassInstance(transactions) {
    return transactions.map(Transaction.instantiate);
  }
}
