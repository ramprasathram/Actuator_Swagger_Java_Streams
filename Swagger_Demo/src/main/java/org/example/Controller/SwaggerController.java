package org.example.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.Domain.Employee;
import org.example.service.SwaggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Tag(name = "Service to Retrieve EmployeeDetails")
@RequestMapping("employee/v1")
public class SwaggerController {

@Autowired
    SwaggerService swaggerService;
    @GetMapping("/loadEmployees")
    @Operation(summary = "Loads default Employee Information")
    @ApiResponse(responseCode = "200",description = "Success",content = {@Content(schema = @Schema(implementation = Employee.class,type = "Array"))})
    private ResponseEntity<String> loadEmploye(){
        return swaggerService.loadEmploye();
    }
    @GetMapping("/getAllEmployees")
    @Operation(summary = "Gets Information of all the Employees")
    @ApiResponse(responseCode = "200",description = "Success",content = {@Content(schema = @Schema(implementation = Employee.class,type = "Array"))})
    private ResponseEntity<List<Employee>> getAllEmployees(){
        return swaggerService.getAllEmployees();
    }
    @PostMapping("/AddAnEmployee")
    @Operation(summary = "Add Employee Information ")
    private ResponseEntity<String> AddAnEmployee(@RequestBody Employee employee){
        return swaggerService.addAnEmployee(employee);
    }
    @DeleteMapping("/deleteAnEmployee/{id}")
    @Operation(summary = "Delete Employee Information ")
    @Parameter(name = "id",description = "Unique ID to each Employee",required = true,schema = @Schema(type = "String"),in = ParameterIn.PATH)
    @ApiResponse(responseCode = "200",description = "Success",content = {@Content(schema = @Schema(implementation = String.class))})
    private ResponseEntity<String> deleteAnEmployee(@PathVariable String id){
        return swaggerService.deleteAnEmployee(id);
    }
    @GetMapping("/getAnEmployeeById/{id}")
    @Operation(summary = "Get Information about an  Employee By EmployeeId")
    @Parameter(name = "id",description = "Unique ID to each Employee",required = true,schema = @Schema(type = "String"),in = ParameterIn.PATH)
    @ApiResponse(responseCode = "200",description = "Success",content = {@Content(schema = @Schema(implementation = Employee.class,type = "Array"))})
    private ResponseEntity<List<Employee>> getAllEmployees(@PathVariable String id){
        return swaggerService.getAnEmployeeById(id);
    }
    @PostMapping("/Increment/{IncrementAmount}")
    @Operation(summary = "Increment Api")
    @Parameter(name="IncrementAmount",description = "Provide the increment percentage",required = true,schema = @Schema(type="String"),in=ParameterIn.PATH)
    private ResponseEntity<String> increment(@PathVariable String IncrementAmount){
        return swaggerService.increment(IncrementAmount);
    }
    @PostMapping("/employeepartition")
    @Operation(summary = "Increment Api")
    @Parameter(name="type",description = "Provide the increment To certain Employees",required = true,schema = @Schema(type="String"),in=ParameterIn.PATH)
    private ResponseEntity<String> Incrementbypartition(@RequestParam String type){
        return swaggerService.Incrementbypartition(type);
    }
    @GetMapping("/contactInfo")
    private ResponseEntity<String> contactInfo(){
        return new ResponseEntity<>("Anandan Umasankar \n anandanumasankar@gmail.com", HttpStatus.OK);
    }
}

