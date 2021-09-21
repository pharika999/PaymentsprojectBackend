import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

 

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {

 

  private transactions:any;
  constructor(private http:HttpClient) {
    this.transactions=[];
   }
   getDataFromApi(url:string){
    return this.http.get(url);
  }
  getTransactions(){
    return this.transactions;
  }
}