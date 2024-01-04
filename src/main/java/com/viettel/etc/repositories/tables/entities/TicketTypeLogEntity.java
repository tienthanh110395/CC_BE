/*
package com.viettel.etc.repositories.tables.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_TYPE_LOG")
public class TicketTypeLogEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "TICKET_TYPE_LOG_SEQ")
    @SequenceGenerator(name = "TICKET_TYPE_LOG_SEQ", sequenceName = "TICKET_TYPE_LOG_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_TYPE_LOG_ID")
    Long ticketTypeLogId;

    @Column(name = "CREATE_USER")
    String createUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "LEVEL_TT")
    Long levelTt;

    @Column(name = "TABLE_NAME")
    String tableName;

    @Column(name = "ACT_TYPE")
    Long actType;


    public enum ActionType {
        INSERT(1L),
        UPDATE(2L),
        DELETE(3L);
        public final Long value;

        ActionType(Long value) {
            this.value = value;
        }
    }

    public enum LevelTt {
        GROUP(1L),
        GENRE(2L),
        TYPE(3L),
        LEVEL_CATE(5L),
        ERROR(6L),
        MAP_ERROR(7L),
        EXPIRE(8L),
        STATISTIC(10L);
        public final Long value;

        LevelTt(Long value) {
            this.value = value;
        }
    }

    public enum TableName {
        TICKET_GROUP("Nhóm phản ánh"),
        TICKET_GENRE("Thể loại phản ánh"),
        TICKET_TYPE("Loại phản ánh"),
        TICKET_LEVEL_CATE("Mức độ ưu tiên"),
        TICKET_ERROR_CAUSE("Nguyên nhân lỗi"),
        MAPPING_ERROR_CAUSE("Map nguyên nhân lỗi"),
        TICKET_EXPIRE_CAUSE("Nguyên nhân quá hạn"),
        TICKET_CATE_STATISTIC("Danh mục thống kê");

        public final String value;

        TableName(String value) {
            this.value = value;
        }
    }
}
*/
