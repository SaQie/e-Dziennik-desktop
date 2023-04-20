module pl.edziennik.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires lombok;
    requires java.logging;
    requires spring.core;
    requires spring.web;
    requires spring.webmvc;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.client5.httpclient5;


    exports pl.edziennik.client;
    exports pl.edziennik.client.controller.auth;
    exports pl.edziennik.client.rest;
    exports pl.edziennik.client.controller;
    exports pl.edziennik.client.controller.admin;
    exports pl.edziennik.client.rest.client;
    exports pl.edziennik.client.rest.client.response;
    exports pl.edziennik.client.exception;
    exports pl.edziennik.client.utils;
    exports pl.edziennik.client.validator.auth;
    exports pl.edziennik.client.controller.model.admin;
    exports pl.edziennik.client.controller.configuration;
    exports pl.edziennik.client.common;
    exports pl.edziennik.client.core;
    exports pl.edziennik.client.controller.admin.schools;
    exports pl.edziennik.client.controller.admin.accounts;
    exports pl.edziennik.client.controller.admin.configuration;
    exports pl.edziennik.client.task.register;
    exports pl.edziennik.client.task.school;
    exports pl.edziennik.client.controller.admin.schoolclasses;
    exports pl.edziennik.client.core.toast;
    opens pl.edziennik.client to javafx.fxml;
    opens pl.edziennik.client.controller.auth to javafx.fxml;
    opens pl.edziennik.client.controller.admin to javafx.fxml;
    opens pl.edziennik.client.controller to javafx.fxml;
    opens pl.edziennik.client.controller.configuration to javafx.fxml;
    opens pl.edziennik.client.controller.admin.schools to javafx.fxml;
    opens pl.edziennik.client.task.school to javafx.fxml;
    opens pl.edziennik.client.task.register to javafx.fxml;
    opens pl.edziennik.client.controller.admin.configuration to javafx.fxml;
    opens pl.edziennik.client.controller.admin.accounts to javafx.fxml;
    opens pl.edziennik.client.core to javafx.fxml;
    exports pl.edziennik.client.controller.admin.accounts.student;
    opens pl.edziennik.client.controller.admin.accounts.student to javafx.fxml;
    exports pl.edziennik.client.controller.admin.accounts.teacher;
    opens pl.edziennik.client.controller.admin.accounts.teacher to javafx.fxml;
    opens pl.edziennik.client.controller.admin.accounts.parent to javafx.fxml;
    opens pl.edziennik.client.controller.admin.schoolclasses to javafx.fxml;
    exports pl.edziennik.client.rest.dto.student;
    exports pl.edziennik.client.rest.dto.teacher;
    exports pl.edziennik.client.rest.dto.admin;
    exports pl.edziennik.client.rest.dto.school;
    exports pl.edziennik.client.rest.dto.auth;
    exports pl.edziennik.client.rest.dto.schoolclass;
    exports pl.edziennik.client.rest.dto.schoollevel;
    exports pl.edziennik.client.rest.dto.config;
    exports pl.edziennik.client.rest.dto.parent;
    exports pl.edziennik.client.rest.dto;
    exports pl.edziennik.client.controller.admin.accounts.parent;
    exports pl.edziennik.client.controller.admin.accounts.admin;
    opens pl.edziennik.client.controller.admin.accounts.admin to javafx.fxml;
    exports pl.edziennik.client.common.factory;
    exports pl.edziennik.client.common.constants;
}