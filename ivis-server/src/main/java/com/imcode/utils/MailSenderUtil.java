package com.imcode.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruslan on 22.07.16.
 */
public class MailSenderUtil {

    private JavaMailSender mailSender;
    private MimeMessage mimeMessage;
    private MimeMessageHelper helper;

    private boolean html;
    private boolean multipart;

    private String mailTo;
    private String mailSubject;
    private String mailText;
    private Map<String, File> attachements;

    public MailSenderUtil(JavaMailSender mailSender, boolean multipart, boolean html, String fromAddress, String fromUsername) {
        this.mailSender = mailSender;
        mimeMessage = mailSender.createMimeMessage();
        try {
            helper = new MimeMessageHelper(mimeMessage, multipart, "utf-8");
            helper.setFrom(new InternetAddress(fromAddress, fromUsername));
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.html = html;
        this.multipart = multipart;
    }

    public void createMessage(String mailTo, String mailSubject, String mailText) {
        try {
            helper.setTo(mailTo);
            helper.setSubject(mailSubject);
            helper.setText(mailText, html);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void createMessage() {

        try {
            helper.setTo(mailTo);
            helper.setSubject(mailSubject);
            helper.setText(mailText, html);

            if (multipart) {
                attachements.forEach((name, file) -> {
                    try {
                        helper.addAttachment(name, file);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage() {
        mailSender.send(mimeMessage);
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public void attachFile (File file) {
        if (attachements == null) {
            attachements = new HashMap<>();
        }
        attachements.put(file.getName(), file);
    }
}
