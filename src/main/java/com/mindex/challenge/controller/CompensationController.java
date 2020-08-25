package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "REST API calls pertaining to employee compensations")
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    private CompensationService compensationService;

    @Autowired
    public CompensationController(CompensationService compensationService) {

        LOG.debug("Autowired instance of Compensation Service");
        this.compensationService = compensationService;
    }

    @PostMapping("/compensation")
    @ApiOperation(value = "Creates a compensation record for an existing employee", notes = "Returns the newly created compensation record of an employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Compensation createCompensation(@RequestBody Compensation compensation) {

        LOG.debug("Received compensation create request for [{}]", compensation.getEmployeeId());
        return compensationService.createCompensation(compensation);
    }

    @PutMapping("/compensation/{employeeId}")
    @ApiOperation(value = "Updates a compensation record for an existing employee", notes = "Returns the updated compensation record of an employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Compensation updateCompensation(@PathVariable String employeeId, @RequestBody Compensation compensation) {

        LOG.debug("Received compensation update request for [{}]", employeeId);
        compensation.setEmployeeId(employeeId);
        return compensationService.updateCompensation(compensation);
    }

    @GetMapping("/compensation/{employeeId}")
    @ApiOperation(value = "Retrieves the compensation record of an existing employee", notes = "Returns the compensation record of an employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Compensation readCompensation(@PathVariable String employeeId) {

        LOG.debug("Received compensation read request for [{}]", employeeId);
        Compensation compensations = compensationService.readCompensation(employeeId);
        return compensationService.readCompensation(employeeId);
    }

}
