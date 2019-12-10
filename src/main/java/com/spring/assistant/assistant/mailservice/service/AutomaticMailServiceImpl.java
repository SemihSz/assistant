package com.spring.assistant.assistant.mailservice.service;


import com.spring.assistant.assistant.mailservice.model.MailInfoModel;
import com.spring.assistant.assistant.todo.repository.SubTodoRepository;
import com.spring.assistant.assistant.todo.repository.TodoRepository;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@Service
public class AutomaticMailServiceImpl implements Serializable {


    private TodoRepository todoRepository;

    public AutomaticMailServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Autowired
    private JavaMailSender mail;

    SubTodoRepository subTodoRepository;

    private static final Logger logger = LoggerFactory.getLogger(AutomaticMailServiceImpl.class);


    public void getAllAutomatic(MailInfoModel mailInfoModel, LocalDate exceptFinishDate) throws MessagingException {
        Integer difference = 0;
        difference = controlFinishDate(exceptFinishDate);
        if (difference == 1) {
            sendStandartMail(mailInfoModel);
        }


    }

    private Integer controlFinishDate(LocalDate expectDate) {

        final LocalDate localDate = LocalDate.now();
        Period period = Period.between(localDate, expectDate);
        return period.getDays();
    }


    private void sendStandartMail(MailInfoModel mailInfoModel) throws MessagingException {
        MimeMessage mimeMessage = mail.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(mailInfoModel.getTo());
        helper.setSubject(mailInfoModel.getSubject());
        helper.setText(mailInfoModel.getBody1() + " " + mailInfoModel.getBody2(), true);
        mail.send(mimeMessage);
    }


}
