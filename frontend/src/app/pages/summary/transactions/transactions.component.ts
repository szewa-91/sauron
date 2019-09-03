import { Component, OnInit } from '@angular/core';
import { TransactionsService } from 'src/app/data/transactions.service';
import Transaction from 'src/app/model/Transaction';
import { Bank } from '~/app/model/Bank';
import { AuthService } from '~/app/auth/auth.service';
import { catchError, tap } from 'rxjs/operators';
import { flatMap } from 'node_modules/rxjs/internal/operators';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnInit {
  private error: string;
  private transactions: Array<Transaction>;
  private banks: Array<Bank>;

  constructor(
      private transactionsService: TransactionsService,
      private authService: AuthService) {
  }

  ngOnInit() {
    this.authService.getUserData()
        .pipe(
            tap(user => this.banks = user.banks),
            flatMap(user => this.transactionsService.fetchTransactions(user.id)),
            tap(transactions => this.transactions = transactions),
            catchError(error => this.error = error)
        ).subscribe();
  }

  resolveColor(transaction: Transaction): string {
    return this.banks
        ? this.banks.filter(bank => bank.id === transaction.bankId).pop().color.toString()
        : undefined;
  }
}
