import { Component, OnInit } from '@angular/core';
import { BalanceService } from '~/app/data/balance.service';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.scss']
})
export class BalanceComponent implements OnInit {

  private error: string;
  private currentBalance: Number;

  constructor(private balanceService: BalanceService) {
  }

  ngOnInit() {
    this.balanceService.fetchCurrentBalance().subscribe(
        currentBalance => this.currentBalance = currentBalance,
        error => this.error = error
    );
  }

}
