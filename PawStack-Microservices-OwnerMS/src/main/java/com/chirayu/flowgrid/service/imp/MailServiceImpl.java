package com.chirayu.flowgrid.service.imp;

import com.chirayu.flowgrid.dto.MailDTO;
import com.chirayu.flowgrid.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * @author chirayu
 * @created 2026-05-03
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final RestClient restClient;
    @Value("${mail.service.url}")
    private String mailServiceUrl;

    @Override
    public String sendMail(MailDTO mailDTO) {
        ResponseEntity<String> response = restClient.post()
                .uri(mailServiceUrl)
                .body(mailDTO)
                .retrieve()
                .toEntity(String.class);

        log.info("Mail response status: {}", response.getStatusCode());
        log.info("Mail response body: {}", response.getBody());
        return response.getBody();
    }

}
