package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.StatisticTypeEntity;
import com.viettel.etc.repositories.tables.entities.TicketErrorCauseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TicketCateStatisticRepositoryJPA extends JpaRepository<StatisticTypeEntity, Long> {

    boolean existsByCode(String code);

    @Modifying
    @Transactional
    @Query(value = "UPDATE statistic_type SET status =:status WHERE statistic_type_id IN (:lstIds)",nativeQuery = true)
    void updateStatusMultipleTicketStatistic(List<Long> lstIds, Long status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE statistic_type SET status =:status WHERE statistic_type_id =:statisticTypeId" ,nativeQuery = true)
    void updateStatusTicketStatisticType(Long statisticTypeId, Long status);

    StatisticTypeEntity findAllByCodeAndLevelStatistic(String code, long levelStatistic);

    StatisticTypeEntity findByCodeAndStatisticTypeIdNot(String ticketCateStatisticsCode , Long statisticTypeId);

    @Modifying
    @Transactional
    @Query(value = "delete (select *FROM statistic_type  st LEFT JOIN statistic_type  st2 ON st.parent_id = st2.statistic_type_id LEFT JOIN statistic_type  st3 ON st2.parent_id = st3.statistic_type_id LEFT JOIN statistic_type  st4 ON st3.parent_id = st4.statistic_type_id LEFT JOIN statistic_type  st5 ON st4.parent_id = st5.statistic_type_id WHERE st.parent_id IN ( :statisticTypeId ) OR ( st.statistic_type_id IN ( :statisticTypeId ) OR ( st2.parent_id IN ( :statisticTypeId ) OR ( st3.parent_id IN ( :statisticTypeId ) OR st4.parent_id IN ( :statisticTypeId ) ) ) ))" ,nativeQuery = true)
    int doDelete(Long statisticTypeId);

    @Query(value = "SELECT CC.STATISTIC_TYPE_SEQ.nextval FROM dual", nativeQuery = true)
    Long getNextValSequenceNo();
}
