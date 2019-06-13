import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionsComponent } from '~/app/pages/summary/transactions.component';
import { HttpClientModule } from 'node_modules/@angular/common/http';
import { AgGridModule } from 'ag-grid-angular';
import { SummaryRoutingModule } from '~/app/pages/summary/summary-routing.module';

@NgModule({
  declarations: [
    TransactionsComponent
  ],
  imports: [
    CommonModule,
    SummaryRoutingModule,
    HttpClientModule,
    AgGridModule.withComponents([])
  ]
})
export class SummaryModule {
}
