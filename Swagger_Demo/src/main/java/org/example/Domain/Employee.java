package org.example.Domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class Employee {
    @Schema(required = true,type="String",name = "EmployeeName",description = "Provide the name of the employee",example = "Raja")
    private String employeName;
    @Schema(required = true,type="String",name = "EmployeId",description = "Provide the id of the employee",example = "1847")
    private String employeId;
    @Schema(required = true,type="String",name = "Integer",description = "Provide the salary of the employee",example = "24400")
    private Integer employeSalary;
    @Schema(required = true,type="String",name = "EmploymentType",description = "Provide the type of the employee",example = "Permanent/temporary")
    private String employmentType;

    public Employee(String employeName, String employeId, Integer employeSalary, String employmentType) {
        this.employeName = employeName;
        this.employeId = employeId;
        this.employeSalary = employeSalary;
        this.employmentType = employmentType;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getEmployeName() {
        return employeName;
    }

    public void setEmployeName(String employeName) {
        this.employeName = employeName;
    }

    public String getEmployeId() {
        return employeId;
    }

    public void setEmployeId(String  employeId) {
        this.employeId = employeId;
    }

    public Integer getEmployeSalary() {
        return employeSalary;
    }

    public void setEmployeSalary(Integer employeSalary) {
        this.employeSalary = employeSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeName, employee.employeName) && Objects.equals(employeId, employee.employeId) && Objects.equals(employeSalary, employee.employeSalary) && Objects.equals(employmentType, employee.employmentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeName, employeId, employeSalary, employmentType);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeName='" + employeName + '\'' +
                ", employeId=" + employeId +
                ", employeSalary=" + employeSalary +
                ", employmentType='" + employmentType + '\'' +
                '}';
    }
}
