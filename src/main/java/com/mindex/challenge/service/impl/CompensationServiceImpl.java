package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.exception.CompensationNotFoundException;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    private CompensationRepository compensationRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public CompensationServiceImpl(CompensationRepository compensationRepository, EmployeeRepository employeeRepository) {

        this.compensationRepository = compensationRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Compensation createCompensation(Compensation compensation) {

        LOG.debug("Creating compensation [{}]", compensation);

        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByEmployeeId(compensation.getEmployeeId()));
        if(!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with employee id " + compensation.getEmployeeId() + " does not exist");
        }

        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation readCompensation(String employeeId) {

        LOG.debug("Reading compensation of employee with id [{}]", employeeId);

        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByEmployeeId(employeeId));
        if(!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with employee id " + employeeId + " does not exist");
        }

        return compensationRepository.findCompensationByEmployeeId(employeeId);
    }

    @Override
    public Compensation updateCompensation(Compensation compensation) {
        LOG.debug("Updating compensation of employee with id [{}]", compensation.getEmployeeId());

        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByEmployeeId(compensation.getEmployeeId()));
        if(!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with employee id " + compensation.getEmployeeId() + " does not exist");
        }
        Optional<Compensation> compensationExists = Optional.ofNullable(compensationRepository.findCompensationByEmployeeId(compensation.getEmployeeId()));
        if(!compensationExists.isPresent()) {
            throw new CompensationNotFoundException("Compensation for employee id " + compensation.getEmployeeId() + " does not exist");
        }

        return compensationRepository.save(compensation);
    }
}
