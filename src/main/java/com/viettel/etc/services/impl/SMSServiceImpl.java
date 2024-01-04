package com.viettel.etc.services.impl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import utils.Protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class SMSServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(SMSServiceImpl.class);
    private static final String FIRST_NUMBER_84 = "84";
    private static final int VALUE_2 = 2;

    @Value("${ws.sms.url}")
    private String url;

    @Value("${ws.sms.username}")
    private String username;

    @Value("${ws.sms.password}")
    private String password;

    @Value("${ws.sms.cp-code}")
    private String cpCode;

    @Value("${ws.sms.sender}")
    private String sender;

    /***
     * Gui tin nhan thong bao
     * @param phone
     * @param content
     * @param authentication
     * @return
     */
    public int sendSMS(String phone, String content, Authentication authentication) {
        phone = buildPhoneNumberBeforeSend(phone);
        if (phone == null) return -1;
        return sendMT(username, password, cpCode, sender, phone, content, "0", authentication);
    }

    private int sendMT(String username, String password, String cpCode, String sender, String receiver, String content, String contentType, Authentication authentication) {
        int result = -1;
        long startTime = System.currentTimeMillis();

        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:impl=\"http://impl.bulkSms.ws/\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<impl:wsCpMt>"
                + "<User>" + username + "</User>"
                + "<Password>" + password + "</Password>"
                + "<CPCode>" + cpCode + "</CPCode>"
                + "<RequestID>" + "1" + "</RequestID>"
                + "<UserID>" + receiver + "</UserID>"
                + "<ReceiverID>" + receiver + "</ReceiverID>"
                + "<ServiceID>" + sender + "</ServiceID>"
                + "<CommandCode>" + "bulksms" + "</CommandCode>"
                + "<Content>" + content + "</Content>"
                + "<ContentType>" + contentType + "</ContentType>"
                + "</impl:wsCpMt>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";

        PostMethod post = null;
        String response = null;
        try {
            Protocol protocol = new Protocol(url);
            HttpClient httpclient = new HttpClient();
            HttpConnectionManager conMgr = httpclient.getHttpConnectionManager();
            HttpConnectionManagerParams conPars = conMgr.getParams();
            conPars.setConnectionTimeout(20000);
            conPars.setSoTimeout(60000);
            post = new PostMethod(protocol.getUrl());
            RequestEntity entity = new StringRequestEntity(request, "text/xml", "UTF-8");
            post.setRequestEntity(entity);
            post.setRequestHeader("SOAPAction", "");
            httpclient.executeMethod(post);
            InputStream is = post.getResponseBodyAsStream();
            if (is != null) {
                response = getStringFromInputStream(is);
            }

            if (response != null && !"".equals(response)) {
                if (response.contains("<result>")) {
                    int start = response.indexOf("<result>") + "<result>".length();
                    int end = response.lastIndexOf("</result>");
                    String responseCode = response.substring(start, end);
                    if ("1".equalsIgnoreCase(responseCode)) {
                        result = 0; //call success
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("sendMT false ", e);
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
        return result;
    }

    private String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOG.error("getStringFromInputStream", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOG.error("getStringFromInputStream", e);
                }
            }
        }

        return sb.toString();
    }

    /**
     * Checks if is valid phone number.
     *
     * @param phone the phone
     * @return true, if is valid phone number
     */
    private String buildPhoneNumberBeforeSend(String phone) {
        try {
            String firstNumber = phone.substring(0, VALUE_2);
            String phoneLast = null;

            if (FIRST_NUMBER_84.equals(firstNumber)) {
                phoneLast = phone.substring(VALUE_2);
            } else {
                phoneLast = phone.substring(1);
            }

            return String.format("%s%s", FIRST_NUMBER_84, phoneLast);

        } catch (Exception e) {
            LOG.error("buildPhoneNumberBeforeSend error", e);
        }
        return null;
    }
}
