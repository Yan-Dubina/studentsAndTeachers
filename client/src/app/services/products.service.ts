import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const PRODUCTS_API = 'http://localhost:8080/api/products';
@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) { }

  getProducts(): Observable<any> {
    return this.http.get(PRODUCTS_API);
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
