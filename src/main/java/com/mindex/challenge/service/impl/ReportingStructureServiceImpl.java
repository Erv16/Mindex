package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    private EmployeeRepository employeeRepository;

    @Autowired
    public ReportingStructureServiceImpl(EmployeeRepository employeeRepository) {

        LOG.debug("Autowired instance of Employee Repository");
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ReportingStructure generateNumberOfReports(String employeeId) {

        LOG.debug("Generating number of reports for employee [{}]", employeeId);
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

        LOG.debug("Calculating number of reports for employee [{}]", employee.getEmployeeId());
        int numberOfReportees = 0;
        List<Employee> employeeDirectReports = employee.getDirectReports();
        if(employeeDirectReports != null && employeeDirectReports.size() != 0) {
            for (Employee reportee : employeeDirectReports) {
                Employee tempEmployee = employeeRepository.findByEmployeeId(reportee.getEmployeeId());
                reportee.setEmployeeId(tempEmployee.getEmployeeId());
                reportee.setFirstName(tempEmployee.getFirstName());
                reportee.setLastName(tempEmployee.getLastName());
                reportee.setDepartment(tempEmployee.getDepartment());
                reportee.setPosition(tempEmployee.getPosition());
                reportee.setDirectReports(tempEmployee.getDirectReports());
                numberOfReportees += numberOfReports(tempEmployee);
                numberOfReportees++;
            }
        }

        return numberOfReportees;
    }
}
