import { Component } from '@angular/core';
import { ExpenseResponseDTO } from '../../../models/expense-response-dto';
import { ExpenseService } from '../../../services/expense.service';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-view-expenses',
  templateUrl: './view-expenses.component.html',
  styleUrl: './view-expenses.component.css'
})
export class ViewExpensesComponent {

  users: any[] = [];
  expenses: ExpenseResponseDTO[] = [];

  constructor(private expenseService: ExpenseService,private userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe({
      next: (response) => {
        this.users = response;
      },
      error: (error) => {
        console.error('Error fetching users:', error);
      }
    });
  }

  onUserSelect(event: Event): void {
    const selectedUserId = (event.target as HTMLSelectElement).value;
    if (selectedUserId) {
      this.expenseService.getExpensesByUserId(Number(selectedUserId)).subscribe({
        next: (response) => {
          this.expenses = response;
        },
        error: (error) => {
          console.error('Error fetching expenses:', error);
        }
      });
    }
  }

  downloadReceipt(expenseId: number): void {
    this.expenseService.downloadReceipt(expenseId).subscribe({
      next: (response) => {
        const blob = new Blob([response], { type: 'application/pdf' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `receipt-${expenseId}.pdf`;
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
      },
      error: (error) => {
        console.error('Error downloading receipt:', error);
      }
    });
  }
  
}
