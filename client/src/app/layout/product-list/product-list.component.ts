import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../../models/Product";
import {ProductsService} from "../../services/products.service";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  @Input() products: Product[];
  artist: String;
  @Input() pageSizeOptions: number;

  pageSize:number;
  pageIndex:number;


  pageEvent: PageEvent;

  handlePageEvent(e: PageEvent) {
    this.pageEvent = e;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
  }

  constructor() {
  }

  ngOnInit(): void {
  }
}
