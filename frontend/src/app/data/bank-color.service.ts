import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BankColorService {

  constructor() {
  }

  public resolveBankColor(bankId: number): string {
    switch (bankId) {
      case 1:
        return '#ff7253';
      case 2:
        return '#57c3ff';
      case 3:
        return '#ffea3f';
      default:
        return '';
    }
  }
}
