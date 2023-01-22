import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/Product";
import {Artist} from "../../models/Artist";
import {Router, ActivatedRoute, Route} from "@angular/router";
import {ProductsService} from "../../services/products.service";
import {mergeMap} from "rxjs";


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  products: Product[];
  mapProducts: Product[];
  artist: String;

  constructor(private productsService: ProductsService) {
  }

  ngOnInit(): void {
    this.productsService.getProducts().subscribe(data => {
      console.log(data);
      this.mapProducts = data;
      data.flatMap(obj => this.mapProducts.push(obj));
      data.flatMap(obj => this.mapProducts.push(obj));
      data.flatMap(obj => this.mapProducts.push(obj));
      data.flatMap(obj => this.mapProducts.push(obj));
      data.flatMap(obj => this.mapProducts.push(obj));
      data.flatMap(obj => this.mapProducts.push(obj));
      data.flatMap(obj => this.mapProducts.push(obj));
      data.flatMap(obj => this.mapProducts.push(obj));
      data.flatMap(obj => this.mapProducts.push(obj));
      console.log(this.products);
      this.getNext();
  });
  }

  getNext(){
    this.products = this.mapProducts.slice(0,10);
  }
  // GoToAboutProduct(product: Product): void{
  //   this.router.navigate(['otherRoute']);
  // }
  AddToCart(id: number): void {
    console.log('add to cart' + id)
  }

}
