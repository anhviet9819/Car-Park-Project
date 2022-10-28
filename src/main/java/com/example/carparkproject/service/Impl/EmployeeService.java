package com.example.carparkproject.service.Impl;

import com.example.carparkproject.entity.Bookingoffice;
import com.example.carparkproject.entity.Employee;
import com.example.carparkproject.exception.ResourceNotFoundException;
import com.example.carparkproject.repository.EmployeeRepository;
import com.example.carparkproject.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> getById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found employee lot with id: "+ id));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> updateById(Long id, Employee employee) {
        Employee employee1 = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found employee with id = " + id));

        if(employee.getEmployeeAddress() != null){
            employee1.setEmployeeAddress(employee.getEmployeeAddress());
        }
        if(employee.getEmployeeBirthdate() != null){
            employee1.setEmployeeBirthdate(employee.getEmployeeBirthdate());
        }
        if(employee.getEmployeeEmail() != null){
            employee1.setEmployeeEmail(employee.getEmployeeEmail());
        }
        if(employee.getEmployeeName() != null){
            employee1.setEmployeeName(employee.getEmployeeName());
        }
        if(employee.getEmployeePhone() != null){
            employee1.setEmployeePhone(employee.getEmployeePhone());
        }
        if(employee.getAccount() != null){
            employee1.setAccount(employee.getAccount());
        }
        if(employee.getDepartment() != null){
            employee1.setDepartment(employee.getDepartment());
        }
        if(employee.getSex() != null){
            employee1.setSex(employee.getSex());
        }
        if(employee.getPassword() != null){
            employee1.setPassword(employee.getPassword());
        }
        return new ResponseEntity<>(employeeRepository.save(employee1), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> create(Employee employee) {
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found employee with id = " + id));

        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
