import { Component, OnInit } from '@angular/core';
import { TransactionsService } from 'src/app/data/transactions.service';
import Transaction, { TransactionDirection } from 'src/app/model/Transaction';
import { catchError, flatMap, map, tap } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-transaction-details',
  templateUrl: './transaction-details.component.html',
  styleUrls: ['./transaction-details.component.scss']
})
export class TransactionDetailsComponent implements OnInit {
  private error: string;
  private transaction: Transaction;

  constructor(
      private route: ActivatedRoute,
      private transactionsService: TransactionsService) {
  }

  ngOnInit() {
    this.route.paramMap
        .pipe(
            map(paramsMap => paramsMap.get('id')),
            flatMap(id => this.transactionsService.getTransaction(Number.parseInt(id))),
            tap(transaction => this.transaction = transaction),
            catchError(error => this.error = error)
        ).subscribe();
  }

  printDirection(transaction: Transaction) {
    return transaction.direction === TransactionDirection.PAY ? 'Outcoming' : 'Incoming';
  }
}
