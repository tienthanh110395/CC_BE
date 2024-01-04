package com.viettel.etc.services.tables;
import com.viettel.etc.repositories.tables.entities.StatisticEntity;
import com.viettel.etc.repositories.tables.StatisticRepositoryJPA;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Autogen class: Create Service For Table Name Statistic
 * 
 * @author ToolGen
 * @date Thu Dec 02 09:01:16 ICT 2021
 */
@Service
public class StatisticServiceJPA {
    @Autowired
    StatisticRepositoryJPA statistic;
    public List<StatisticEntity>  findAll() {
        return this.statistic.findAll();
    }
    public StatisticEntity save(StatisticEntity Statistic) {
        return this.statistic.save(Statistic);
    }
    public Optional<StatisticEntity> findById(Long id) {
        return this.statistic.findById(id);
    }
    public void deleteById(Long id) {
        this.statistic.deleteById(id);
    }
    public StatisticEntity getOne(Long id) {
        return this.statistic.getOne(id);
    }
    public Boolean existsById(Long id) {
        return this.statistic.existsById(id);
    }

}