package com.example.employeedatabase;

public class employeeModel {
     String empID;
     String empName;
     String empPosition;
     String empAddress;
    String empGender;

    public employeeModel()
    {

    }
    public employeeModel(String empID,String empName, String empPosition,String empAddress,String empGender)
    {
        this.empAddress=empAddress;
        this.empID = empID;
        this.empName =empName;
        this.empPosition=empPosition;
        this.empGender = empGender;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public String getEmpGender() {
        return empGender;
    }

    public String getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }

}
