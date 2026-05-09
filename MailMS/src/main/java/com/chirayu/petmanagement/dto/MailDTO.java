package com.chirayu.petmanagement.dto;


import com.chirayu.petmanagement.enums.MailType;

/**
 * @author chirayu
 * @created 2026-05-03
 */

public record MailDTO(String to, String firstName, String lastName , MailType category) {
}
