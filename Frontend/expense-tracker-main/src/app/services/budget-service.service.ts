import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BudgetDTO } from '../models/budget-dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BudgetServiceService {

  private BASEURL: string = "http://localhost:8080/api/v1/budget";

  constructor(private http: HttpClient) { }

  createBudget(budget: BudgetDTO): Observable<BudgetDTO> {
    return this.http.post<BudgetDTO>(this.BASEURL, budget);
  }

  getBudgetsByUserId(userId: number): Observable<BudgetDTO[]> {
    return this.http.get<BudgetDTO[]>(`${this.BASEURL}/user/${userId}`);
  }
}
