import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { FormsModule } from '@angular/forms';
import {MatFormFieldModule, MatInputModule} from "@angular/material";

@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
      MatInputModule
  ],
  exports: [
    LoginComponent,
  ]
})
export class LoginModule {
}
