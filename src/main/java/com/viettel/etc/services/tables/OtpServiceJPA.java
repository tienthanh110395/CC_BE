package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.OtpRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.OtpEntity;
import com.viettel.etc.repositories.tables.entities.OtpIdentify;
import com.viettel.etc.utils.FnCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtpServiceJPA {
    @Autowired
    OtpRepositoryJPA otpRepositoryJPA;

    public Boolean existsById(OtpIdentify id) {
        return otpRepositoryJPA.existsById(id);
    }

    public OtpEntity getById(OtpIdentify id) {
        return otpRepositoryJPA.getOne(id);
    }

    public OtpEntity save(OtpEntity otp) {
        return otpRepositoryJPA.save(otp);
    }

    public void delete(OtpEntity otp) {
        otpRepositoryJPA.delete(otp);
    }

    public boolean isSendOTPByPhoneAndConfirmType(String phone, Integer confirmType) {
        phone = FnCommon.getPhoneNumber(phone);
        List<OtpEntity> otpEntities = otpRepositoryJPA.findByPhoneAndConfirmType(phone, confirmType);
        return !FnCommon.isNullOrEmpty(otpEntities);
    }
}
