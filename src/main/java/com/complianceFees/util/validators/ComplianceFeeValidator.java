package com.complianceFees.util.validators;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ComplianceFeeValidator {

    private static final Set<String> US_STATES = Set.of(
            "AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA",
            "HI","ID","IL","IN","IA","KS","KY","LA","ME","MD",
            "MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ",
            "NM","NY","NC","ND","OH","OK","OR","PA","RI","SC",
            "SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"
    );

    public boolean isValidUPC(String upcNumber) {
        return upcNumber != null && upcNumber.matches("^\\d{12}$"); // Basic 12-digit UPC validation
    }

    public boolean isValidStateCode(String stateCode) {
        return stateCode != null && US_STATES.contains(stateCode);
    }

}
