package com.complianceFees.controller;

import com.complianceFees.util.ComplianceFeesUtil;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/v1/api")
public class ComplianceFeeController {

    @Autowired
    private GoogleGenAiChatModel chatModel;

    @Autowired
    private ComplianceFeesUtil complianceFeesUtil;

    @Value("${spring.ai.google.genai.chat.options.model}")
    private String modelName;
    
    @GetMapping("/checkComplianceFees")
    public String checkComplianceFees(@RequestParam String upcNumber, @RequestParam String stateCode) {

        GoogleGenAiChatOptions options = GoogleGenAiChatOptions.builder()
                .model(modelName)
                .googleSearchRetrieval(true)     // IMPORTANT — enables Google Search
                .temperature(0.0)
                .responseMimeType(ComplianceFeesUtil.MODEL_RESPONSE_TYPE)
                .build();

        Prompt prompt = complianceFeesUtil.getComplianceFeesPrompt(upcNumber, stateCode, options);

        long start = System.currentTimeMillis();
        ChatResponse response = chatModel.call(prompt);
        long end = System.currentTimeMillis();

        long durationMs = end - start;
        System.out.println("Time taken by the model in milliseconds :: " + durationMs + " ms");

        String jsonResponse = response != null && response.getResult() != null ? response.getResult().getOutput().getText() : "";

        if (!StringUtils.isEmpty(jsonResponse) && jsonResponse.startsWith("```json")) {
            jsonResponse = jsonResponse.replaceAll("```json", "").replaceAll("```", "").trim();
        }

        return jsonResponse;

    }

}