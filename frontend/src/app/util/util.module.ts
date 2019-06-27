import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignedAmountPipe } from '~/app/util/signed-amount.pipe';

@NgModule({
  declarations: [
    SignedAmountPipe,
  ],
  exports: [
    SignedAmountPipe
  ],
  imports: [
    CommonModule
  ]
})
export class UtilModule {
}
