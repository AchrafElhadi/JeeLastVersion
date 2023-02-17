import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit {

  bills!:any

  constructor(private http:HttpClient
    ,private route:ActivatedRoute) { }

  ngOnInit(): void {
    console.log(this.route.snapshot.params["id"])
    this.http.get("http://localhost:8888/BILLING-SERVICE/getcustomer/"+this.route.snapshot.params["id"]).subscribe({
      next:(data)=>{
          this.bills=data
      },
      error:(err)=>{

      }
    })
  }

}
