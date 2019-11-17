package com.spring.assistant.assistant.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class MailServiceImpl implements MailService{


    @Autowired
    private JavaMailSender mail;


    @Override
    public void sendStandartMail(String email, String subject, String textBody) throws  MessagingException{
        MimeMessage mimeMessage = mail.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(mimeMessage,true);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(textBody,true);
        /*SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(textBody);*/
        mail.send(mimeMessage);
    }
}
