import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { Router } from '@angular/router';
import { BudgetDTO } from '../../../models/budget-dto';
import { BudgetServiceService } from '../../../services/budget-service.service';
import { response } from 'express';

@Component({
  selector: 'app-budget-form',
  templateUrl: './budget-form.component.html',
  styleUrl: './budget-form.component.css'
})
export class BudgetFormComponent {

  budgetForm: FormGroup;
  users: any[] = []; // Array to store fetched users

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private budgetService: BudgetServiceService,
    private router: Router
  ) {
    this.budgetForm = this.fb.group({
      userId: [null, Validators.required],
      amount: [null, [Validators.required, Validators.min(0.01)]],
      startDate: [null, Validators.required],
      endDate: [null, Validators.required],
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

  onSubmit(): void {
    if (this.budgetForm.valid) {
      const budgetFormValue = this.budgetForm.value;
      const budgetRequest: BudgetDTO = {
        amount: budgetFormValue.amount,
        startDate: budgetFormValue.startDate,
        endDate: budgetFormValue.endDate,
        userId: budgetFormValue.userId
      }

      this.budgetService.createBudget(budgetRequest).subscribe({
        next: (response) => {
          console.log('Budget created successfully!', response);
          alert('Entry added successfully');
          this.router.navigate(['/']);
          console.log('Form submitted:', budgetRequest);
        }, 
        error: (error) => {
          console.error('Error creating Budget!', error);
        }
      })
    
    } else {
      console.log('Form is invalid');
    }
  }
}
