import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/Product";
import {Type} from "../../models/Type";

@Component({
  selector: 'app-shop-card',
  templateUrl: './shop-card.component.html',
  styleUrls: ['./shop-card.component.css']
})

export class ShopCardComponent implements OnInit{
  products: Product[] = [];
  constructor() {}
  ngOnInit(): void {
    // const sing1: Singer = {
    //   id: 1, name: "Ala"
    // }

    // const prod1: Product = {
    //   artist: undefined, type: undefined,
    //   cost: 10, description: "Ala ma kota a kot ma ale", id: 1
    // }
    // const prod2: Product = {
    //   artist: undefined, type: undefined,
    //   cost: 12, description: "Beta ma kota a kot ma ale", id: 2
    // }
    // this.products.push(prod1)
    // this.products.push(prod2)
  }
  AddToCart(id: number): void{
    console.log('add to cart' + id)
  }

}
