package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Statistic_type
 * 
 * @author ToolGen
 * @date Wed Dec 01 13:45:17 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "STATISTIC_TYPE")
public class StatisticTypeEntity implements Serializable, Cloneable{

    @Id
    @GeneratedValue(generator = "STATISTIC_TYPE_SEQ")
    @SequenceGenerator(name = "STATISTIC_TYPE_SEQ", sequenceName = "STATISTIC_TYPE_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATISTIC_TYPE_ID")
    Long statisticTypeId;

    @Column(name = "NAME")
    String name;

    @Column(name = "CODE")
    String code;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "TEMPLATE")
    String template;

    @Column(name = "PARENT_ID")
    Long parentId;

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

    @Column(name = "MYCC_ID")
    String myccId;

    @Column(name = "LEVEL_STATISTIC")
    Long levelStatistic;

//    @Column(name = "LEVEL_TT")
//    Long levelTt ;

    public enum STATUS {
        VALID(1L),
        INVALID(0L);
        public final Long value;

        STATUS(Long value) {
            this.value = value;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public enum StatisticTypeLevel {
        Level_1(1L),
        Level_2(2L),
        Level_3(3L),
        Level_4(4L),
        Level_5(5L);
        public final Long value;

        StatisticTypeLevel(Long value) {
            this.value = value;
        }
    }
}