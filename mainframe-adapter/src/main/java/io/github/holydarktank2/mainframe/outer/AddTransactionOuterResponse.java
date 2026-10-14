package io.github.holydarktank2.mainframe.outer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddTransactionOuterResponse {
    private String transactionType;
    private String responseCode;
    private String responseMessage;
    private final Map<String, Map<String, Object>> sections = new HashMap<>();

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void putSectionField(String section, String field, Object value) {
        sections.computeIfAbsent(section, ignored -> new HashMap<>()).put(field, value);
    }

    public Map<String, Object> getSection(String section) {
        return sections.getOrDefault(section, Collections.emptyMap());
    }

    public String getString(String section, String field) {
        Object value = getSection(section).get(field);
        return value == null ? null : value.toString();
    }
}
