import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

 

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  setCustomer(customer:any){
     this.customer=customer;
   }

 getDataFromApi(url:string){
  return this.http.get(url);
}
postApi(url:string,payload:any){
  return this.http.post(url,payload);
}
getCustomer(){
  return this.customer;
}
  private customer:any;
  constructor(private http:HttpClient) {
    this.customer={};
   }

}