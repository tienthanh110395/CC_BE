package com.viettel.etc.services;

import com.viettel.etc.dto.*;
import com.viettel.etc.utils.exceptions.EtcException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface TicketCateStatisticService {
    Object searchTicketCateStatistic(Authentication authentication, TicketCateStatisticSearchDTO dataParams);

    boolean changeStatusTicketCateStatistic(TicketCateStatisticDTO dataParams, Authentication authentication);

    boolean changeStatusMultipleTicketCateStatistic(TicketCateStatisticDTO dataParams, Authentication authentication);

    Object getTicketCateStatisticById(TicketCateStatisticDTO params, Authentication authentication);

    Boolean onDeleteStatistic(Long statisticTypeId, Authentication authentication);

    Object createCateStatistic(TicketCateStatisticDTO dataParams, Authentication authentication);

    Object getDataDetailTicketCateStatistic(Long statisticTypeId, TicketCateStatisticDTO params);

    void exportFile(TicketCateStatisticSearchDTO params, HttpServletResponse response);

    ResponseEntity<?> importFile(Authentication authentication, MultipartFile fileImport) throws EtcException, IOException;

    void downloadTemplate(Authentication authentication, HttpServletResponse response);

}
