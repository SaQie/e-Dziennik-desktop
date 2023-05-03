package pl.edziennik.client.common;

import pl.edziennik.client.util.ResourceUtil;

public enum AccountType {

    WORKER(ResourceUtil.getMessage("worker.combo.box.label")),
    STUDENT(ResourceUtil.getMessage("student.combo.box.label"));

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
