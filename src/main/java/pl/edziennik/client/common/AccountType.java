package pl.edziennik.client.common;

public enum AccountType {

    WORKER("Pracownik"),
    STUDENT("Student");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
