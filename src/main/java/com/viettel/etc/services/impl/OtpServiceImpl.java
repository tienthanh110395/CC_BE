package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ContractDetailDTO;
import com.viettel.etc.dto.OtpDTO;
import com.viettel.etc.repositories.tables.entities.OtpEntity;
import com.viettel.etc.repositories.tables.entities.OtpIdentify;
import com.viettel.etc.services.CRMService;
import com.viettel.etc.services.CategoriesService;
import com.viettel.etc.services.OtpService;
import com.viettel.etc.services.tables.OtpServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    OtpServiceJPA otpServiceJPA;

    @Autowired
    SMSServiceImpl smsService;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    CRMService crmService;

    @Value("${sms.user.request-otp}")
    String sms;

    @Value("${sms.otp.duration}")
    String duration;

    /**
     * @param params
     * @param authentication
     * @return
     */
    @Override
    public Object requestOTPContract(OtpDTO params, Authentication authentication) {

        if (FnCommon.isNullObject(params.getConfirmType())) {
            return null;
        }

        ContractDetailDTO contractDetailDTO = crmService.getContractDetails(authentication);
        if (contractDetailDTO == null || contractDetailDTO.getNoticePhoneNumber() == null) {
            return null;
        }

        if (isSendOtp(contractDetailDTO.getNoticePhoneNumber(), params.getConfirmType())) {
            throw new EtcException("crm.sended.otp");
        }

        OtpIdentify id = new OtpIdentify(contractDetailDTO.getNoticePhoneNumber(), params.getConfirmType());
        if (otpServiceJPA.existsById(id)) {
            OtpEntity otpEntity = otpServiceJPA.getById(id);
            otpServiceJPA.delete(otpEntity);
        }
        String pass = RandomStringUtils.randomNumeric(6);
        OtpEntity data = new OtpEntity();
        data.setPhone(id.getPhone());
        data.setConfirmType(id.getConfirmType());
        data.setOtp(pass);
        data.setDuration(Integer.valueOf(duration));
        String contentSMS = String.format(sms, pass);
        smsService.sendSMS(contractDetailDTO.getNoticePhoneNumber(), contentSMS, authentication);
        return otpServiceJPA.save(data);
    }


    /**
     * @param otp
     * @param categoryCode
     * @param confirmType
     * @param authentication
     * @return
     */
    @Override
    public boolean checkOtpImportantService(String otp, String categoryCode, Integer confirmType, Authentication authentication) {
        if (!FnCommon.isNullOrEmpty(otp) && categoriesService.getConfigOtp(categoryCode, authentication)) {
            OtpIdentify otpIdentify = new OtpIdentify();
            otpIdentify.setConfirmType(confirmType);
            ContractDetailDTO contractDetailDTO = crmService.getContractDetails(authentication);
            if (contractDetailDTO == null || contractDetailDTO.getNoticePhoneNumber() == null) {
                return false;
            }
            otpIdentify.setPhone(contractDetailDTO.getNoticePhoneNumber());
            return validateOtp(otpIdentify, otp);
        }
        return true;
    }

    @Override
    public boolean validateOtp(OtpIdentify otpIdentify, String otp) {
        //validate otp
        if (!otpServiceJPA.existsById(otpIdentify)) {
            throw new EtcException("validate.otp.not.exist");
        }
        OtpEntity otpEntity = otpServiceJPA.getById(otpIdentify);
        if (!otpEntity.getOtp().equals(otp)) {
            throw new EtcException("validate.otp.wrong");
        }
        long diff = new Date().getTime() - otpEntity.getSignDate().getTime();
        if (diff > otpEntity.getDuration() * 1000 * 60) {   // diff in minute
            throw new EtcException("validate.opt.expired");
        }
        otpServiceJPA.delete(otpEntity);
        return true;
    }

    /**
     * Check otp xem da duoc gui vao sdt chua
     *
     * @param phone
     * @param confirmType
     * @return
     */
    @Override
    public boolean isSendOtp(String phone, Integer confirmType) {
        return otpServiceJPA.isSendOTPByPhoneAndConfirmType(phone, confirmType);
    }
}
