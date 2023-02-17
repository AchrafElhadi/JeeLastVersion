import { Injectable } from '@angular/core';
import { UUID } from 'angular2-uuid';
import { Observable, of, throwError } from 'rxjs';
import { PageProduct, Product } from '../model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  products!:Array<Product>

   
  constructor() { 
    this.products=[
      {id:UUID.UUID(),name:'Computer',price:33,promotion:true},
      {id:UUID.UUID(),name:'Printer',price:33,promotion:false},
      {id:UUID.UUID(),name:'Smart phone',price:33,promotion:true},
      
    ]
    for(let i=0;i<10;i++)
    {
      this.products.push({id:UUID.UUID(),name:'Computer',price:33,promotion:true})
      this.products.push({id:UUID.UUID(),name:'Printer',price:33,promotion:false})
      this.products.push({id:UUID.UUID(),name:'Smart phone',price:33,promotion:false})
    }
  }
  getAllProducts():Observable<Array<Product>>
  {
    return of(this.products);
  }

  getPAgeProducts(page:number,size:number):Observable<PageProduct>
  {
    let index=page*size
    let ttl=~~(this.products.length/size);
    if(this.products.length%size!=0)
      ttl++

    let pageProducts=this.products.slice(index,index+size)
    return of({page:page,size:size,TotalPages:ttl,products:pageProducts})
  }

  handleDeleteProduit(id:string):Observable<boolean>
  {
    let prod=this.products.filter(v=>v.id!=id)
    return of(true)
  }
  setPromotion(id:string):Observable<boolean>
  {
    let prod=this.products.find(p=>p.id==id);
    
    if(prod!=undefined)
    {
      prod.promotion=!prod?.promotion
      return of(true)
    }else
    return  throwError(()=>new Error("Product not found"))
  }
  searchProduct(keyword:string,page:number,size:number):Observable<PageProduct>
  {
    let prod=this.products.filter(p=>p.name.includes(keyword))

    let index=page*size
    let ttl=~~(prod.length/size);
    if(prod.length%size!=0)
      ttl++

    let pageProducts=prod.slice(index,index+size)
    return of({page:page,size:size,TotalPages:ttl,products:pageProducts})
    
  }
}
