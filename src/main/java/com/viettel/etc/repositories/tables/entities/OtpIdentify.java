package com.viettel.etc.repositories.tables.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpIdentify implements Serializable {

    public static final int REGISTER = 0;
    public static final int RESET_PASSWORD = 1;
    public static final int QUICK_SALE = 2;
    public static final int SIGN = 3;
    public static final int TOPUP_CTV = 4;
    public static final int MERGES_CONTRACT = 5;
    public static final int SPLIT_CONTRACT = 6;
    public static final int CHARGE_TICKET = 7;
    public static final int DESTROY_CONTRACT = 8;
    public static final int DESTROY_RFID = 9;
    public static final int CONTRACT_ADJUST_BALANCE = 10;
    public static final int TRANSFER_OWNER = 11;
    public static final int TICKET_ADJUSTMENT = 12;
    public static final int APPEND_CONTRACT = 13;
    public static final int TICKET_ADJUSTMENT_VEHICLE_GROUP = 14;
    public static final int SMS_REGISTER = 15;
    public static final int SMS_MODIFICATION = 16;
    public static final int FEEDBACK = 17;
    public static final int REPORT_ERROR = 18;

    String phone;

    Integer confirmType;
}
