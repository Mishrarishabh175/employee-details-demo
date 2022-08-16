import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Employee, EmployeeResponse, LocationEmp, Product, Role } from './employee-list/employee-response.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeListService  {
  isChanged=false;
  isFetched=false;
  private employeeList:Employee[]=[];
  private roleList!:Role[];
  private productList!:Product[];
  private locationList!:LocationEmp[];

  emitEditEmployee=new Subject<Employee>();
  roleSubject = new BehaviorSubject<Role[]>([]);
  productSubject = new BehaviorSubject<Product[]>([]);
  locationSubject = new BehaviorSubject<LocationEmp[]>([]);
  employeeSubject =new BehaviorSubject<EmployeeResponse|null>(null);
  page:number=1;
  entries:number=10;
  
  constructor(private http:HttpClient) { 
  
  }
  
  fetchRoles(){
      if(this.roleList === undefined)
      {
        this.http.get<Role[]>("http://localhost:8080/roles").subscribe({
          next:response=>{
            this.roleList=response;
            this.roleSubject.next(this.roleList)
          },
          error:(error)=>{
            console.log(error);
          }
        })
      }
  }
  fetchlocations(){
    if(this.locationList=== undefined)
    {
      this.http.get<LocationEmp[]>("http://localhost:8080/locations").subscribe({
        next:response=>{
          this.locationList=response;
          this.locationSubject.next(this.locationList);
        },
        error:(error)=>{
          console.log(error);
        }
      })
    }
  }
  fetchproducts(){
    if(this.productList===undefined)
    {
      this.http.get<Product[]>("http://localhost:8080/products").subscribe({
        next:response=>{
          this.productList=response;
          this.productSubject.next(this.productList)},
        error:(error)=>{
          console.log(error);
        }
      })
    }
  }

  fetchEmployeesFromEdit(id:number):Employee[]{
    return this.employeeList.filter((emp)=>emp.id===id);
  }

  fetchEmp(page?:number,
  entries?:number,
  firstName?:string,
  lastName?:string,
  email?:string,
  role?:string,
  product?:string,
  location?:string){
    let searchParams = new HttpParams();

    if(!!page) this.page=page
    searchParams=searchParams.append("page",this.page)

    if(!!entries) this.entries =entries
    searchParams=searchParams.append("entries",this.entries)

    if(!!firstName)
    {
      searchParams=searchParams.append("firstName",firstName)
    }

    if(!!lastName)
    {
      searchParams=searchParams.append("lastName",lastName)
    }

    if(!!email)
    {
      searchParams=searchParams.append("email",email)
    }
    if(!!role)
    {
      this.roleList.forEach((res)=>{
        if(res.name===role)
        {
          searchParams=searchParams.append("roleId",res.roleId)
        }
      })
    }

    if(!!location)
    {
      this.locationList.forEach((res)=>{
        if(res.name===location)
        {
          searchParams=searchParams.append("locationId",res.locationId)
        }
      })
    }
    if(!!product)
    {
      this.productList.forEach((res)=>{
        if(res.name===product)
        {
          searchParams=searchParams.append("productId",res.productId)
        }
      })
    }
    this.http.get<EmployeeResponse>("http://localhost:8080/employees/search",{
      params:searchParams
    }).subscribe({
      next:(response)=>{
        this.employeeList=response.employees
        this.employeeSubject.next(response)
      },
      error:(err)=>{
        console.log(err)
      }
    })
  }

  deleteEmployee(empId:number,pageIndex:number,pageSize:number)
  {
     this.http.delete<{success:string,message:string}>("http://localhost:8080/employee/"+empId)
     .subscribe({next:(response)=>{
      console.log(response.message)
      this.fetchEmp(pageIndex+1,pageSize)
    },
    error:(error)=>{
      console.log(error)
    }  });
  }

}
