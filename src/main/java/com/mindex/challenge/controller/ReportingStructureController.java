package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {

    @GetMapping
    public ReportingStructure getNumberOfReports() {
        return null;
    }
}
