import { TmplAstRecursiveVisitor } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { map, Observable, take } from 'rxjs';
import { AuthService } from './auth-service.service';

type NewType = Observable<boolean | UrlTree>;

@Injectable({
  providedIn: 'root'
})
export class LogOffGuardService {

  constructor(private authService:AuthService,
    private router:Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | NewType | Promise<boolean | UrlTree> {
    this.authService.autoLogin();
    return this.authService.user.pipe(
      take(1),
      map(token => {
        const isAuth = !!token;
        if (isAuth) {
          return this.router.createUrlTree(['/home']);
        }
        return true;
      }))
  }
}
