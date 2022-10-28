package com.example.carparkproject.service;

import com.example.carparkproject.entity.Bookingoffice;
import com.example.carparkproject.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEmployeeService {
    public ResponseEntity<List<Employee>> getAll();
    public ResponseEntity<Employee> getById(Long id);
    public ResponseEntity<Employee> updateById(Long id, Employee employee);
    public ResponseEntity<Employee> create(Employee employee);
    public ResponseEntity<HttpStatus> deleteById(Long id);
}
