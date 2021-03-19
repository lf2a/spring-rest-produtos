package com.github.lf2a.services;

import com.github.lf2a.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <h1>AbstractEmailService.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 19/03/2021
 */
@Service
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        var message = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(message);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        var message = new SimpleMailMessage();
        message.setTo(pedido.getCliente().getEmail());
        message.setFrom(sender);
        message.setSubject("Pedido confirmado! Codigo: " + pedido.getId());
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setText(pedido.toString());

        return message;
    }
}
