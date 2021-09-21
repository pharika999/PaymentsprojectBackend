import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class DataService
{
  employeeId:any;
  employeeDetails:any;
  constructor(private http:HttpClient) {
    this.employeeDetails={
      id:'',
      name:''
    }
   }
   setEmployeeId(empId:any){
     this.employeeId=empId;
   }
   getDataFromApi(url:string){
    return this.http.get(url);
   }
   setEmployeeDetails(emp:any){
     this.employeeDetails=emp;
   }
   getEmployeeDetails(){
     return this.employeeDetails;
   }
   postApi(url:string,payload:any){
    return this.http.post(url,payload);
}
}
