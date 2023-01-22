import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Product} from "../models/Product";

const PRODUCTS_API = 'http://localhost:8080/product';
@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(PRODUCTS_API+"/all").pipe(map( resp=> {
      if(resp) {
        return Object.values(resp);
      }
      return [];
    }));
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(PRODUCTS_API+"?id=" + id);
  }

  createProduct(product: any): Observable<any> {
    return this.http.post(PRODUCTS_API,product);
  }

  likeProduct(id: number, username: string, description?: string): Observable<any> {
    return this.http.post(PRODUCTS_API + '/' + id + '/like', {
      username: username,
      description: description
    }); // todo add like on backend
  }
}
