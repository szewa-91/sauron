import { Component, OnInit } from '@angular/core';
import { TransactionsService } from 'src/app/data/transactions.service';
import Transaction from 'src/app/model/Transaction';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnInit {
  private error: string;
  private transactions: Array<Transaction>;

  constructor(private transactionsService: TransactionsService) {
  }

  ngOnInit() {
    this.transactionsService.fetchTransactions()
        .subscribe(
            transactions => this.transactions = transactions,
            error => this.error = error
        );
  }

  private getBankColor(transaction: Transaction): string {
    switch (transaction.bankId) {
      case 1:
        return 'bank-indicator--bank1';
      case 2:
        return 'bank-indicator--bank2';
      case 3:
        return 'bank-indicator--bank3';
      default:
        return '';
    }
  }

}
