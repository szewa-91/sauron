export default class Transaction {

  constructor(rawObject) {
    this.id = rawObject.id;
    this.bankId = rawObject.bankId;
    this.transactionTitle = rawObject.transactionTitle;
    this.accountNumber = rawObject.accountNumber;
    this.direction = TransactionDirection[rawObject.direction as string];
    this.amount = rawObject.amount;
    this.transactionDate = rawObject.transactionDate;
  }

  id: number;
  bankId: number;
  transactionTitle: string;
  accountNumber: string;
  direction: TransactionDirection;
  amount: number;
  transactionDate: string;

  get signedAmount(): number {
    return this.direction === TransactionDirection.RECEIVE ? this.amount : -this.amount;
  }

  public static instantiate(rawObject: Transaction) {
    return new Transaction(rawObject);
  }
}

export enum TransactionDirection {
  PAY, RECEIVE
}
