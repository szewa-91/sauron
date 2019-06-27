import { Pipe, PipeTransform } from '@angular/core';
import Transaction from '~/app/model/Transaction';

@Pipe({
  name: 'signedAmount'
})
export class SignedAmountPipe implements PipeTransform {

  transform(transaction: Transaction): number {
    console.log(transaction)
    return transaction.direction === 'RECEIVE' ? transaction.amount : - transaction.amount;
  }

}
