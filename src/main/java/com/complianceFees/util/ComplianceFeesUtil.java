package com.complianceFees.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ComplianceFeesUtil {

    public static final String MODEL_RESPONSE_TYPE = "application/json";


    @Value("${compliance.fees.list}")
    private String complianceFeesList;


    /**
     * Method to get prompt w.r.t compliance fees checking
     * for the provided upcNumber and stateCode.
     *
     * @param upcNumber -> UPC Number for which compliance fees needs to be checked.
     * @param stateCode -> State for which compliance fees needs to be checked.
     * @param options -> GoogleApiModelOptions
     * @return -> Prompt
     */
    @NotNull
    public Prompt getComplianceFeesPrompt(String upcNumber, String stateCode, GoogleGenAiChatOptions options) {

        String message = "You are a compliance fee evaluator. Consider UPC number i.e. " + upcNumber + " and U.S. state i.e. " + stateCode + ", first infer or identify what type of product the UPC corresponds to (e.g., electronic device, battery-embedded product, mattress, etc.)." +
                " Then determine which of these fees apply: " + complianceFeesList + " **STRICTLY** Respond ONLY in JSON array format." +
                " Each object in the array **MUST** contain the following keys: {\"productDescription\": \"Product Description\",\"complianceFee\": \"Fee Name\", \"feeAmount\": \"Amount\", \"briefExplanation\": \"Brief Explanation\"}." +
                " **IMPORTANT: If a fee applies, populate all fields.**" +
                " **If NO fees apply, or if the UPC is UNKNOWN, you MUST still return a single object with the productDescription and briefExplanation populated, but set the 'complianceFee', and 'feeAmount' fields to an empty string: \"\".**" +
                " **ABSOLUTELY DO NOT provide ANY text outside of the JSON array.**";

        return new Prompt(
                new UserMessage(message),
                options
        );
    }
}
