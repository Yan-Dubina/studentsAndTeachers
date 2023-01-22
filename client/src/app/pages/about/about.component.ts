import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Artist} from "../../models/Artist";
import {Product} from "../../models/Product";
import {Type} from "../../models/Type";
import {ProductsService} from "../../services/products.service";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})

export class AboutComponent implements OnInit{
  constructor(private productsService: ProductsService, private Activatedroute:ActivatedRoute, private router:Router){}

  information: string;

  sing1: Artist = {
    id: 1, name: "Ala"
  }
  product: Product ;
  ngOnInit(): void {
    this.Activatedroute.queryParams.subscribe(params => {
      this.information = params['product']
    })
    this.productsService.getProduct(Number(this.information)).subscribe( x=>{
      this.product = x;
      // console.log(x)
    })
  }
  addToCart(id: number): void{
    console.log('add'  + id)
  }

}
