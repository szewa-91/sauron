import { Component, OnInit } from '@angular/core';
import { TransactionsService } from 'src/app/data/transactions.service';
import Transaction from 'src/app/model/Transaction';
import { Bank } from '~/app/model/Bank';
import { AuthService } from '~/app/auth/auth.service';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

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
      private router: Router,
      private transactionsService: TransactionsService,
      private authService: AuthService) {
  }

  ngOnInit() {
    this.authService.getUserData()
        .pipe(
            tap(user => this.banks = user.banks)
        ).subscribe();

    this.transactionsService.fetchTransactions()
        .pipe(
            tap(transactions => this.transactions = transactions),
            catchError(error => this.error = error)
        ).subscribe();
  }

  onTransactionSelected(transaction: Transaction) {
    this.router.navigate([`/summary/transactions`, transaction.id]); // TODO make id unique on backend side
  }

  resolveColor(transaction: Transaction): string {
    return this.banks
        ? this.banks.find(bank => bank.id === transaction.bankId).color
        : undefined;
  }
}
