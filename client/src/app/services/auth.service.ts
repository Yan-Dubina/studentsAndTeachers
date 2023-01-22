import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {NotificationService} from "./notification.service";

const AUTH_API = 'http://localhost:8080/auth/'; //todo dodac AUTH2.0
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private notificationService: NotificationService,
              private router: Router) { }

  public login(model: any): Observable<boolean> {
   return  this.http.post<boolean>(AUTH_API + 'login', {
      userName: model.username,
      password: model.password
    });
  }
  public  register(user: any):Observable<any> {
    return  this.http.post(AUTH_API+'signin',{
      username: user.username,
      firstname: user.firstname,
      lastname: user.lastname,
      email: user.email,
      password: user.password,
      confirmPassword: user.confirmPassword
    });
  }

}
