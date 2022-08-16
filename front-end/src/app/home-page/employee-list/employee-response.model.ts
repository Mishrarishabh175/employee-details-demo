export interface nammable{
    name:String;
}
export interface Role extends nammable{
    roleId:number;
    name:String;
}
export interface Product extends nammable{
    productId:number;
    name:String;
}
export interface LocationEmp extends nammable{
    locationId:number;
    name:string;
}
export interface Employee{
    id:number;
    firstName:string;
    lastName:string;
    email:string;
    location:{locationId:number,name:string};
    role:{roleId:number,name:string};
    product:{productId:number,name:string};
    address: {
        addressId: number;
        lane1: string;
        lane2: string;
        street: string;
        city: string;
        state: string;
        pinCode: number;
    };
}
export interface EmployeeResponse{
    employees:Employee[];
    totalPages:number;
    totalEntries:number;
    entries:number;
    pageNumber:number;
    hasNext:boolean;
    hasPrevious:boolean;
}
export interface EmployeeRequest{
    id:number;
    firstName:string;
    lastName:string;
    email:string;
    locationId:number;
    roleId:number;
    productId:number;
    address: {
        addressId: number;
        lane1: string;
        lane2: string;
        street: string;
        city: string;
        state: string;
        pinCode: number;
    };
}
export interface User{
    username:string;
    password:string;
}