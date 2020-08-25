package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public ReportingStructureServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ReportingStructure generateNumberOfReports(String employeeId) {

        ReportingStructure reportingStructure = new ReportingStructure();
        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByEmployeeId(employeeId));
        if(!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with employee id " + employeeId + " does not exist");
        }
        reportingStructure.setEmployee(employee.get());
        reportingStructure.setNumberOfReports(numberOfReports(employee.get()));
        return reportingStructure;
    }

    public int numberOfReports(Employee employee) {

        int numberOfReportees = 0;
        List<Employee> employeeDirectReports = employee.getDirectReports();
        if(employeeDirectReports != null || employeeDirectReports.size() != 0) {
            for(Employee reportee: employeeDirectReports) {
                numberOfReportees += reportee.getDirectReports().size();
                numberOfReportees++;
            }
        }

        return numberOfReportees;
    }
}
