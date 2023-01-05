module pl.edziennik.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires lombok;
    requires java.logging;
    requires spring.core;
    requires spring.web;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens pl.edziennik.client to javafx.fxml;
    exports pl.edziennik.client;
    exports pl.edziennik.client.controller.auth;
    exports pl.edziennik.client.rest;
    exports pl.edziennik.client.controller;
    exports pl.edziennik.client.controller.admin;
    exports pl.edziennik.client.rest.client;
    exports pl.edziennik.client.rest.pojo;
    exports pl.edziennik.client.task;
    exports pl.edziennik.client.exception;
    exports pl.edziennik.client.utils;
    exports pl.edziennik.client.validator.auth;
    exports pl.edziennik.client.controller.model.admin;
    opens pl.edziennik.client.controller.auth to javafx.fxml;
    opens pl.edziennik.client.controller.admin to javafx.fxml;
    opens pl.edziennik.client.controller to javafx.fxml;
    opens pl.edziennik.client.task to javafx.fxml;
    exports pl.edziennik.client.rest.common;

}