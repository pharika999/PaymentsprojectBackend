import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Router } from '@angular/router';
import { CustomerService } from 'src/service/customer.service';
import { JWTTokenService } from 'src/service/jwttoken.service';
import { LocalStorageService } from 'src/service/localStorage.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  customerUser:any;
  employee:any;
  flag:any;
  customer:any;
  constructor(private dataservice:DataService, private router:Router,private custsvc:CustomerService,
    private jwtService:JWTTokenService,private storageService:LocalStorageService) { 
    this.flag=true;
    this.customerUser={
      username:'',
      password:''
    }
    this.employee={
      empId:'',
      password:''
    }
  }

  ngOnInit(): void {
    // if (this.custsvc.getCustomer.length == 0) {
    //   this.custsvc.getDataFromApi('http://localhost:8080/customeruser/'+this.customerUser.username)
    //     .subscribe((result: any) => {
    //       this. customerid=result.customerid;
    //       console.log("Customer onit "+this.customerid);
    //       this.custsvc.setCustomer(result);
    //     },(err:any)=>{
    //       console.log(err);
    //     })
    // }

  }

  onChange(value:any)
  {
    //console.log(value.name);
    if(value.name==="customerLogin")
    this.flag=true;
    else
    this.flag=false;
  }
  apiResult={
    success:false,
    error:false
  }

  handleLogin(){
      let url = 'http://localhost:8080/login/authenticate';
      let payLoad = {
        "username":this.customerUser.username,
        "password":this.customerUser.password
      }
      this.dataservice.postApi(url, payLoad).subscribe((result:any) => {
        console.log(result);
        this.apiResult.success=true;
        this.apiResult.error =false;
        this.jwtService.setToken(result);
        this.storageService.set('token',result.jwt);
        //console.log("Token in local storage",this.storageService.get('token'));
        //this.router.navigate(['/customer']);
        if (this.custsvc.getCustomer.length == 0) {
          this.custsvc.getDataFromApi('http://localhost:8080/customeruser/'+this.customerUser.username)
            .subscribe((result: any) => {
              this. customer=result.customer;
              this.storageService.set('customer',result.customer.customerid);
              this.custsvc.setCustomer(result.customer);
              console.log("login  ",this.customer);
              this.router.navigate(['/customer']);
            },(err:any)=>{
              console.log(err);
            })
        }
        //this.router.navigate(['/customer']);
      }, err => {
        alert("Invalid credentials");
        this.apiResult.success=false;
        this.apiResult.error =true;
      })
      console.log("Button clicked")
      //this.router.navigate(['/customer']);
  }
}
