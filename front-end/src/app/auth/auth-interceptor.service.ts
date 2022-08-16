import { HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpParams, HttpRequest } from '@angular/common/http';
import { ErrorHandler, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, exhaustMap, Observable, take, throwError } from 'rxjs';
import { AuthService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor{

  constructor(private authService: AuthService,private router:Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return this.authService.user.pipe(
      take(1),
      exhaustMap(token => {
        if (!token) {
          return next.handle(req);
        }
        const modifiedReq = req.clone({headers: new HttpHeaders({
          Authorization:"Bearer "+token
        })});
        return next.handle(modifiedReq);
      })
    ).pipe(catchError(this.handleError.bind(this)));
  }
  handleError(errorRes:HttpErrorResponse)
  {
    let errorMessage = "An unknow error occured";
    if(errorRes.status===401)
    {
      errorMessage="Session Expired";
      this.authService.logout(errorMessage);
    }
    else if(errorRes.status === 400)
    {
      errorMessage="BAD_REQUEST"
    }
    return throwError(()=>errorMessage)
  }
}
