package com.viettel.etc.repositories.tables.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.*;

/**
 * Autogen class Entity: Create Entity For Table Name Statistic
 * 
 * @author ToolGen
 * @date Thu Dec 02 09:01:17 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "STATISTIC")
public class StatisticEntity implements Serializable{

    @Id
    @GeneratedValue(generator = "STATISTIC_SEQ")
    @SequenceGenerator(name = "STATISTIC_SEQ", sequenceName = "STATISTIC_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATISTIC_ID")
    Long statisticId;

    @Column(name = "CONTRACT_NO_USER_NAME")
    String contractNoUserName;

    @Column(name = "PLATE_NUMBER")
    String plateNumber;

    @Column(name = "SYSTEM_PHONE_NUMBER")
    String systemPhoneNumber;

    @Column(name = "CALL_PHONE_NUMBER")
    String callPhoneNumber;

    @Column(name = "SOURCE_ID")
    Long sourceId;

    @Column(name = "L1_STATISTIC_TYPE_ID")
    Long l1StatisticTypeId;

    @Column(name = "L2_STATISTIC_TYPE_ID")
    Long l2StatisticTypeId;

    @Column(name = "L3_STATISTIC_TYPE_ID")
    Long l3StatisticTypeId;

    @Column(name = "L4_STATISTIC_TYPE_ID")
    Long l4StatisticTypeId;

    @Column(name = "L5_STATISTIC_TYPE_ID")
    Long l5StatisticTypeId;

    @Column(name = "STATISTIC_CONTENT")
    String statisticContent;

    @Column(name = "CUST_REACTION")
    String custReaction;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;
}