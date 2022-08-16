import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EmployeeListService } from '../../employee-list.service';

@Component({
  selector: 'app-dialog-box',
  templateUrl: './dialog-box.component.html',
  styleUrls: ['./dialog-box.component.css']
})
export class DialogBoxComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: {pageIndex:number,pageSize:number,empId:number},
  private employeeListService:EmployeeListService) {}

  ngOnInit(): void {
  }
  onDelete(){
    this.employeeListService.deleteEmployee(this.data.empId,this.data.pageIndex,this.data.pageSize);
  }
}
