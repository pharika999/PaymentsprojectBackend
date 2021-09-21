import { Component, OnInit, ViewChild } from '@angular/core';
import { CustomerService } from 'src/service/customer.service';
import { LocalStorageService } from 'src/service/localStorage.service';
import { MessageService } from 'src/service/message.service';
import { DataService } from '../data.service';
@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {
  private customer:any;
  private today:any;
  amount:any;
  receivername:string;
  receiveraccountnumber:string;
  bank:any;
  transaction:any;
  bankname:any;
  temp:any;
  message:any;
  transferfee:any;
  currency:any;
  transfertypes:any;
  selectedMsgCode:any;
  constructor(public custsvc:CustomerService ,public msgsvc:MessageService,private datasvc:DataService,
    private storageService:LocalStorageService) {
     this.customer=this.custsvc.getCustomer();
     //console.log(this.customer);
     this.amount=0;
     this.transferfee=this.amount*0.25;
     this.transfertypes={
      "transferTypeCode":''
     },
     this.currency={
       currencyCode:'INR'
     }
     this.bankname="";
     this.bank={
       bic:''
     }
     this.receivername=''
     this.receiveraccountnumber=''
  }
   /* getBic(){
      this.temp=this.transferform.controls['receiverbic'];
      console.log(this.temp);
    }*/
  getCustomer(){
    return this.customer;
  }
  getDate(){
     this.today=new Date();
     let dd = this.today.getDate();
     let  mm = this.today.getMonth()+1; 
     let yyyy = this.today.getFullYear();
    if(dd<10) 
    dd='0'+dd;
    if(mm<10) 
    mm='0'+mm;  
    this.today = dd+'-'+mm+'-'+yyyy;
    return this.today;
  }
  onMessageChange(selected:any){
    this.selectedMsgCode=selected.value;
  }
  getBic(){
          // console.log("hello",this.customer);
            //   console.log('http://localhost:8080/bank/'+this.bank.bic)
               this.datasvc.getDataFromApi('http://localhost:8080/bank/'+this.bank.bic)
                 .subscribe((result: any) => {
                   this.bankname = result
                 },(err:any)=>{
                   alert(err.error);
                   
                   console.log(err);
                 })
        }
        apiResult={
          success:false,
          error:false
        }
        handleTransfer() {
          let url = 'http://localhost:8080/transaction'
          let payLoad = {
            "customer":this.customer,
            "currency":this.currency,
            "message":{
              "messageCode":this.selectedMsgCode
            },
            "senderBank":{
              "bic":"ACBLINBBXXX"
            },
            "receiverBIC":this.bank,
            "receiverAccountHolderName":this.receivername,
            "receiverAccountNumber":this.receiveraccountnumber,
            "transfertype":this.transfertypes,
            "inramount":this.amount,
            "currencyamount":1.0,
            "transferDate":this.today,
            "transferfees":this.transferfee
          };
          this.datasvc.postApi(url, payLoad).subscribe((result:any) => {
            console.log(result);
            //this.customer.clearbalance-=(this.amount+this.transferfee);
            this.custsvc.setCustomer(this.customer);
            this.apiResult.success=true;
            this.apiResult.error =false;
          //  console.log(this.apiResult.success);
          console.log(result.message);
          alert(result.message);

 

          }, err => {
            this.apiResult.success=false;
            this.apiResult.error =true;
            console.log("Hi",err.error);
          })
        }
  ngOnInit(): void {
    this.custsvc.getDataFromApi('http://localhost:8080/customers/'+this.storageService.get('customer'))
        .subscribe((result: any) => {
          this. customer=result;

          //console.log("Customer onit "+this.customer.customerid);
          this.custsvc.setCustomer(result);
        },(err:any)=>{
          console.log(err);
        })
      
    if (this.msgsvc.getMessage().length == 0){
      this.msgsvc.getDataFromApi('http://localhost:8080/message')
        .subscribe((result: any) => {
          this.message = result.map((x: any) => {
            return { ...x }
          });
        },(err:any)=>{
          console.log(err);
        })
    }
  }
  processSDN(){
    this.datasvc.getDataFromApi("http://localhost:8080/sdn/"+this.receivername)
    .subscribe((result:any)=>{
       //console.log("res",result)
       if (result)
       alert("Reciever is in sanction list. You cannot send money!")
    },err=>{
      console.log("Err",err)
    });

 

  }
  iscustomer(){
    return this.customer.customertype==='I';
  }
  onAmountChange(){
    if(this.amount=="")
    this.transferfee="";
    else
    this.transferfee=this.amount*0.25;
   // console.log(this.amount);
  }
}