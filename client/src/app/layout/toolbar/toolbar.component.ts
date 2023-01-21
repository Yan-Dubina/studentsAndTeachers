import {Component, OnInit} from '@angular/core';
import {Router, RouterModule} from "@angular/router";

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  isLogged: boolean;
  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.isLogged = false;
  }

  login(): void {
    this.router.navigate(['/login']);
  }
  main():void {
    this.router.navigate(['']);
  }
}
