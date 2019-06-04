import { Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from '~/app/login/login.component';
import { AuthGuard } from '~/app/auth/auth.guard';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'transactions',
    loadChildren: './transactions/transactions.module#TransactionsModule',
    canActivate: [AuthGuard],

  },
];
