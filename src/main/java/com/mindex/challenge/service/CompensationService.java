package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * Interface for task 2 of the assessment that contains
 * methods to create, read and update compensation
 * records of an existing employee
 *
 * @author Erwin Palani
 */
public interface CompensationService {

    Compensation createCompensation(Compensation compensation);
    Compensation readCompensation(String employeeId);
    Compensation updateCompensation(Compensation compensation);
}
