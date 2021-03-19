package com.github.lf2a.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


/**
 * <h1>MockEmailService.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 19/03/2021
 */
@Service
public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOG.info("Simulando envio de email");
        LOG.info(message.toString());
        LOG.info("Email enviado");
    }
}
