package com.example.employeedemo.controller;

import com.example.employeedemo.model.Employee;
import com.example.employeedemo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody Employee employee) { return service.create(employee); }
    @GetMapping public List<Employee> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public Employee findById(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Employee update(@PathVariable Long id, @Valid @RequestBody Employee employee) { return service.update(id, employee); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
