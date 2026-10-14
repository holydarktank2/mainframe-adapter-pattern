package io.github.holydarktank2.mainframe.outer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddTransactionOuterRequest {
    private String transactionType;
    private final Map<String, Map<String, Object>> sections = new HashMap<>();

    public String getTransactionType() {
        return transactionType;
    }

    public Map<String, Map<String, Object>> getSections() {
        Map<String, Map<String, Object>> copy = new HashMap<>();
        sections.forEach((key, value) -> copy.put(key, Collections.unmodifiableMap(value)));
        return Collections.unmodifiableMap(copy);
    }

    public Map<String, Object> getSection(String section) {
        return sections.getOrDefault(section, Collections.emptyMap());
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void putSectionField(String section, String field, Object value) {
        sections.computeIfAbsent(section, ignored -> new HashMap<>()).put(field, value);
    }
}
