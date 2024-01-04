package com.viettel.etc.utils;

import com.squareup.okhttp.MediaType;

import java.util.*;

public class Constants {
    public static final String REQUEST_MAPPING_V1 = "/api/v1";
    public static final String MOBILE = "/mobile";
    public static final String COMMON_DATE_FORMAT = "dd/MM/yyyy";
    public static final String COMMON_DATE_FORMAT_24H = "dd/MM/yyyy hh24:mi:ss";
    public static final String COMMON_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType FORM_URL_ENCODED = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    public static final String MESSAGE_CODE = "MESSAGE_CODE";
    public static final String MESSAGE_ERROR_VI = "MESSAGE_ERROR_VI";
    public static final String MESSAGE_ERROR_EN = "MESSAGE_ERROR_EN";
    public static final String LOCALE_VN = "vi_VN";
    public static final String TIMEZONE_VN = "Asia/Ho_Chi_Minh";

    public static final long ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;

    public interface MINIO_FOLDER_KEY {
        String CONTRACT_PROFILE = "CONTRACT_PROFILE";
        String VEHICLE_PROFILE = "VEHICLE_PROFILE";
        String OTHER = "OTHER";
    }

    public interface ROLE_CC {
        String ROLE_ADMIN_CC = "Role_Admin_CC";
        String ROLE_HOSO_CC = "Role_HoSo_CC";
    }

    protected static final Map<String, String> PROPERTY_MAPPING = initMap();

    private static Map<String, String> initMap() {
        Map<String, String> map = new HashMap<>();
        map.put("accountUser", "Tên đăng nhập");
        map.put("contractId", "Id hợp đồng");
        map.put("actType", "Loại tác động");
        map.put("plateNumber", "Biển số xe");
        map.put("type", "Loại");
        map.put("offerExternalId", "offerExternalId");
        map.put("fileBase64", "Nội dung file");
        map.put("signDate", "Ngày ký");
        map.put("effDate", "Ngày hiệu lực");
        map.put("expDate", "Ngày hết hiệu lực");
        map.put("emailNotification", "Thông báo bằng email");
        map.put("smsNotification", "Thông báo bằng SMS");
        map.put("pushNotification", "Thông báo trên App");
        map.put("billCycle", "Chu kỳ xuất hóa đơn");
        map.put("payCharge", "Quy định gói cước lượt trả tiền xe qua trạm");
        map.put("noticeName", "Họ và tên người thông báo cước");
        map.put("noticeStreet", "Số nhà, đường/ phố thông báo cước");
        map.put("noticeAreaCode", "Mã khu vực thông báo cước");
        map.put("noticePhoneNumber", "Số điện thoại thông báo cước");
        map.put("signName", "Người ký");
        map.put("actTypeId", "Loại tác động");
        map.put("documentTypeId", "Loại giấy tờ");
        map.put("fileName", "Tên file");
        map.put("promotionId", "Khuyến mãi");
        map.put("assignLevel", "Mức độ gán");
        map.put("fromDate", "Từ ngày");
        map.put("toDate", "Đến ngày");
        map.put("shopId", "Cửa hàng");
        map.put("servicePlanCode", "Mã bảng cước, giá vé");
        map.put("servicePlanTypeId", "Bảng cước, giá vé");
        map.put("ocsCode", "Mã OCS");
        map.put("vehicleGroupId", "Loại phương tiện tính phí");
        map.put("autoRenew", "Gia hạn tự động");
        map.put("fee", "Giá phí");
        map.put("scope", "Phạm vi áp dụng");
        map.put("startDate", "Ngày bắt đầu");
        map.put("fullName", "Họ và tên");
        map.put("phoneNumber", "Số điện thoại");
        map.put("accountUserId", "Id tài khoản");
        map.put("amount", "Tổng tiền");
        map.put("address", "Địa chỉ");
        map.put("customerName", "Tên khách hàng");
        map.put("stockModelCode", "Mã mặt hàng trên BCCS");
        map.put("price", "Giá tiền");
        map.put("vat", "Phí VAT");
        map.put("quantity", "Số lượng");
        map.put("transType", "Loại giao dịch");
        map.put("saleTransType", "Loại giao dịch");
        map.put("balanceBefore", "Số tiền sau giao dịch");
        map.put("custId", "Khách hàng");
        map.put("url", "Đường dẫn");
        map.put("versionName", "Tên phiên bản");
        map.put("partnerCode", "partnerCode");
        map.put("partnerRefId", "partnerRefId");
        map.put("requestType", "requestType");
        map.put("momoTransId", "momoTransId");
        map.put("signature", "Chữ ký");
        map.put("EPC", "Mã etag");
        map.put("movementType", "Loại trạm");
        map.put("accessKey", "accessKey");
        map.put("orderId", "orderId");
        map.put("orderInfo", "orderInfo");
        map.put("returnUrl", "returnUrl");
        map.put("requestId", "requestId");
        map.put("errorCode", "errorCode");
        map.put("message", "message");
        map.put("localMessage", "localMessage");
        map.put("payUrl", "payUrl");
        map.put("orderType", "orderType");
        map.put("transId", "transId");
        map.put("payType", "payType");
        map.put("responseTime", "responseTime");
        map.put("extraData", "extraData");
        return Collections.unmodifiableMap(map);
    }

    public interface APP_CLIENT_ID {
        String APP_CHU_PT = "mobile-app-chupt";
        String PORTAL_CHU_PT = "portal-chu-pt";
        String APP_CTV_DAI_LY = "mobile-app-ctv-daily";
        String BOO1 = "boo1";
        String CMS = "cms";
        String VT_PAY = "viettelpay";
        String MOMO = "momo";
        String CRM = "crm";
        String BOT = "portal-bot";
        String MOT = "portal-mot";
        String OCS = "ocs";
    }

    public interface OTP_CATEGORY {
        String TABLE_NAME = "OTP_CONFIG";
        String CODE_DAU_NOI = "DAU_NOI";
        String CODE_MERGES_CONTRACT = "MERGES_CONTRACT";
        String CODE_SPLIT_CONTRACT = "SPLIT_CONTRACT";
        String CODE_CHARGE_TICKET = "CHARGE_TICKET";
        String CODE_DESTROY_CONTRACT = "DESTROY_CONTRACT";
        String CODE_DESTROY_RFID = "DESTROY_RFID";
        String CODE_CONTRACT_ADJUST_BALANCE = "CONTRACT_ADJUST_BALANCE";
        String CODE_TRANSFER_OWNER = "TRANSFER_OWNER";
        String CODE_APPEND_CONTRACT = "APPEND_CONTRACT";
        String ON = "1";
        String OFF = "0";
        String CODE_CONTRACT_BATCH_ADJUST_BALANCE = "CONTRACT_BATCH_ADJUST_BALANCE";
        String CODE_TICKET_ADJUSTMENT = "TICKET_ADJUSTMENT";
        String CODE_TICKET_ADJUSTMENT_VEHICLE_GROUP = "TICKET_ADJUSTMENT_VEHICLE_GROUP";
        String CODE_FEEDBACK = "FEEDBACK";
        String CODE_REPORT_ERROR = "REPORT_ERROR";
    }
}
