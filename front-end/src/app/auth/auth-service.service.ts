import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, map, tap } from 'rxjs/operators';
import { throwError, BehaviorSubject } from 'rxjs';


@Injectable({ providedIn: 'root' })
export class AuthService {
  user = new BehaviorSubject<string | null>(null);
  error=new BehaviorSubject<string>("");

  constructor(private http: HttpClient, private router: Router) {}

  loginBasic(username:string,password:string)
  {
    return this.http
    .post<{token:string}>(
      'http://localhost:8080/login',{
        username:username,
        password:password
      }
    )
    .pipe(
      catchError(this.handleError),
      tap(resData => {
        this.handleAuthentication(
          resData.token
        );
      })
      );
  }
  autoLogin() {
    const token = localStorage.getItem('token');
    if (!token) {
      return;
    }
    this.user.next(token);
  }

  logout(error?:string) {
    this.user.next(null);
    this.router.navigate(['/']);
    if(!!error) this.error.next(error)
    localStorage.removeItem('token');
  }


  private handleAuthentication(token: string) {
    this.user.next(token);
    localStorage.setItem('token', token);
  }

  private handleError(errorRes: string) {
    let errorMessage = 'An unknown error occurred!';
    if(errorRes==="BAD_REQUEST")
    {
        errorMessage = 'Incorrect Email or Password';
    }
    return throwError(()=>errorMessage);
  }
}

