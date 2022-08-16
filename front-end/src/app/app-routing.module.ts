import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { AuthGuardService } from './auth/auth-guard.service';
import { LogOffGuardService } from './auth/log-off-guard.service';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginComponent } from './login-page/login.component';

const routes: Routes = [{
  path:'',
  component:LoginComponent,
  canActivate:[LogOffGuardService]
},{
  path:'home',
  component: HomePageComponent,
  canActivate:[AuthGuardService]
},{
  path:'add',
  component:AddEmployeeComponent,
  canActivate:[AuthGuardService]
},{
  path:'edit/:id',
  component:AddEmployeeComponent,
  canActivate:[AuthGuardService]
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
