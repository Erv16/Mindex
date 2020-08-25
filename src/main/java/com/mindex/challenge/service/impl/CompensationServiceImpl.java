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

import java.util.Optional;

/**
 * Service implementation that includes the business logic for
 * creation, updating and reading of compensation records for
 * an existing employee
 *
 * @author Erwin Palani
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    private CompensationRepository compensationRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public CompensationServiceImpl(CompensationRepository compensationRepository, EmployeeRepository employeeRepository) {

        LOG.debug("Autowired instances of Compensation and Employee Repositories");
        this.compensationRepository = compensationRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Creates a new compensation record for an existing employee
     *
     * @param compensation      compensation contains the employee
     *                          details, salary and effective date
     *                          which is forwarded onto the service
     *                          for processing
     * @return                  compensation record that has been
     *                          created
     */
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

    /**
     * Retrieves the compensation record for an employee if the employee exists
     *
     * @param employeeId        employee id whose compensation
     *                          needs to be retrieved
     * @return                  compensation record of the employee
     */
    @Override
    public Compensation readCompensation(String employeeId) {

        LOG.debug("Reading compensation of employee with id [{}]", employeeId);

        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByEmployeeId(employeeId));
        if(!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with employee id " + employeeId + " does not exist");
        }

        return compensationRepository.findCompensationByEmployeeId(employeeId);
    }

    /**
     * Updates the compensation record of an existing employee
     *
     * @param compensation      compensation values that will be
     *                          updated
     * @return                  updated compensation record
     */
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
