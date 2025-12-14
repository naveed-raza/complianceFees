package com.complianceFees.controller;

import com.complianceFees.service.ComplianceFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/v1/api")
public class ComplianceFeeController {

    @Autowired
    private ComplianceFeeService complianceFeeService;

    @GetMapping("/checkComplianceFees")
    public String checkComplianceFees(@RequestParam String upcNumber, @RequestParam String stateCode) {
        return complianceFeeService.checkComplianceFees(upcNumber, stateCode);
    }

}