package com.example.employeedemo.service;

import com.example.employeedemo.model.Employee;
import com.example.employeedemo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) { this.repository = repository; }
    public Employee create(Employee employee) { return repository.save(employee); }
    @Transactional(readOnly = true)
    public List<Employee> findAll() { return repository.findAll(); }
    @Transactional(readOnly = true)
    public Employee findById(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + id)); }
    public Employee update(Long id, Employee changes) {
        Employee employee = findById(id);
        employee.setName(changes.getName()); employee.setEmail(changes.getEmail());
        employee.setDepartment(changes.getDepartment()); employee.setSalary(changes.getSalary());
        return repository.save(employee);
    }
    public void delete(Long id) { if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + id); repository.deleteById(id); }
}
