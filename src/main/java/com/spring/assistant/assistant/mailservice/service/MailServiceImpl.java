package com.spring.assistant.assistant.mailservice.service;

import com.spring.assistant.assistant.mailservice.model.MailInfoModel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@NoArgsConstructor
public class MailServiceImpl implements MailService{


    @Autowired
    private JavaMailSender mail;


    @Override
    public void sendStandartMail(MailInfoModel mailInfoModel) throws MessagingException {
        MimeMessage mimeMessage = mail.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(mimeMessage,true);
        helper.setTo(mailInfoModel.getTo());
        helper.setSubject(mailInfoModel.getSubject());
        helper.setText(mailInfoModel.getBody1() + " " + mailInfoModel.getBody2(), true);
        mail.send(mimeMessage);
    }
}
