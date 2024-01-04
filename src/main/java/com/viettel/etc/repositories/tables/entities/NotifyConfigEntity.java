package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name NOTIFY_CONFIG
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "NOTIFY_CONFIG")
public class NotifyConfigEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "NOTIFY_CONFIG_SEQ")
    @SequenceGenerator(name = "NOTIFY_CONFIG_SEQ", sequenceName = "NOTIFY_CONFIG_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTIFY_CONFIG_ID")
    Long notifyConfigId;

    @Column(name = "NAME")
    String name;

    @Column(name = "CODE")
    String code;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "SMS_CONTENT")
    String contentSms;

    @Column(name = "MAIL_CONTENT")
    String contentMail;

    @Column(name = "IS_SMS")
    Long isSms;

    @Column(name = "IS_MAIL")
    Long isMail;

    @Column(name = "TYPE")
    Long type;

    @Column(name = "MAIL_SUBJECT")
    String mailSubject;

    public enum NotifyType {
        TAO_MOI_YC("1"),
        GAN_HET_HAN_YC("2"),
        QUA_HAN_YC("3"),
        XUAT_LAI_YC("4"),
        XU_LY_YC("5"),
        CONG_TIEN_DCC("6"),
        TRU_TIEN_DCC("7"),
        PHE_DUYET_DCC("8");
        public final String value;

        NotifyType(String value) {
            this.value = value;
        }
    }
}
