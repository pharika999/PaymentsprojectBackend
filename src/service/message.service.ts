
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

 

@Injectable({
  providedIn: 'root'
})
export class MessageService {

 

  private Message:any;
  constructor(private http:HttpClient) {
    this.Message=[];
   }
   getDataFromApi(url:string){
    return this.http.get(url);
  }
  getMessage(){
    return this.Message;
  }
  
}
 



