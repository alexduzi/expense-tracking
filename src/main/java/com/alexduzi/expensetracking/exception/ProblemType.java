package com.alexduzi.expensetracking.exception;

public enum ProblemType {
    DATABASE_EXCEPTION("/dbexception", "Db error"),
    ENTITY_ALREADY_EXISTS_EXCEPTION("/entity-already-exists", "Entity already exists"),
    ENTITY_NOT_FOUND_EXCEPTION("/entity-not-found", "Entity not found"),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION("/method-argument-not-valid", "Validation error");

    private String title;
    private String uri;

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }

    ProblemType(String path, String title) {
        this.uri = "https://expense-tracking.com.br/" + path;
        this.title = title;
    }
}
