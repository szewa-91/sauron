import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { LayoutRoutingModule } from './layout-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { MenuComponent } from './menu/menu.component';

@NgModule({
  declarations: [MenuComponent],
  imports: [
    LayoutRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class LayoutModule { }
