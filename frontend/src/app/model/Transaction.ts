export default interface Transaction {
  id: number;
  bankId: number;
  transactionTitle: string;
  accountNumber: string;
  direction: 'PAY' | 'RECEIVE';
  amount: number;
  transactionDate: string;
}

