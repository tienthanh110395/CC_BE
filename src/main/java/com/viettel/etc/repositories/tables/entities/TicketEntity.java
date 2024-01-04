package com.viettel.etc.repositories.tables.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TICKET")
public class TicketEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_SEQ")
    @SequenceGenerator(name = "TICKET_SEQ", sequenceName = "TICKET_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "CUST_ID")
    Long custId;

    @Column(name = "CONTRACT_NO")
    String contractNo;

    @Column(name = "CUST_TYPE_ID")
    Long custTypeId;

    @Column(name = "PLATE_NUMBER")
    String plateNumber;

    @Column(name = "PHONE_NUMBER")
    String phoneNumber;

    @Column(name = "CUST_NAME")
    String custName;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "CUST_ADDRESS")
    String custAddress;

    @Column(name = "PRIORITY_ID")
    Long priorityId;

    @Column(name = "SOURCE_ID")
    Long sourceId;

    @Column(name = "L1_TICKET_TYPE_ID")
    Long l1TicketTypeId;

    @Column(name = "L2_TICKET_TYPE_ID")
    Long l2TicketTypeId;

    @Column(name = "L3_TICKET_TYPE_ID")
    Long l3TicketTypeId;

    @Column(name = "LOCATION")
    String location;

    @Column(name = "AREA_CODE")
    String areaCode;

    @Column(name = "CONTENT_RECEIVE")
    String contentReceive;

    @Column(name = "TICKET_KIND")
    Long ticketKind;

    @Column(name = "SUPPORT_INFO")
    String supportInfo;

    @Column(name = "REQUEST_DATE")
    Date requestDate;

    @Column(name = "SLA_DATE")
    Date slaDate;

    @Column(name = "NOTE")
    String note;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "RESPONSE_STATUS")
    Long responseStatus;

    @Column(name = "REOPEN_DATE")
    Date reopenDate;

    @Column(name = "ASSIGN_TYPE")
    Long assignType;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "PLATE_TYPE_CODE")
    String plateTypeCode;

    @Column(name = "PROVINCE_NAME")
    String provinceName;

    @Column(name = "DISTRICT_NAME")
    String districtName;

    @Column(name = "COMMUNE_NAME")
    String communeName;

    @Column(name = "PHONE_CONTACT")
    String phoneContact;

    @Column(name = "SLA")
    Long sla;

    @Column(name = "STAGE_ID")
    Long stageId;

    @Column(name = "STATION_ID")
    Long stationId;

    @Column(name = "STAGE_NAME")
    String stageName;

    @Column(name = "STATION_NAME")
    String stationName;

    @Column(name = "TICKET_TIMES", columnDefinition = "NUMBER DEFAULT 1")
    Long ticketTimes;

    @Column(name = "PROCESS_USER")
    String processUser;

    @Column(name = "FEED_BACK")
    String feedBack;

    @Column(name = "TICKET_CHANNEL", columnDefinition = "NUMBER DEFAULT 1")
    Long ticketChannel;

    @Column(name = "CONTRACT_ID")
    Long contractId;

    public enum Priority {
        NORMAL(1L),
        HOT(2L),
        VIP(3L);
        public final Long value;

        Priority(Long value) {
            this.value = value;
        }
    }
}
