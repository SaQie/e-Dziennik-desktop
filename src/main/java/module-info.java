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


    exports pl.edziennik.client;
    exports pl.edziennik.client.controller.auth;
    exports pl.edziennik.client.rest;
    exports pl.edziennik.client.controller;
    exports pl.edziennik.client.controller.admin;
    exports pl.edziennik.client.rest.client;
    exports pl.edziennik.client.rest.pojo;
    exports pl.edziennik.client.rest.client.response;
    exports pl.edziennik.client.exception;
    exports pl.edziennik.client.utils;
    exports pl.edziennik.client.validator.auth;
    exports pl.edziennik.client.controller.model.admin;
    exports pl.edziennik.client.controller.configuration;
    exports pl.edziennik.client.common;
    exports pl.edziennik.client.core;
    exports pl.edziennik.client.controller.admin.schools;
    exports pl.edziennik.client.controller.admin.configuration;
    exports pl.edziennik.client.task.register;
    exports pl.edziennik.client.task.school;
    opens pl.edziennik.client to javafx.fxml;
    opens pl.edziennik.client.controller.auth to javafx.fxml;
    opens pl.edziennik.client.controller.admin to javafx.fxml;
    opens pl.edziennik.client.controller to javafx.fxml;
    opens pl.edziennik.client.controller.configuration to javafx.fxml;
    opens pl.edziennik.client.controller.admin.schools to javafx.fxml;
    opens pl.edziennik.client.task.school to javafx.fxml;
    opens pl.edziennik.client.task.register to javafx.fxml;
    opens pl.edziennik.client.controller.admin.configuration to javafx.fxml;
}