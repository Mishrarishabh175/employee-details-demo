import { R } from '@angular/cdk/keycodes';
import { HttpClient } from '@angular/common/http';
import { AfterContentInit, ChangeDetectorRef, Component, DoCheck, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { exhaustMap, map, Observable, startWith, Subscription } from 'rxjs';
import { EmployeeListService } from '../home-page/employee-list.service';
import { Employee, LocationEmp, EmployeeRequest, Product, Role } from '../home-page/employee-list/employee-response.model';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit,OnDestroy {
  addEmployee!:FormGroup;
  editMode =false
  id!:number
  employee!:Employee;
  roles!:Role[];
  products!:Product[];
  locations!:LocationEmp[];

  roleSubscription!:Subscription;
  productSubscription!:Subscription;
  locationSubscription!:Subscription;


  constructor(private route:ActivatedRoute,
    private router:Router,
    private employeeListService:EmployeeListService,
    private http:HttpClient) { }

  ngOnInit(): void {
    this.employeeListService.fetchRoles();
    this.employeeListService.fetchlocations();
    this.employeeListService.fetchproducts();    

    this.addEmployee=new FormGroup({
      "firstName":new FormControl(null,[Validators.required,Validators.minLength(2),Validators.pattern("^[A-Za-z ]*$")]),
      "lastName":new FormControl(null,[Validators.required,Validators.minLength(2),Validators.pattern("^[A-Za-z ]*$")]),
      "email":new FormControl(null,[Validators.required,Validators.minLength(2),Validators.email]),
      "location": new FormControl(null,[Validators.required]),
      "role":new FormControl(null,[Validators.required]),
      "product": new FormControl(null,[Validators.required]),
      "address":new FormGroup({
        "lane1":new FormControl(null,[Validators.pattern("^[A-Za-z-. 0-9]*$")]),
        "lane2":new FormControl(null,[Validators.pattern("^[A-Za-z-. 0-9]*$")]),
        "street": new FormControl(null,[Validators.pattern("^[A-Za-z- 0-9]*$")]),
        "city": new FormControl(null,[Validators.required,Validators.minLength(2),Validators.pattern("^[A-Za-z-. ]*$")]),
        "state": new FormControl(null,[Validators.required,Validators.minLength(2),Validators.pattern("^[A-Za-z-. ]*$")]),
        "pincode":new FormControl(null,
          [Validators.required,Validators.minLength(6),Validators.maxLength(6),Validators.pattern("^[0-9]*$")])
      })
    })


    if(this.route.snapshot.url[0].path==='edit')
    {
      this.editMode=true;
      this.id= +this.route.snapshot.url[1].path
      this.employee=this.employeeListService.fetchEmployeesFromEdit(this.id)[0];
       this.addEmployee.setValue({
          "firstName":this.employee.firstName,
          "lastName":this.employee.lastName,
          "email":this.employee.email,
          "role":this.employee.role.name,
          "product":this.employee.product.name,
          "location":this.employee.location.name,
          "address":{
            "lane1":this.employee.address.lane1,
            "lane2":this.employee.address.lane2,
            "street":this.employee.address.street,
            "city":this.employee.address.city,
            "state":this.employee.address.state,
            "pincode":this.employee.address.pinCode
          }
        })
    }
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

  ngOnDestroy(): void {
    this.roleSubscription.unsubscribe();
    this.locationSubscription.unsubscribe();
    this.productSubscription.unsubscribe();
  }
  onSubmit(){
    if(!this.addEmployee.valid)
    {
      return;
    }
    console.log(this.addEmployee)
    let addres=<FormGroup>this.addEmployee.controls["address"]
    if(this.editMode)
    {
      this.http.put<{message:string,status:string}>("http://localhost:8080/employees",{
        id:this.id,
        firstName:this.addEmployee.controls["firstName"].value,
        lastName:this.addEmployee.controls['lastName'].value,
        email:this.addEmployee.controls["email"].value,
        locationId: this.locationId(this.addEmployee.controls["location"].value),
        productId:this.productId(this.addEmployee.controls["product"].value),
        roleId:this.roleId(this.addEmployee.controls["role"].value),
        address:{
          addressId:this.employee.address.addressId,
          lane1:addres.controls["lane1"].value,
          lane2:addres.controls["lane2"].value,
          street:addres.controls["street"].value,
          city:addres.controls["city"].value,
          state:addres.controls["state"].value,
          pinCode:addres.controls["pincode"].value
        }
      }).subscribe({next:(response)=>{
        console.log(response.message)
        this.router.navigate(['/home'])
      },error:(error)=>{
        console.log(error)
      }})
    }
    else{
      this.http.post<{message:string,status:string}>("http://localhost:8080/employees",{
        firstName:this.addEmployee.controls["firstName"].value,
        lastName:this.addEmployee.controls['lastName'].value,
        email:this.addEmployee.controls["email"].value,
        locationId: this.locationId(this.addEmployee.controls["location"].value),
        productId:this.productId(this.addEmployee.controls["product"].value),
        roleId:this.roleId(this.addEmployee.controls["role"].value),
        address:{
          lane1:addres.controls["lane1"].value,
          lane2:addres.controls["lane2"].value,
          street:addres.controls["street"].value,
          city:addres.controls["city"].value,
          state:addres.controls["state"].value,
          pinCode:addres.controls["pincode"].value
        }
      }).subscribe({next:(response)=>{
        console.log(response.message)
        this.router.navigate(['/home'])
      },error:(error)=>{
        console.log(error)
      }})
    }
    
  }
  locationId(name:string)
  {
    let id;
    this.locations.forEach((location)=>{if(location.name==name) id=location.locationId})
    return id;
  }
  productId(name:string){
    let id;
    this.products.forEach((product)=>{if(product.name==name) id=product.productId})
    return id;
  }
  roleId(name:string){
    let id;
    this.roles.forEach((role)=>{if(role.name==name) id=role.roleId})
    return id;
  }
}
