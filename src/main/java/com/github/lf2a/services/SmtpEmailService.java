package com.github.lf2a.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

/**
 * <h1>SmtpEmailService.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 19/03/2021
 */
public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOG.info("Simulando envio de email");
        mailSender.send(message);
        LOG.info("Email enviado");
    }

    @Override
    public void sendHtmlEmail(MimeMessage message) {
        LOG.info("Simulando envio de email HTML...");
        javaMailSender.send(message);
        LOG.info("Email enviado");
    }
}
