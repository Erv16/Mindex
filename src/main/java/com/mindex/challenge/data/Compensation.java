package com.mindex.challenge.data;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * Class Definiton for Compensation object for task 2 of the assessment
 * Properties of the object and methods for accessing its properties
 *
 * @author Erwin Palani
 */
public class Compensation {

    @Id
    private String employeeId;
    private double salary;
    private LocalDate effectiveDate;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "Compensation{" +
                "employeeId='" + employeeId + '\'' +
                ", salary=" + salary +
                ", effectiveDate=" + effectiveDate +
                '}';
    }
}
