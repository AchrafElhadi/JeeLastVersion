import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-bill-details',
  templateUrl: './bill-details.component.html',
  styleUrls: ['./bill-details.component.css']
})
export class BillDetailsComponent implements OnInit {

  billsdetail!:any
  constructor(private http:HttpClient
    ,private route:ActivatedRoute) { }

  ngOnInit(): void {
    console.log(this.route.snapshot.params["id"])
    this.http.get("http://localhost:8888/BILLING-SERVICE/fullBill/"+this.route.snapshot.params["id"]).subscribe({
      next:(data)=>{
          this.billsdetail=data
      },
      error:(err)=>{

      }
    })
  }

}
