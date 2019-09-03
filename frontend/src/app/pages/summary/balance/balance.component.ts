import { Component, OnInit } from '@angular/core';
import { BalanceService } from '~/app/data/balance.service';
import { AuthService } from '~/app/auth/auth.service';
import { flatMap, tap } from 'node_modules/rxjs/internal/operators';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.scss']
})
export class BalanceComponent implements OnInit {

  private error: string;
  private currentBalance: Number;

  constructor(
      private balanceService: BalanceService,
      private authService: AuthService) {
  }

  ngOnInit() {
    this.authService.getUserData()
        .pipe(
            flatMap(user => this.balanceService.fetchCurrentBalance(user.id)),
            tap(currentBalance => this.currentBalance = currentBalance),
            catchError(error => this.error = error)
        ).subscribe();
  }

}
