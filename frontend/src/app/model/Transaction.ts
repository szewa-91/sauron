export default interface Transaction {
  id: number;
  accountNumber: string;
  direction: string;
  amount: number;
}

export function formatTransactionDirection(direction: string) {
  return direction === 'PAY' ? 'Pay' : 'Receive';
}
