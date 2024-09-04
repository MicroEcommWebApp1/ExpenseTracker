import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ExpenseRequestDTO } from '../models/expense-request-dto';
import { ExpenseResponseDTO } from '../models/expense-response-dto';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {
  private BASEURL: string = "http://localhost:8080/api/v1/expense";

  constructor(private http: HttpClient) { }

  createExpense(expense: ExpenseRequestDTO, receipt?: File): Observable<any> {
    const formData = new FormData();
    
    formData.append('amount', expense.amount.toString());
    formData.append('description', expense.description);
    formData.append('category', expense.category);
    if (expense.createdDate) {
      formData.append('createdDate', expense.createdDate.toISOString().split('T')[0]); // Format date to string
    }
    formData.append('userId', expense.userId.toString());
    if (receipt) {
      formData.append('receipt', receipt);
    }

    return this.http.post(this.BASEURL, formData, {
      headers: new HttpHeaders({
        'Accept': 'application/json'
      })
    });
  }

  getExpensesByUserId(userId: number): Observable<ExpenseResponseDTO[]> {
    return this.http.get<ExpenseResponseDTO[]>(`${this.BASEURL}/user/${userId}`);
  }

  downloadReceipt(expenseId: number): Observable<Blob> {
    return this.http.get(`${this.BASEURL}/${expenseId}/receipt`, {
      responseType: 'blob'
    });
  }
}
