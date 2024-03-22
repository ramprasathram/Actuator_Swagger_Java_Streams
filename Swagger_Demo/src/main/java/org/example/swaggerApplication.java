package org.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.example.Domain.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition
public class swaggerApplication {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", "1", 100, "Permanent"));
        employeeList.add(new Employee("B", "2", 200, "Temporary"));
        employeeList.add(new Employee("C", "3", 300, "Permanent"));
        employeeList.add(new Employee("D", "4", 400, "Temporary"));

        //Stream Reduce Method Example
        //Finding the average salary of all the employees
        Double getsumsalary = employeeList
                .stream().
                mapToDouble(Employee::getEmployeSalary).
                reduce(0.00, (emp1, emp2) -> emp1 + emp2);
        swaggerApplication app=new swaggerApplication();
        app.anymatchexample(employeeList);
        app.allmatchExample(employeeList);
        app.nonematchExample(employeeList);
        app.findFirstExample(employeeList);
        app.findAnyExample(employeeList);
    }

    /*Stream Anymatch Example
    Takes in a predicate function and returns true if any of the elements pass the condition
    will not check all the elements of the given collection return true and terminate if any of the object passes the predicate condition*/
    public void anymatchexample(List<Employee> employeeList) {
        Boolean istemp = employeeList.stream().anyMatch(employee ->
        {
            System.out.println("Searching");
            return employee.getEmploymentType().equalsIgnoreCase("Permanent");
        });
    }

    /*Stream AllMatch Example
    Takes in a predicate function and returns true if all the elements pass the condition
    will check all the elements of the given collection return true */
    public void allmatchExample(List<Employee> employeeList) {
        Boolean istemp1 = employeeList.stream().allMatch(employee ->
        {
            System.out.println("Searching_All_Match");
            return employee.getEmploymentType().equalsIgnoreCase("Permanent");
        });
    }

    /*Stream nonematch Example
    Takes in a predicate function and checks whether the given Condition is met or not.
    will return true if the defined condition is not matched and false when the condition becomes true*/
    public void nonematchExample(List<Employee> employeeList) {
        Boolean istemp1 = employeeList.stream().noneMatch(employee ->
        {
            System.out.println("Searching_All_Match");
            return employee.getEmploymentType().equalsIgnoreCase("Permanent");
        });
    }

    /*Stream FindFirst Example
    Returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty.
    If the stream has no encounter order, then any element may be returned.
    more powerful when combining it with filter methods of stream*/
    public void findFirstExample(List<Employee> employeeList) {
        Employee employeeFindFirst = employeeList.stream().filter(employee -> employee.getEmploymentType().equalsIgnoreCase("permanent")).findFirst().get();
    }

    /*Stream FindAny Example
    Returns an Optional describing any element of this stream, or an empty Optional if the stream is empty.
    If the stream has no encounter order, then any element may be returned.
    more powerful when combining it with filter methods of stream*/
    public void findAnyExample(List<Employee> employeeList) {
        Employee employeeFindFirst = employeeList.stream().filter(employee -> employee.getEmploymentType().equalsIgnoreCase("permanent")).findAny().get();
    }
}
