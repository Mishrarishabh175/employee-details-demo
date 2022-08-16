import {  ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {  PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { EmployeeListService } from 'src/app/home-page/employee-list.service';
import { DialogBoxComponent } from './dialog-box/dialog-box.component';
import { Employee, EmployeeResponse } from './employee-response.model';


@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit,OnDestroy {

  displayedColumns=['position','firstname','lastname','email','product','role','location','action']
  employeeResponse!:EmployeeResponse;
  dataSource!:Employee[];
  employeeSubscriber!:Subscription;

  length!:number;
  pageSize = 10;
  pageSizeOptions: number[] = [1,2,5, 10, 25, 100];
  pageIndex=0;

  constructor(private employeeListService:EmployeeListService,
    private router:Router,
    private ref:ChangeDetectorRef,
    private dialog:MatDialog) { }

  ngOnInit(): void {
    this.employeeListService.fetchEmp(this.pageIndex+1,this.pageSize);
    this.employeeSubscriber = this.employeeListService.employeeSubject.subscribe((res)=>{
      if(!!res)
      {
        this.employeeResponse=res;
        this.length=res.totalEntries;
        this.dataSource=res.employees;
      }

    })
  }

  onPaginatorChange(event:PageEvent)
  {
    this.pageIndex=event.pageIndex
    this.pageSize=event.pageSize
    this.employeeListService.fetchEmp(this.pageIndex+1,this.pageSize);
  }

  ngOnDestroy(): void {
    this.employeeSubscriber.unsubscribe();
  }
  onEdit(employee:Employee){
    this.router.navigate(["/edit",employee.id]);
  }
  onDelete(empId:number){
    this.openDialog('0ms','0ms',empId)
  }
  openDialog(enterAnimationDuration: string, exitAnimationDuration: string,empId:number): void {
    this.dialog.open(DialogBoxComponent, {
      width: '250px',
      enterAnimationDuration,
      exitAnimationDuration,
      data:{
        pageIndex:this.pageIndex,
        pageSize:this.pageSize,
        empId:empId
      }
    });
  }
}
