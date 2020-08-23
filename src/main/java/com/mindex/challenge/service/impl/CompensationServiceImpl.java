package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Employee employee = employeeRepository.findByEmployeeId(compensation.getEmployeeId());
        if(employee == null) {
            System.out.println("Employee doesn't exist");
        }

        return compensationRepository.insert(compensation);
    }

    @Override
    public Compensation readCompensation(String employeeId) {

        Compensation compensation = compensationRepository.findCompensationByEmployeeId(employeeId);
        if(compensation == null) {
            System.out.println("Could not find compensation as employee doesn't exist");
        }

        return compensation;
    }
}
