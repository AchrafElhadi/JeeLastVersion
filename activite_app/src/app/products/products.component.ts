import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Product } from '../model/product.model';
import { ProductsService } from '../services/products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products!:Array<Product>
  errorMessage!:string
  searchFromGroup!:FormGroup
  currentPage:number=0
  pageSize:number=5
  totalPages=0
  constructor(private productService:ProductsService
    ,private frombuilder:FormBuilder) { }

  ngOnInit(): void {

    this.searchFromGroup=this.frombuilder.group({
      keyword:this.frombuilder.control(null)
    })
  this.handleGetPageProducts()
  }

  handleGetAllProducts()
  {
    this.productService.getAllProducts().subscribe({
      next:(data)=>
      {
        this.products=data
      },
      error:(err)=>{
          this.errorMessage=err
      }
    }
     )
  }

  handleGetPageProducts()
  {
    this.productService.getPAgeProducts(this.currentPage,this.pageSize).subscribe({
      next:(data)=>
      {
        this.products=data.products
        this.totalPages=data.TotalPages
      },
      error:(err)=>{
          this.errorMessage=err
      }
    }
     )
  }

  handleDeleteProduct(p:Product)
  {

    let conf=confirm("Sure ??")
    if(!conf)
    return
    this.productService.handleDeleteProduit(p.id).subscribe(
      {
        next:(data)=>{
          
          let index=this.products.indexOf(p)
          this.products.splice(index,1)
        },
        error:()=>{

        }
      }
    )
  }
  handleSetPromotion(p:Product)
  {
    let promo=p.promotion
      this.productService.setPromotion(p.id).subscribe({
        next:(data)=>{
          p.promotion=!promo
        },
        error:(err)=>{
          this.errorMessage=err
        }
      })
  }
  handleSearchProducts()
  {
    this.currentPage=0
    let keyword=this.searchFromGroup.value.keyword
    this.productService.searchProduct(keyword,this.currentPage,this.pageSize).subscribe({
      next:(data)=>{
         this.products=data.products
         this.totalPages=data.TotalPages
      },
      error:()=>{

      }
    })
  }
  gotToNextPage(page:number)
  {
    this.currentPage=page
    if(this.searchFromGroup.value.keyword)
    this.handleSearchProducts()
    else
    this.handleGetPageProducts()
  }
}
