package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompensationServiceImpl implements CompensationService {

    private CompensationRepository compensationRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public CompensationServiceImpl(CompensationRepository compensationRepository, EmployeeRepository employeeRepository) {
        this.compensationRepository = compensationRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Compensation createCompensation(Compensation compensation) {

        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByEmployeeId(compensation.getEmployeeId()));
        if(!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with employee id " + compensation.getEmployeeId() + " does not exist");
        }
        return compensationRepository.insert(compensation);
    }

    @Override
    public Compensation readCompensation(String employeeId) {

        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByEmployeeId(employeeId));
        if(!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with employee id " + employeeId + " does not exist");
        }

        return compensationRepository.findCompensationByEmployeeId(employeeId);
    }
}
