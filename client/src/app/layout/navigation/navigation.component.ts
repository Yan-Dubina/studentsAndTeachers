import {Component, OnInit} from '@angular/core';
import {User} from "../../models/User";
import {TokenStorageService} from "../../services/token-storage.service";
import {UserService} from "../../services/user.service";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit{

  vinylStyles: string[] = ['Rock','Jazz','Hip-hop'];
  cdStyles: string[] = ['Rock','Jazz','Hip-hop'];
  isLoggedIn = false;
  isDataLoaded = false;
  user: User;

  constructor( private tokenService: TokenStorageService,
               private userService: UserService,
               private router: Router) {
  }

  ngOnInit(): void {

    this.isLoggedIn = !!this.tokenService.getToken();

    if(this.isLoggedIn) {
      this.userService.getCurrentUser()
        .subscribe(data => {
          this.user = data;
          this.isDataLoaded = true;
        })
    }
  }

  logout():void{
    this.tokenService.logOut();
    this.router.navigate(['/login']);
  }

}
