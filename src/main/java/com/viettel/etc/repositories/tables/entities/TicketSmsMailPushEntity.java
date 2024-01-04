package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_sms_mail_push
 *
 * @author ToolGen
 * @date Tue Jun 01 11:14:42 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_SMS_MAIL_PUSH")
public class TicketSmsMailPushEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_SMS_MAIL_PUSH_SEQ")
    @SequenceGenerator(name = "TICKET_SMS_MAIL_PUSH_SEQ", sequenceName = "TICKET_SMS_MAIL_PUSH_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_SMS_MAIL_PUSH_ID")
    Long ticketSmsMailPushId;

    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "SMS_MAIL_PUSH_TYPE")
    String smsMailPushType;

    @Column(name = "MESSAGE")
    String message;

    @Column(name = "PHONE")
    String phone;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "MOBILE_ID")
    String mobileId;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "ERORR_MESSAGE")
    String errorMessage;

    public enum SMSMailPushType {
        SEND_SMS("SMS"),
        SEND_EMAIL("EMAIL"),
        PUSH_NOTIFY("PUSH");
        public final String value;

        SMSMailPushType(String value) {
            this.value = value;
        }
    }
}
