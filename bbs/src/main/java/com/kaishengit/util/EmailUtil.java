package com.kaishengit.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bayllech on 2016/12/16.
 */
public class EmailUtil {
    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    public static void sendHtmlEmail(String toAddress, String subject, String context) {
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(Config.get("email.smtp"));
        htmlEmail.setSmtpPort(Integer.valueOf(Config.get("email.port")));
        htmlEmail.setAuthentication(Config.get("email.username"), Config.get("email.password"));
        htmlEmail.setStartTLSEnabled(true);

        try {
            htmlEmail.setFrom(Config.get("email.fromemail"));
            htmlEmail.setCharset("utf-8");
            htmlEmail.setSubject(subject);
            htmlEmail.setHtmlMsg(context);
            htmlEmail.addTo(toAddress);

            htmlEmail.send();
        } catch (EmailException e) {
            logger.error("向{}发送邮件失败",toAddress);
            throw new RuntimeException("向" + toAddress + "发送邮件失败");
        }

    }
}
