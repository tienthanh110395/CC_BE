package com.viettel.etc.services.tables;
import com.viettel.etc.repositories.tables.entities.TicketExtentReasonEntity;
import com.viettel.etc.repositories.tables.TicketExtentReasonRepositoryJPA;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Autogen class: Create Service For Table Name Ticket_extent_reason
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:57:18 ICT 2022
 */
@Service
public class TicketExtentReasonServiceJPA {
    @Autowired
    TicketExtentReasonRepositoryJPA ticket_extent_reason;
    public List<TicketExtentReasonEntity>  findAll() {
        return this.ticket_extent_reason.findAll();
    }
    public TicketExtentReasonEntity save(TicketExtentReasonEntity Ticket_extent_reason) {
        return this.ticket_extent_reason.save(Ticket_extent_reason);
    }
    public Optional<TicketExtentReasonEntity> findById(Long id) {
        return this.ticket_extent_reason.findById(id);
    }
    public void deleteById(Long id) {
        this.ticket_extent_reason.deleteById(id);
    }
    public TicketExtentReasonEntity getOne(Long id) {
        return this.ticket_extent_reason.getOne(id);
    }
    public Boolean existsById(Long id) {
        return this.ticket_extent_reason.existsById(id);
    }

}