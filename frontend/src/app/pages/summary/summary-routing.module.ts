import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SummaryComponent } from '~/app/pages/summary/summary.component';
import { TransactionDetailsComponent } from '~/app/pages/summary/transaction-details/transaction-details.component';

const routes: Routes = [
  {
    path: '',
    component: SummaryComponent,
  },  {
    path: 'transactions/:id',
    component: TransactionDetailsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SummaryRoutingModule { }
