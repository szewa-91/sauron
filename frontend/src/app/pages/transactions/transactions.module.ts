import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionsRoutingModule } from './transactions-routing.module';
import { TransactionsComponent } from '~/app/pages/transactions/transactions.component';
import { HttpClientModule } from 'node_modules/@angular/common/http';

@NgModule({
  declarations: [
    TransactionsComponent
  ],
  imports: [
    CommonModule,
    TransactionsRoutingModule,
    HttpClientModule,
  ]
})
export class TransactionsModule {
}
