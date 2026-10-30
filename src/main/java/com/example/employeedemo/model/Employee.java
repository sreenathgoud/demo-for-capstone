package com.example.employeedemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "employees")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String name;
    @NotBlank @Email @Size(max = 190)
    @Column(nullable = false, unique = true, length = 190)
    private String email;
    @NotBlank @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String department;
    @NotNull @DecimalMin("0.00") @Digits(integer = 10, fraction = 2)
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salary;

    protected Employee() {}
    public Employee(String name, String email, String department, BigDecimal salary) {
        this.name = name; this.email = email; this.department = department; this.salary = salary;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
}
