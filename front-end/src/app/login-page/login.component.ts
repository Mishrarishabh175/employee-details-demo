import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit, ViewChild,ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeListService } from '../home-page/employee-list.service';
import { AuthService } from '../auth/auth-service.service';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit,OnDestroy {
  error:string="";
  errorSubscription!:Subscription;
  loginForm!:FormGroup;
  constructor(
    private authService:AuthService,
    private router:Router) { }

  ngOnInit(): void {
    this.loginForm=new FormGroup({
      "username":new FormControl(null,[Validators.required,Validators.minLength(4),
      Validators.maxLength(16),Validators.pattern("^[A-Za-z.0-9]*$")]),
      "password":new FormControl(null,[Validators.required,Validators.minLength(6),
        Validators.maxLength(16)])
    })
    this.errorSubscription = this.authService.error.subscribe((error)=>{
      this.error=error
    })
  }
  ngOnDestroy(): void {
    this.errorSubscription.unsubscribe();
  }
  onSubmit()
  {
    if(this.loginForm.valid)
    {
      this.authService.loginBasic(this.loginForm.value.username,this.loginForm.value.password).subscribe({
        next:(res)=>{
          this.router.navigate(["/home"])
        },
        error:(error)=>{
          this.error=error        
        }
      });
    }
    
  }
}
