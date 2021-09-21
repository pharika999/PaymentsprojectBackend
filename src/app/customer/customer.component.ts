import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/service/customer.service';
import { LocalStorageService } from 'src/service/localStorage.service';
;

 

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent {


  private customer : any={};
  constructor(public custsvc:CustomerService,private router:Router,private storageService:LocalStorageService) {
    //this.customer=this.custsvc.getCustomer();
    //console.log("Customer oninit",this.customer);
  }
  getCustomer(){
    return this.customer;
  }
  
  ngOnInit(): void {
    if (this.custsvc.getCustomer.length == 0) {
      this.custsvc.getDataFromApi('http://localhost:8080/customers/'+this.storageService.get('customer'))
        .subscribe((result: any) => {
          this. customer=result;

          console.log("Customer onit "+this.customer.customerid);
          this.custsvc.setCustomer(result);
        },(err:any)=>{
          console.log(err);
        })
    }
  }

  onClickTransfer(){
    this.router.navigate(['/transfer']);
  }

 

}