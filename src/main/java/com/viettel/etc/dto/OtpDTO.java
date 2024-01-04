package com.viettel.etc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * Thong tin OTP
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpDTO {

    Long contractId;

    String phone;

    String user;

    Integer confirmType;

    Long userType;

    String plateTypeCode;

    public OtpDTO(long l, String phone, String user, int i) {
        this(l, phone, user, i, null, null);
    }

    public String getPhone() {
        if (StringUtils.startsWith(phone, "84")) {
            return "0" + phone.substring(2);
        } else {
            return phone;
        }
    }
}
