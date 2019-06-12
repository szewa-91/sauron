import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { PagesRoutingModule } from './pages-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';

@NgModule({
  declarations: [],
  imports: [
    PagesRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class PagesModule { }
