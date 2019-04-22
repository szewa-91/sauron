import { Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { TransactionsComponent } from '~/app/transactions/transactions.component';

export const routes: Routes = [
  {
      path: '',
      redirectTo: '/transactions',
      pathMatch: 'full',
  },
  {
      path: 'home',
      component: HomeComponent,
  },
  {
      path: 'transactions',
      component: TransactionsComponent,
  },
];
