package com.github.lf2a.services;

import com.github.lf2a.domain.Cliente;
import com.github.lf2a.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * <h1>EmailService.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 19/03/2021
 */
@Service
public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendOrderConfirmationHtmlEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage message);

    void sendHtmlEmail(MimeMessage message);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
