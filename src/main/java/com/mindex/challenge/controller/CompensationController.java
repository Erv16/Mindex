package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/compensation")
public class CompensationController {

    private CompensationService compensationService;

    @Autowired
    public CompensationController(CompensationService compensationService) {
        this.compensationService = compensationService;
    }

    @PutMapping
    public Compensation createCompensation(@RequestBody Compensation compensation) {

        return compensationService.createCompensation(compensation);
    }

    @GetMapping("/{employeeId}")
    public Compensation readCompensation(@PathVariable String employeeId) {

        return compensationService.readCompensation(employeeId);
    }

}
