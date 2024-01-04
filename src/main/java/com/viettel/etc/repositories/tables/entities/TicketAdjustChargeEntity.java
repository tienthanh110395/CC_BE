package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_adjust_charge
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_ADJUST_CHARGE")
public class TicketAdjustChargeEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_ADJUST_CHARGE_SEQ")
    @SequenceGenerator(name = "TICKET_ADJUST_CHARGE_SEQ", sequenceName = "TICKET_ADJUST_CHARGE_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_ADJUST_CHARGE_ID")
    Long ticketAdjustChargeId;

    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "PLATE_TYPE_CODE")
    String plateTypeCode;

    @Column(name = "PLATE_NUMBER")
    String plateNumber;

    @Column(name = "PAY_TYPE")
    String payType;

    @Column(name = "CONTRACT_NO")
    String contractNo;

    @Column(name = "ACCOUNT_TYPE")
    String accountType;

    @Column(name = "ADJUST_AMOUNT")
    Long adjustAmount;

    @Column(name = "REASON")
    String reason;

    @Column(name = "ADJUST_CONTENT")
    String adjustContent;

    @Column(name = "REQUEST_DATE")
    Date requestDate;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "SITE_ID")
    Long siteId;

    public enum Status {
        VALID(1L),
        INVALID(0L);
        public final Long value;

        Status(Long value) {
            this.value = value;
        }
    }

}
