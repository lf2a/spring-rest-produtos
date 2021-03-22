package com.github.lf2a.services;

import com.github.lf2a.domain.Cliente;
import com.github.lf2a.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    @Autowired
    private TemplateEngine engine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        var message = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(message);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
        try {
            var message = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(message);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        var message = javaMailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setTo(pedido.getCliente().getEmail());
        messageHelper.setFrom(sender);
        messageHelper.setSubject("Pedido confirmado! Codigo: " + pedido.getId());
        messageHelper.setSentDate(new Date(System.currentTimeMillis()));
        messageHelper.setText(htmlFromTemplatePedido(pedido), true);

        return message;
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

    protected String htmlFromTemplatePedido(Pedido pedido) {
        var context = new Context();
        context.setVariable("pedido", pedido);

        return engine.process("email/confirmacaoPedido", context);
    }

    @Override
    public void sendNewPasswordEmail(Cliente cliente, String newPass) {
        var message = prepareNewPasswordEmail(cliente, newPass);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
        var smm = new SimpleMailMessage();
        smm.setTo(cliente.getEmail());
        smm.setFrom(sender);
        smm.setSubject("Solicitação de nova senha");
        smm.setSentDate(new Date(System.currentTimeMillis()));
        smm.setText("Nova senha: " + newPass);
        return smm;
    }
}
