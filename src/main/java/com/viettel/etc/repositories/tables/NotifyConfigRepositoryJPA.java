package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.NotifyConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyConfigRepositoryJPA extends JpaRepository<NotifyConfigEntity, Long> {
    List<NotifyConfigEntity> findByType(Long type);
}
