import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const IMAGE_API = 'http://localhost:8080/api/image/'

@Injectable({
  providedIn: 'root'
})
export class ImageUploadService {

  constructor(private http: HttpClient) {
  }

  uploadImageForProduct(file: File, type: TypeOfProduct): Observable<any> {
    const uploadData = new FormData();
    uploadData.set('file', file);
    return this.http.post(IMAGE_API + type, uploadData);
  }

  uploadProfileImage(file: File): Observable<any> {
    const uploadData = new FormData();
    uploadData.set('file', file);
    return this.http.post(IMAGE_API + 'user', uploadData);
  }

  getProfileImage(): Observable<any> {
    return this.http.get(IMAGE_API + 'user');
  }

  getImageForProduct(productId: number, type: TypeOfProduct): Observable<any> {
    return this.http.get(IMAGE_API + type + '?id=' + productId);
  }

}
