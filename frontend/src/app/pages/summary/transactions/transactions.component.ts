import { Component, OnInit } from '@angular/core';
import { TransactionsService } from 'src/app/data/transactions.service';
import Transaction from 'src/app/model/Transaction';
import { BankColorService } from '~/app/data/bank-color.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnInit {
  private error: string;
  private transactions: Array<Transaction>;

  constructor(
      private transactionsService: TransactionsService,
      private bankColorService: BankColorService
  ) {
  }

  ngOnInit() {
    this.transactionsService.fetchTransactions()
        .subscribe(
            transactions => this.transactions = transactions,
            error => this.error = error
        );
  }

  resolveColor(transaction: Transaction): string {
    return this.bankColorService.resolveBankColor(transaction.bankId);
  }
}
