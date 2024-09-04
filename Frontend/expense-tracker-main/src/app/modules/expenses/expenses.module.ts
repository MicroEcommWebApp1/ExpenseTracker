import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddExpenseFormComponent } from './add-expense-form/add-expense-form.component'; // Adjust the path as necessary
import { ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ViewExpensesComponent } from './view-expenses/view-expenses.component';
import { BudgetFormComponent } from './budget-form/budget-form.component';
import { HighchartsChartModule } from 'highcharts-angular';

@NgModule({
  declarations: [
    AddExpenseFormComponent,
    DashboardComponent,
    ViewExpensesComponent,
    BudgetFormComponent
    
  ],
  imports: [ReactiveFormsModule,
    CommonModule,
    HighchartsChartModule
  ],
  exports: [
    AddExpenseFormComponent ,
   DashboardComponent
  ]
})
export class ExpensesModule { }
