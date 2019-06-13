import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { NativeScriptCommonModule } from 'nativescript-angular/common';
import { SummaryRoutingModule } from 'src/app/pages/summary/summary-routing.module';

@NgModule({
  declarations: [],
  imports: [
    SummaryRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class SummaryModule { }
