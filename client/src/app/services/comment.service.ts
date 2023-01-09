import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const COMMENT_API = 'http://localhost:8080/api/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) {
  }

  addComment(productId: number, type: TypeOfProduct, message: string): Observable<any> {
    return this.http.post(COMMENT_API+'?productId='+productId+'&type='+type, {
      message: message
    });
  }

  getComments(productId: number, type: TypeOfProduct): Observable<any> {
    return this.http.get(COMMENT_API+'?productId='+productId+'&type='+type);
  }

  delete(commentId: number): Observable<any> {
    return this.http.delete(COMMENT_API+'?id='+commentId);
  }
}
