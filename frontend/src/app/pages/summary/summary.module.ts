import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionsComponent } from '~/app/pages/summary/transactions/transactions.component';
import { HttpClientModule } from 'node_modules/@angular/common/http';
import { SummaryRoutingModule } from '~/app/pages/summary/summary-routing.module';
import { SummaryComponent } from './summary.component';
import { BalanceComponent } from './balance/balance.component';
import { UtilModule } from '~/app/util/util.module';
import { MatTooltipModule } from '@angular/material/tooltip';
import { TransactionDetailsComponent } from '~/app/pages/summary/transaction-details/transaction-details.component';

@NgModule({
  declarations: [
    TransactionsComponent,
    TransactionDetailsComponent,
    SummaryComponent,
    BalanceComponent
  ],
  imports: [
    CommonModule,
    MatTooltipModule,
    SummaryRoutingModule,
    HttpClientModule,
    UtilModule,
  ]
})
export class SummaryModule {
}
