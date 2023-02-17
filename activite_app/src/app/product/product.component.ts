import { Component, OnInit } from '@angular/core';
import { product } from '../model/product.model';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products!:any
  constructor(private productservice:ProductService) { }

  ngOnInit(): void {
    this.productservice.getProducts().subscribe({
      next:(data)=>{
        console.log(data)
        this.products=data
      },
      error:(err)=>{
        console.log(err)
      }
    })
  }

}
