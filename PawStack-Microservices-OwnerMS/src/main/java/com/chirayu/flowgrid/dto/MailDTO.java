package com.chirayu.flowgrid.dto;

import com.chirayu.flowgrid.enums.MailType;

/**
 * @author chirayu
 * @created 2026-05-03
 */

public record MailDTO(String to, String firstName, String LastName , MailType category) {
}
