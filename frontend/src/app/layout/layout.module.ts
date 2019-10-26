import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LayoutRoutingModule } from './layout-routing.module';
import { MenuComponent } from './menu/menu.component';
import { LayoutComponent } from './layout.component';
import { LoginModule } from '~/app/pages/login/login.module';

@NgModule({
  declarations: [MenuComponent, LayoutComponent],
  imports: [
    CommonModule,
    LayoutRoutingModule,
      LoginModule
  ],
  exports: [
      LayoutComponent
  ]
})
export class LayoutModule { }
