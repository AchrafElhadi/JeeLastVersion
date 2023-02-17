export interface Product{
    id:string;
    name:string;
    price:number;
    promotion:boolean;
}

export interface PageProduct
{
    products:Product[];
    size:number;
    page:number;
    TotalPages:number
}
export interface product
{
    id:number;
    name:string;
    price:number;
    quantity:boolean;
}