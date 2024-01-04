package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Cc_all_column_header
 *
 * @author ToolGen
 * @date Tue Jun 08 12:19:39 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "CC_ALL_COLUMN_HEADER")
public class CcAllColumnHeaderEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "CC_ALL_COLUMN_HEADER_SEQ")
    @SequenceGenerator(name = "CC_ALL_COLUMN_HEADER_SEQ", sequenceName = "CC_ALL_COLUMN_HEADER_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "CC_ALL_COLUMN_HEADER_ID")
    Long ccAllColumnHeaderId;

    @Column(name = "TABLE_NAME")
    String tableName;

    @Column(name = "COLUMN_NAME")
    String columnName;

    @Column(name = "DATA_TYPE")
    String dataType;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "COLUMN_HEADER")
    String columnHeader;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

}
