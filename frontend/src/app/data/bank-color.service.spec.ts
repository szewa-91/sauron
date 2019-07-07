import { TestBed } from '@angular/core/testing';

import { BankColorService } from './bank-color.service';

describe('BankColorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BankColorService = TestBed.get(BankColorService);
    expect(service).toBeTruthy();
  });
});
