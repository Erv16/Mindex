package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/compensation")
public class CompensationController {

    @PutMapping
    public Compensation createCompensation(@RequestBody Compensation compensation) {

        return null;
    }

    @GetMapping("/{employeeId}")
    public Compensation readCompensation(@PathVariable String employeeId) {

        return null;
    }

}
