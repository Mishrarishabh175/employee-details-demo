import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth/auth-service.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private router:Router,
    private authService:AuthService) { }

  ngOnInit(): void {
  }
  onAdd(){
    this.router.navigate(["/add"])
  }
  onLogOut(){
    this.authService.logout();
  }
}
