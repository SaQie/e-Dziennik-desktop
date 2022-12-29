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

    requires org.kordamp.ikonli.javafx;

    opens pl.edziennik.client to javafx.fxml;
    exports pl.edziennik.client;
    exports pl.edziennik.client.controller.auth;
    exports pl.edziennik.client.rest;
    exports pl.edziennik.client.controller;
    opens pl.edziennik.client.controller.auth to javafx.fxml;
    opens pl.edziennik.client.controller to javafx.fxml;
    exports pl.edziennik.client.rest.client;
    exports pl.edziennik.client.rest.pojo;
    exports pl.edziennik.client.task;
    opens pl.edziennik.client.task to javafx.fxml;

}