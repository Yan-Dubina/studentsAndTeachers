import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/Product";
import {Singer} from "../../models/Singer";
import {Router, ActivatedRoute, Route} from "@angular/router";


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit{
 products: Product[] = [];
 constructor() {}
  ngOnInit(): void {

    const sing1: Singer = {
      id: 1, name: "Ala"
    }
    const prod1: Product = {
      cost: 10, description: "Ala ma kota a kot ma ale", id: 1, singer: sing1, type: "Ala"
    }
    const prod2: Product = {
      cost: 12, description: "Beta ma kota a kot ma ale", id: 2, singer: sing1, type: "Ala"
    }
    this.products.push(prod1)
    this.products.push(prod2)
  }
  // GoToAboutProduct(product: Product): void{
  //   this.router.navigate(['otherRoute']);
  // }
  AddToCart(id: number): void{
    console.log('add to cart' + id)
  }

}
