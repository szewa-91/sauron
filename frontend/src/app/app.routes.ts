import { Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';

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
      loadChildren: './transactions/transactions.module#TransactionsModule',
  },
];
