package com.spring.assistant.assistant.mailservice;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public interface MailService {

    void sendStandartMail(MailInfoModel mailInfoModel) throws MessagingException, IOException, MailException;

}
