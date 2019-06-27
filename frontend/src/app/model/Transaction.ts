export default interface Transaction {
  id: number;
  accountNumber: string;
  direction: 'PAY' | 'RECEIVE';
  amount: number;
}

