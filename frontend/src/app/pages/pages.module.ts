import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PagesRoutingModule } from './pages-routing.module';
import { HomeComponent } from 'src/app/pages/home/home.component';
import { LoginModule } from 'src/app/pages/login/login.module';

@NgModule({
  declarations: [
    HomeComponent,
  ],
  imports: [
    CommonModule,
    PagesRoutingModule,
    LoginModule,
  ]
})
export class PagesModule {
}
