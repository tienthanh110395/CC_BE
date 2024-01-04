package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.OtpEntity;
import com.viettel.etc.repositories.tables.entities.OtpIdentify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpRepositoryJPA extends JpaRepository<OtpEntity, OtpIdentify> {
    @Query("SELECT o FROM OtpEntity o WHERE o.phone = :phone AND o.confirmType = :confirmType AND current_date < o.signDate + (o.duration * 1/1440)")
    List<OtpEntity> findByPhoneAndConfirmType(String phone, Integer confirmType);
}
