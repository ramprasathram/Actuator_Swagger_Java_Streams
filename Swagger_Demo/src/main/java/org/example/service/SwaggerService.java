package org.example.service;

import io.swagger.v3.oas.models.examples.Example;
import org.example.Domain.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.openmbean.InvalidOpenTypeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SwaggerService {
    List<Employee> employeeList=new ArrayList<>();

    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    public ResponseEntity<String> addAnEmployee(Employee employee) {
        employeeList.add(employee);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAnEmployee(String id) {
        employeeList.removeIf(emp->emp.getEmployeId().equalsIgnoreCase(id));
        return new ResponseEntity<>("Success  "+employeeList,HttpStatus.OK);
    }

    public ResponseEntity<List<Employee>> getAnEmployeeById(String id) {
        return new ResponseEntity<>(employeeList.stream().
                filter(emp->emp.getEmployeId().equalsIgnoreCase(id)).collect(Collectors.toList()),HttpStatus.OK);
    }

    public ResponseEntity<String> increment(String incrementAmount) {
        List<Employee> incrementEmployeList=employeeList.stream().map(employee -> {
            int increment=Integer.parseInt(incrementAmount)+employee.getEmployeSalary();
            employee.setEmployeSalary(increment);
            return employee;
        }).toList();
        return new ResponseEntity<>(employeeList.toString(),HttpStatus.OK);
    }

    public ResponseEntity<String> Incrementbypartition(String type) {
                List<Employee> finalList=employeeList.stream().
                collect(Collectors.partitioningBy(employee -> employee.getEmploymentType().equalsIgnoreCase(type)))
                        .get(true)
                        .stream().map(employee -> {
                            Integer incrementamount=200+employee.getEmployeSalary();
                            employee.setEmployeSalary(incrementamount);
                            return employee;
                        }).collect(Collectors.toList());
                Map<Boolean,List<Employee>> emp1 = employeeList.stream().
                collect(Collectors.partitioningBy(employee -> employee.getEmploymentType().equalsIgnoreCase(type)));
        return new ResponseEntity<>(finalList.toString(),HttpStatus.OK);
    }

    public ResponseEntity<String> loadEmploye() {
        employeeList.add(new Employee("A","1",100,"Permanent"));
        employeeList.add(new Employee("B","2",200,"Temporary"));
        employeeList.add(new Employee("C","3",300,"Permanent"));
        employeeList.add(new Employee("D","4",400,"Temporary"));
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);

    }

    public void supplierFunctionalInterface(){
        Supplier<String> sup=()->{
            String date=LocalDate.now().toString();
            String dateFormat="yyyy/MM/dd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            formatter.parse(date);
            return date;
        };
    }
    public  void consumerFunctionalInterface(){
        List<Integer> integerList=new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);

        Consumer<List<Integer>> consumer=(list)->{
            for(Integer i:list){
                System.out.println(i+15);
            }

        };
        consumer.accept(integerList);
    }

    /*public boolean PredicateFunctionalInterface(){
        String date=LocalDate.now().toString();
        String dateFormat="yyyy/MM/dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        Predicate<Object> pr=(date)-{
            Boolean iscorrect;
          try{
              formatter.parse(date);
              iscorrect= true;
          }
          catch (Exception e){
              iscorrect= false;
          }
          
        };
    }*/

    //Stream Reduce Method Example
    //Finding the average salary of all the employees
    Double getsumsalary=employeeList
            .stream().
            mapToDouble(employee-> employee.getEmployeSalary()).
            reduce(0.00,(emp1,emp2)->emp1+emp2);

    /*Stream Anymatch Example
    Takes in a predicate function and returs true if any of the element passes the condition
    will not check all the elements of the given collection return true and terminate if any of the object passes the predicate condition*/
    public void anymatchexample(){
        Boolean istemp=employeeList.stream().anyMatch(employee ->
        { System.out.println("Searching");
            return employee.getEmploymentType().equalsIgnoreCase("Permanent");
        });
    }

    /*Stream AllMatch Example
    Takes in a predicate function and returs true if all  the element passes the condition
    will check all the elements of the given collection return true */
    public void allmatchExample(){
        Boolean istemp1=employeeList.stream().allMatch(employee ->
        { System.out.println("Searching_All_Match");
            return employee.getEmploymentType().equalsIgnoreCase("Permanent");
        });
    }
    /*Stream nonematch Example
    Takes in a predicate function and checks whether the given Condition is met or not.
        will return true if the defined condition is not matched and false when the condition becomes true*/
    public void nonematchExample(){
        Boolean istemp1=employeeList.stream().noneMatch(employee ->
        { System.out.println("Searching_All_Match");
            return employee.getEmploymentType().equalsIgnoreCase("Permanent");
        });
    }

    /*Stream FindFirst Example
    Returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty.
    If the stream has no encounter order, then any element may be returned.
    more powerful when combining it with filter methods of stream*/
    public void findFirstExample(){
        Employee employeeFindFirst=employeeList.stream().filter(employee -> employee.getEmploymentType().equalsIgnoreCase("permanent")).findFirst().get();
    }

    /*Stream FindAny Example
    Returns an Optional describing any  element of this stream, or an empty Optional if the stream is empty.
    If the stream has no encounter order, then any element may be returned.
    more powerful when combining it with filter methods of stream*/
    public void findAnyExample(){
        Employee employeeFindFirst=employeeList.stream().filter(employee -> employee.getEmploymentType().equalsIgnoreCase("permanent")).findAny().get();
    }
}
