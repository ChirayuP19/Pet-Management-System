package com.chirayu.flowgrid.service;

import com.chirayu.flowgrid.dto.MailDTO;

/**
 * @author chirayu
 * @created 2026-05-03
 */

public interface MailService {

    String sendMail(MailDTO mailDTO);
}
