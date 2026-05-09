package com.chirayu.petmanagement.service;


import com.chirayu.petmanagement.dto.MailDTO;

/**
 * @author chirayu
 * @created 2026-05-03
 */
public interface MailService {
    String sendMail(MailDTO mailDTO);
}
