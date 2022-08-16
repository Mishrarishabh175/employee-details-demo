import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import { LoginComponent } from './login-page/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { HomePageComponent } from './home-page/home-page.component';
import { EmployeeListComponent } from './home-page/employee-list/employee-list.component';
import {MatTableModule} from '@angular/material/table';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { FilterEmployeeComponent } from './home-page/filter-employee/filter-employee.component';
import {MatSelectModule} from '@angular/material/select';
import {MatChipsModule} from '@angular/material/chips';
import {MatIconModule} from '@angular/material/icon';
import {MatPaginatorIntl, MatPaginatorModule} from '@angular/material/paginator';
import {MatDialogModule} from '@angular/material/dialog';
import { DialogBoxComponent } from './home-page/employee-list/dialog-box/dialog-box.component';
import { AuthInterceptorService } from './auth/auth-interceptor.service';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    EmployeeListComponent,
    AddEmployeeComponent,
    FilterEmployeeComponent,
    DialogBoxComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatSelectModule,
    MatChipsModule,
    MatIconModule,
    MatPaginatorModule,
    MatDialogModule
  ],
  providers: [MatPaginatorIntl,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
