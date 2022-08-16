import { Component, OnDestroy, OnInit } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import { MatChipInputEvent } from '@angular/material/chips';
import { EmployeeListService } from '../employee-list.service';
import { Employee, LocationEmp, nammable, Product, Role } from '../employee-list/employee-response.model';
import { exhaustMap, map, Observable, startWith, Subscription } from 'rxjs';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn } from '@angular/forms';

@Component({
  selector: 'app-filter-employee',
  templateUrl: './filter-employee.component.html',
  styleUrls: ['./filter-employee.component.css']
})
export class FilterEmployeeComponent implements OnInit,OnDestroy {

  filterForm!:FormGroup;
  selected!:string;
  filters:string[]=[];

  roles!:Role[];
  products!:Product[];
  locations!:LocationEmp[];

  filteredRoles!:Observable<Role[]>;
  filteredLocations!:Observable<LocationEmp[]>;
  filteredProducts!:Observable<Product[]>;
  searchParams:{[key:string]:string|undefined}={
    "firstName":undefined,
    "lastName":undefined,
    "email":undefined,
    "role":undefined,
    "location":undefined,
    "product":undefined
  }

  roleSubscription!:Subscription;
  productSubscription!:Subscription;
  locationSubscription!:Subscription;
  

  constructor(private employeeListService:EmployeeListService) { }
  
  ngOnInit(): void {
    this.employeeListService.fetchRoles();
    this.employeeListService.fetchlocations();
    this.employeeListService.fetchproducts();

    this.filterForm=new FormGroup({
      "firstName":new FormControl(null),
      "lastName":new FormControl(null),
      "email":new FormControl(null),
      "location": new FormControl(null),
      "role":new FormControl(null),
      "product": new FormControl(null)
    })
    this.roleSubscription= this.employeeListService.roleSubject.subscribe((role)=>{
      this.roles=role
    })
    this.locationSubscription= this.employeeListService.locationSubject.subscribe((locations)=>{
      this.locations=locations
    });
    this.productSubscription= this.employeeListService.productSubject.subscribe(products=>{
      this.products=products
    });
  }

  add(){
    for(let control in this.filterForm.controls)
    {
      if(!!this.filterForm.controls[control].value)
      {
        this.searchParams[control]=this.filterForm.controls[control].value;
        this.filterForm.controls[control].setValue(null);
      }
    }
    this.filters=[]
    for(let params in this.searchParams)
    {
      if(!!this.searchParams[params])
      {
        this.filters.push(params+":"+this.searchParams[params])
      }
    }
  }

  remove(filter:string)
  {
    
    this.searchParams[filter.slice(0,filter.indexOf(":"))]=undefined;
    this.filters=[]
    for(let params in this.searchParams)
    {
      if(!!this.searchParams[params])
      {
        this.filters.push(params+":"+this.searchParams[params])
      }
    }
  }

  onSubmit(){
    this.employeeListService.fetchEmp(
    undefined,
    undefined,
    this.searchParams["firstName"],
    this.searchParams["lastName"],
    this.searchParams["email"],
    this.searchParams["role"],
    this.searchParams["product"],
    this.searchParams["location"]
    )
  }
  
  ngOnDestroy(): void {
    this.roleSubscription.unsubscribe();
    this.locationSubscription.unsubscribe();
    this.productSubscription.unsubscribe();
  }
}
