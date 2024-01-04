package com.viettel.etc.services.tables;
import com.viettel.etc.repositories.tables.entities.StatisticTypeEntity;
import com.viettel.etc.repositories.tables.StatisticTypeRepositoryJPA;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Autogen class: Create Service For Table Name Statistic_type
 * 
 * @author ToolGen
 * @date Wed Dec 01 13:45:15 ICT 2021
 */
@Service
public class StatisticTypeServiceJPA {
    @Autowired
    StatisticTypeRepositoryJPA statistic_type;
    public List<StatisticTypeEntity>  findAll() {
        return this.statistic_type.findAll();
    }
    public StatisticTypeEntity save(StatisticTypeEntity Statistic_type) {
        return this.statistic_type.save(Statistic_type);
    }
    public Optional<StatisticTypeEntity> findById(Long id) {
        return this.statistic_type.findById(id);
    }
    public void deleteById(Long id) {
        this.statistic_type.deleteById(id);
    }
    public StatisticTypeEntity getOne(Long id) {
        return this.statistic_type.getOne(id);
    }
    public Boolean existsById(Long id) {
        return this.statistic_type.existsById(id);
    }

}