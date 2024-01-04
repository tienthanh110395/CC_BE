package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author ThaiBQ
 * @date 08/06/2023
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "MAPPING_ERROR_CAUSE")
public class MapErrorEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "MAPPING_ERROR_CAUSE_SEQ")
    @SequenceGenerator(name = "MAPPING_ERROR_CAUSE_SEQ", sequenceName = "MAPPING_ERROR_CAUSE_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAP_ID")
    Long mapId;

    @Column(name = "TICKET_TYPE_ID")
    Long ticketTypeId;

    @Column(name = "TICKET_ERROR_CAUSE_ID")
    Long ticketErrorId;

    @Column(name = "TICKET_ERROR_CAUSE_LV2_ID")
    Long ticketErrorLv2Id;

    @Column(name = "TICKET_ERROR_CAUSE_LV3_ID")
    Long ticketErrorLv3Id;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

}
