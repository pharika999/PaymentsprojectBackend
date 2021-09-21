import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  employee:any;
  constructor(private dataservice:DataService) { }

  ngOnInit(): void {
    /*this.dataservice.getDataFromApi('https://localhost:8080/')
        .subscribe((result: any) => {
          this.products = result.map((x: any) => {
            return { ...x, quantity: 0 }
          });
          this.dataSvc.setProductsData(this.products);
        },err=>{
          console.log(err);
        })*/

  }

}
