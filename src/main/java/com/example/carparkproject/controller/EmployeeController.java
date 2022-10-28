package com.example.carparkproject.controller;

import com.example.carparkproject.entity.Employee;
import com.example.carparkproject.entity.Parkinglot;
import com.example.carparkproject.exception.InvalidRequestBodyException;
import com.example.carparkproject.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getAllEmployy(){
        return employeeService.getAll();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")Long id){
        return employeeService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody @Validated Employee employee,
                                                   BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
//        if(!employee.getSex().equals("F") & !employee.getSex().equals("M") & !employee.getSex().equals("O")){
//            throw new InvalidRequestBodyException("Sex type only equals F(female), M(male), O(other)!");
//        }
        return employeeService.create(employee);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<Employee> updateEmployeeById(@RequestBody @Validated Employee employee,
//                                                           BindingResult bindingResult, @PathVariable("id")Long id) throws BindException {
//        if(bindingResult.hasErrors()){
//            throw new BindException(bindingResult);
//        }
//        return employeeService.updateById(id, employee);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee,
                                                        @PathVariable("id")Long id){
//        if(bindingResult.hasErrors()){
//            throw new BindException(bindingResult);
//        }
        return employeeService.updateById(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("id")Long id){
        return employeeService.deleteById(id);
    }

}
