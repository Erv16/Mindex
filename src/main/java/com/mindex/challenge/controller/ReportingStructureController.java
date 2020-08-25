package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class for Task 1 of the assessment related to Number of Reports
 * The controller helps in mapping and redirecting the request for
 * calculating the number of reports for an employee
 *
 * @author Erwin Palani
 */

@RestController
@Api(description = "REST API call to calculate number of reports for an employee")
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    private ReportingStructureService reportingStructureService;

    @Autowired
    public ReportingStructureController(ReportingStructureService reportingStructureService) {

        LOG.debug("Autowired instance of Reporting Structure Service");
        this.reportingStructureService = reportingStructureService;
    }

    /**
     * Calculates the number of unique direct reports for an existing employee
     * and creates the appropriate reporting structure
     *
     * @param employeeId        employee id whose direct reports
     *                          need to be calculated
     * @return                  count of direct reports reporting
     *                          to the employee
     */
    @GetMapping("/reporting/{employeeId}")
    @ApiOperation(value = "Calculates the number of reports for an employee", notes = "Returns the number of employees that report to a particular employee and the reporting structure")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ReportingStructure getNumberOfReports(@PathVariable String employeeId) {

        LOG.debug("Received Number of reports read request for [{}]", employeeId);
        return reportingStructureService.generateNumberOfReports(employeeId);
    }
}
