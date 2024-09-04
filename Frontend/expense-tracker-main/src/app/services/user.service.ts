import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private BASEURL: string = "http://localhost:8080/api/v1/users";

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<any>{
    return this.http.get(`${this.BASEURL}`);
  }
}
