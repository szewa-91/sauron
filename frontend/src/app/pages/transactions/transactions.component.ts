import { Component, OnInit } from '@angular/core';
import { TransactionsService } from '~/app/pages/transactions/transactions.service';
import Transaction, { formatTransactionDirection } from '~/app/model/Transaction';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnInit {

  private columnDefs = [
    { headerName: 'Account number', field: 'accountNumber', sortable: true, filter: 'agTextColumnFilter' },
    {
      headerName: 'Direction', field: 'direction', sortable: true, filter: false,
      valueFormatter: params => formatTransactionDirection(params.value)
    },
    { headerName: 'Amount', field: 'amount', sortable: true, filter: 'agNumberColumnFilter' }
  ];

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

}
