import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/service/customer.service';
import { LocalStorageService } from 'src/service/localStorage.service';
import { TransactionsService } from 'src/service/transactions.service';
import { DataService } from '../data.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  transactions:any;
  constructor(private custsvc:CustomerService,private transsvc:TransactionsService,private storageService:LocalStorageService) { }

  ngOnInit(): void {
    if (this.transsvc.getTransactions.length == 0) {
      this.custsvc.getDataFromApi('http://localhost:8080/transaction/'+this.storageService.get('customer'))
        .subscribe((result: any) => {
          this.transactions=result;
        },(err:any)=>{
          console.log(err);
        })
      }
    }
  }
