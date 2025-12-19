package com.complianceFees.controller;

import com.complianceFees.service.ComplianceFeeService;
import com.complianceFees.util.validators.ComplianceFeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/v1/api")
public class ComplianceFeeController {

    @Autowired
    private ComplianceFeeService complianceFeeService;

    @Autowired
    private ComplianceFeeValidator complianceFeeValidator;

    @GetMapping("/checkComplianceFees")
    public String checkComplianceFees(@RequestParam String upcNumber, @RequestParam String stateCode) {

        if (complianceFeeValidator.isValidStateCode(stateCode) && complianceFeeValidator.isValidUPC(upcNumber)) {
            return complianceFeeService.checkComplianceFees(upcNumber, stateCode);
        } else {
            return "Invalid UPC number or state code";
        }
    }

}