import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login/login.component';
import {DosageComponent} from './components/dosage/dosage.component';
import {AuthGuard} from './services/auth/auth-guard.guard';
import {MedicinesComponent} from './components/medicines/medicines.component';
import {ChartComponent} from './components/chart/chart.component';
import {ConfirmEmailComponent} from './components/confirm-email/confirm-email.component';

const routes: Routes = [{
  path: '',
  component: HomeComponent
}, {
  path: 'register',
  component: RegisterComponent
}, {
  path: 'login',
  component: LoginComponent
}, {
  path: 'dose',
  component: DosageComponent,
  canActivate: [AuthGuard]
}, {
  path: 'medicines',
  component: MedicinesComponent,
  canActivate: [AuthGuard]
}, {
  path: 'chart',
  component: ChartComponent,
  canActivate: [AuthGuard]
}, {
  path: 'register/confirm-email/:id',
  component: ConfirmEmailComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
