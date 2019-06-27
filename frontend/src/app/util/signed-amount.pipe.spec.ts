import { SignedAmountPipe } from './signed-amount.pipe';

describe('SignedAmountPipe', () => {
  it('create an instance', () => {
    const pipe = new SignedAmountPipe();
    expect(pipe).toBeTruthy();
  });
});
