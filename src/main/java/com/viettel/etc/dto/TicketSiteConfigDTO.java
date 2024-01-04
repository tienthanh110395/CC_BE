package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TicketSiteConfigDTO implements Serializable {
    private static final long serialVersionUID = 7703094822124781210L;
    Long ticketSiteId;
    String ticketSiteCode;
    String ticketSiteName;
    Long parentId;
    List<Long> lstParentId;
    Long status;
    Long levelSite;
    String createUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;
    String updateUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;
    String formDate;
    String toDate;
    List<Long> lstIdsActive;
    List<Long> lstIdsInactive;
    List<Long> lstIds;
    List<UserHandle> lstUserHandle;
    String siteUser;
    String siteNameParent;
    String mail;
    String phoneNumber;
    Long ticketSiteLv;
    String username;
    String password;
    String ticketSiteEmail;
    String ticketSitePhoneNumber;

    @Data
    public static class UserHandle {
        Long ticketSiteUserId;
        Long siteId;
        String userName;
        String mail;
        String phone;
        String name;
    }
}
