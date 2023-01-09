import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const USER_API = "http://localhost:8080/api/user"
@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }

  getUserById(id: number): Observable<any> {
    return this.http.get(USER_API+"?id="+id);
  }

  getCurrentUser(): Observable<any> {
    return this.http.get(USER_API);
  }

  updateUser(user: any): Observable<any> {
    return this.http.put(USER_API, user);
  }

  deleteUser(user: any): Observable<any> {
    return this.http.delete(USER_API, user);
  }
}
