package com.chirayu.flowgrid.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailType {
    WELCOME("Welcome to Petistaan", "welcome.ftlh"),
    MODIFY("Your data in Petistaan has been modified", "modify.ftlh"),
    EXIT("Thanks for visiting Petistaan", "exit.ftlh");

    private String subject;
    private String templateFileName;

}
