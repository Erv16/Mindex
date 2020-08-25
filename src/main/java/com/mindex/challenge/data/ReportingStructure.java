package com.mindex.challenge.data;

/**
 * Class Definiton for Reporting Structure object for task 1 of the assessment
 * Properties of the object and methods for accessing its properties
 *
 * @author Erwin Palani
 */
public class ReportingStructure {

    private Employee employee;
    private int numberOfReports;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    @Override
    public String toString() {
        return "ReportingStructure{" +
                "employee=" + employee +
                ", numberOfReports=" + numberOfReports +
                '}';
    }
}
