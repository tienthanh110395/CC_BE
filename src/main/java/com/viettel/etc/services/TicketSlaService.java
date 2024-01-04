package com.viettel.etc.services;

import com.viettel.etc.dto.*;
import com.viettel.etc.utils.exceptions.EtcException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Wed Jan 26 09:59:24 ICT 2022
 */
public interface TicketSlaService {

    Object getTicketSla(TicketSLADTO itemParamsEntity);

    Object getTicketSlaDetail(Long ticketTypeId, Authentication authentication);

    Object getListDataTicketSLA(Authentication authentication, TicketSlaSearchDTO params);

    Object createOrUpdate(TicketSLANewDTO params, Authentication authentication);

    Object getDataDetail(Long ticketTypeId, TicketSLANewDTO params);

    Boolean doDeleteData(Long ticketTypeId, Authentication authentication);

    void exportTicketSla(TicketSlaSearchDTO params, HttpServletResponse response);

    void downloadServicePlanTemplate(Authentication authentication, HttpServletResponse response);

    ResponseEntity<?> importTicketProcessTime(Authentication authentication, MultipartFile fileImport) throws EtcException, IOException;

    Object updateReception(TicketSLANewDTO params, Authentication authentication);
}