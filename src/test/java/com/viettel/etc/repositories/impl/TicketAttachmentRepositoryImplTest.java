package com.viettel.etc.repositories.impl;

import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class TicketAttachmentRepositoryImplTest {
    @InjectMocks
    private TicketAttachmentRepositoryImpl ticketAttachmentRepositoryImplUnderTest;


    @Test
    void testGetFileAttachInfo() {
        // Setup
        final TicketAttachmentEntity parrams = new TicketAttachmentEntity();
        parrams.setAttachmentId(0L);
        parrams.setTicketId(0L);
        parrams.setFileName("fileName");
        parrams.setFilePath("filePath");
        parrams.setDescription("description");
        parrams.setType(0L);
        parrams.setObjectsId(0L);
        parrams.setCreateUser("createUser");
        parrams.setCreateDate(new Date(0L));
        parrams.setUpdateUser("updateUser");



        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketAttachmentRepositoryImplUnderTest.getTicketAttachment(parrams));
    }

    @Test
    void testGetTicketAttachment() {
        // Setup
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketAttachmentRepositoryImplUnderTest.getTicketAttachment(ticketAttachmentEntity);

        // Verify the results
        Assertions.assertNull(result);
    }
}
