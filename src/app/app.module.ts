import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NavitemsComponent } from './navitems/navitems.component';
import { EmployeeComponent } from './employee/employee.component';
import { DataService } from './data.service';
import { CustomerComponent } from './customer/customer.component';
import { TransferComponent } from './transfer/transfer.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { TransactionsService } from 'src/service/transactions.service';
import { JWTTokenService } from 'src/service/jwttoken.service';
import { LocalStorageService } from 'src/service/localStorage.service';
import { UniversalAppInterceptor } from './universal-app.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavitemsComponent,
    EmployeeComponent,
    CustomerComponent,
    TransferComponent,
    TransactionsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {
        path:"",
        component:LoginComponent
      },
      {
        path:"login",
        component:LoginComponent
      },
      {
        path:"transfer",
        component:TransferComponent
      },
      {
        path:"customer",
        component:CustomerComponent
      },
      {
        path:"transactions",
        component:TransactionsComponent
      }
    ])
  ],
  providers: [DataService,JWTTokenService,LocalStorageService,
    { provide: HTTP_INTERCEPTORS, useClass: UniversalAppInterceptor, multi: true }],
  bootstrap: [AppComponent],           
})

export class AppModule {
   
 }
