package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    private ReportingStructureService reportingStructureService;

    @Autowired
    public ReportingStructureController(ReportingStructureService reportingStructureService) {

        LOG.debug("Autowired instance of Reporting Structure Service");
        this.reportingStructureService = reportingStructureService;
    }

    @GetMapping("/reporting/{employeeId}")
    public ReportingStructure getNumberOfReports(@PathVariable String employeeId) {

        LOG.debug("Received Number of reports read request for [{}]", employeeId);
        return reportingStructureService.generateNumberOfReports(employeeId);
    }
}
