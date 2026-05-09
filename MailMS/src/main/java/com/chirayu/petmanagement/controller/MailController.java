package com.chirayu.petmanagement.controller;

import com.chirayu.petmanagement.dto.MailDTO;
import com.chirayu.petmanagement.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chirayu
 * @created 2026-05-04
 */

@RestController
@RequestMapping("/api/v1/mails")
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;

    @PostMapping
    public ResponseEntity<String> sendMail(@RequestBody MailDTO mailDTO){
        log.info("Mail send to {}",mailDTO.to());
        return ResponseEntity.ok(mailService.sendMail(mailDTO));
    }
}
