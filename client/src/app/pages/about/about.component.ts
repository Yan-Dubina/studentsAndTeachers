import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Artist} from "../../models/Artist";
import {Product} from "../../models/Product";
import {Type} from "../../models/Type";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})

export class AboutComponent implements OnInit{
  constructor(private Activatedroute:ActivatedRoute, private router:Router){}

  information: string;

  sing1: Artist = {
    id: 1, name: "Ala"
  }
  prod1: Product = {
    comments: [], image: undefined, type: Type.CD,
    cost: 10, description: "Ala ma kota a kot ma ale", id: 1, artist: this.sing1
  }
  ngOnInit(): void {
    this.Activatedroute.queryParams.subscribe(params => {
      this.information = params['product']
    })
    console.log(this.information)
  }
  addToCart(id: number): void{
    console.log('add'  + id)
  }

}
