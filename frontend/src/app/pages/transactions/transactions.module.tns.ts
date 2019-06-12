import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { TransactionsRoutingModule } from './transactions-routing.module';
import { NativeScriptCommonModule } from 'nativescript-angular/common';

@NgModule({
  declarations: [],
  imports: [
    TransactionsRoutingModule,
    NativeScriptCommonModule
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class TransactionsModule { }
