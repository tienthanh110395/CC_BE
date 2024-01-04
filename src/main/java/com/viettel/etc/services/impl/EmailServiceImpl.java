package com.viettel.etc.services.impl;

import com.viettel.etc.services.FileService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailServiceImpl {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(EmailServiceImpl.class.getName());

    @Value("${crm.email.server}")
    private String server;

    @Value("${crm.email.port}")
    private String port;

    @Value("${crm.email.local}")
    private String local;

    @Value("${crm.email.user}")
    private String username;

    @Value("${crm.email.pass}")
    private String password;

    @Value("${crm.email.from}")
    private String from;
    @Autowired
    private FileService fileService;

    /***
     * Gui mail thong bao
     * @param subject
     * @param mailReceive
     * @param filePath
     * @param parameter
     * @param authentication
     * @param fileAttach
     */
    public boolean sendMail(String subject, String mailReceive, String filePath, Map<String, String> parameter, Authentication authentication, Map<String, String> fileAttach) {
        String content = getContent(filePath, parameter);
        return sendMailSSL(server, port, local, username, password, from, mailReceive, subject, content, 0, fileAttach);
    }

    private String getContent(String filePath, Map<String, String> parameter) {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(filePath)) {
            StringBuilder content = new StringBuilder();
            if (in != null) {
                try (Reader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(StandardCharsets.UTF_8.name())))) {
                    int c = 0;
                    while ((c = reader.read()) != -1) {
                        content.append((char) c);
                    }

                    String result = content.toString();
                    for (Map.Entry<String, String> entry : parameter.entrySet()) {
                        result = result.replace(entry.getKey(), entry.getValue());
                    }
                    return result;
                } catch (IOException e) {
                    LOGGER.error(e);
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return "";
    }

    /**
     * Thong tin cau hinh mail
     *
     * @param ipHost
     * @param port
     * @param ipLocal
     * @return
     * @throws Exception
     */
    private Properties getHostMail(String ipHost, String port, String ipLocal) {
        Properties props = new Properties();
        props.put("mail.smtp.host", ipHost);
        props.put("mail.smtp.localhost", ipLocal);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);
        return props;

    }

    /**
     * Kiem tra user Pass lay session de gui mail
     *
     * @param props
     * @param userMail
     * @param passMail
     * @return
     * @throws Exception
     */
    private Session getSessionMail(Properties props, final String userMail, final String passMail) throws Exception {
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userMail, passMail);
                    }
                });
        return session;
    }

    /**
     * Set noi dung mail gui di
     *
     * @param session
     * @param mailFrom
     * @param mailReceive
     * @param subject
     * @param content
     * @return
     * @throws Exception
     */
    private Message getMessageForMail(Session session, String mailFrom, String mailReceive, String subject, String content, Map<String, String> fileAttach) throws Exception {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailReceive));
            message.setSubject(subject);
//            message.setContent(content, "text/html;charset=utf-8");
            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(content);
            messageBodyPart.setContent(content, "text/html;charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            if (fileAttach != null && fileAttach.size() > 0) {
                fileAttach.forEach((fileName, fileBase64) -> {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    byte[] file = Base64.decodeBase64(fileBase64);
                    ByteArrayDataSource bds = new ByteArrayDataSource(file, "application/octet-stream");
                    try {
                        attachmentPart.setDataHandler(new DataHandler(bds));
                        attachmentPart.setFileName(fileName);
                        multipart.addBodyPart(attachmentPart);
                    } catch (MessagingException e) {
                        LOGGER.error(e);
                    }
                });
            }
            message.setContent(multipart);
            message.saveChanges();
            return message;
        } catch (Exception e) {
            LOGGER.error("getMessageForMail :", e);
        }
        return null;
    }

    /**
     * Ham xu ly gui mail
     *
     * @param ipServerMail
     * @param port
     * @param ipLocal
     * @param user
     * @param pass
     * @param mailFrom
     * @param mailReceive
     * @param subject
     * @param content
     * @param callId
     * @return
     */
    private boolean sendMailSSL(String ipServerMail, String port, String ipLocal, String user, String pass, String mailFrom,
                                String mailReceive, String subject, String content, long callId, Map<String, String> fileAttach) {
        boolean result = false;
        try {
            Properties props = getHostMail(ipServerMail, port, ipLocal);
            Session session = getSessionMail(props, user, pass);
            Message message = getMessageForMail(session, mailFrom, mailReceive, subject, content, fileAttach);
            if (message != null) Transport.send(message);
            result = true;
        } catch (Exception e) {
            LOGGER.error("sendMailSSL :", e);
        }
        return result;
    }
}
