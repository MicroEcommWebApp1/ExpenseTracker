import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './modules/expenses/dashboard/dashboard.component';
import { AddExpenseFormComponent } from './modules/expenses/add-expense-form/add-expense-form.component';
import { ViewExpensesComponent } from './modules/expenses/view-expenses/view-expenses.component';
import { BudgetFormComponent } from './modules/expenses/budget-form/budget-form.component';

const routes: Routes = [
  
  { path: '', component: DashboardComponent }, 
  { path:'add-expense-form', component:AddExpenseFormComponent},
  { path:'view-expense', component:ViewExpensesComponent},
  { path:'budget-form', component:BudgetFormComponent},
  { path: '**', redirectTo: '' } //404
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
