package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

/**
 * Interface for task 1 of the assessment that contains a method
 * to generate the number of reports of a given employee
 *
 * @author Erwin Palani
 */
public interface ReportingStructureService {

    ReportingStructure generateNumberOfReports(String employeeId);
}
