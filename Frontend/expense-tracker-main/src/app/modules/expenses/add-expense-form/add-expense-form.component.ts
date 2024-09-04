import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ExpenseService } from '../../../services/expense.service';
import { ExpenseRequestDTO } from '../../../models/expense-request-dto';
import { Router } from '@angular/router';
import { UserService } from '../../../services/user.service';
import { response } from 'express';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-expense-form',
  templateUrl: './add-expense-form.component.html',
  styleUrl: './add-expense-form.component.css'
})
export class AddExpenseFormComponent {
  expenseForm: FormGroup;
  users: any[] = [];

  constructor(private fb: FormBuilder, private expenseService: ExpenseService,private userService: UserService,private router: Router) {
    this.expenseForm = this.fb.group({
      userId: ['', Validators.required],
      category: ['', Validators.required],
      amount: ['', [Validators.required, Validators.min(0)]],
      createdDate: ['', Validators.required],
      description: [''],
      receipt: ['']
    });

  }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe({
      next: (response) => {
        this.users = response;
      },
      error: (error) => {
        console.error('Error fetching users:', error);
      }
    })
  }

  onFileChange(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.expenseForm.patchValue({
        receipt: file
      });
    }
  }

  onSubmit(): void {
    if (this.expenseForm.valid) {
      const expenseValues = this.expenseForm.value;
  
      // Convert createdDate to a Date object if it's not already one
      let createdDate: Date | null = null;
      if (expenseValues.createdDate) {
        createdDate = new Date(expenseValues.createdDate);
      }
  
      const expenseRequest: ExpenseRequestDTO = {
        amount: expenseValues.amount,
        description: expenseValues.description,
        category: expenseValues.category,
        createdDate: createdDate, 
        userId: expenseValues.userId
      };
  
      
      this.expenseService.createExpense(expenseRequest, expenseValues.receipt).subscribe({
        next: (response) => {
          console.log('Expense created successfully!', response);
          Swal.fire({
            title: "Expense Added!",
            text: "Successfully",
            icon: "success"
          });
          this.router.navigate(['/']); 
        },
        error: (error) => {
          console.error('Error creating expense!', error);
          Swal.fire({
            icon: "error",
            title: "Something went wrong",
            text: "Enter the valid details",
            footer: '<a href="#">Why do I have this issue?</a>'
          });
        }
      });
    }
  }
  
  
}
