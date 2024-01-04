package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketCateStatisticDTO;
import com.viettel.etc.dto.TicketCateStatisticSearchDTO;
import com.viettel.etc.services.TicketCateStatisticService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1 + "/ticket-cate-statistic")
public class TicketCategoryStatisticController {

    @Autowired
    TicketCateStatisticService ticketCateStatisticService;

    /***
     * Lấy danh sách nguyên nhân lỗi
     * @param authentication
     * @param dataParams
     * @return
     */

    @PostMapping(value = "/search-data-ticket-cate-statistic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> doSearch(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketCateStatisticSearchDTO dataParams) {
        Object result = ticketCateStatisticService.searchTicketCateStatistic(authentication, dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /**
     * Đổi trạng thái 1 thống kê
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/change-status-ticket-cate-statistic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatusTicketErrorCause(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketCateStatisticDTO dataParams) {
        Object resultObj = ticketCateStatisticService.changeStatusTicketCateStatistic(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * lay list data thông kê
     * @param authentication
     * @param params
     * @return
     */
    @PostMapping(value = "/ticket-cate-statistic-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketCateStatisticById(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketCateStatisticDTO params) {
        Object resultObj = ticketCateStatisticService.getTicketCateStatisticById(params, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Doi trang thai multiple:  nguyen nhan loi
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/change-multiple-status-ticket-cate-statistic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatusMultipleTicketCateStatistic(@RequestBody TicketCateStatisticDTO dataParams, @AuthenticationPrincipal Authentication authentication) {
        Object resultObj = ticketCateStatisticService.changeStatusMultipleTicketCateStatistic(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xóa 1 bản ghi thông kê
     * @param statisticTypeId
     * @return
     */
    @DeleteMapping(value = "/delete-statistic/{statisticTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> onDeleteStatistic(@AuthenticationPrincipal Authentication authentication,
                                                    @Valid @PathVariable(name = "statisticTypeId") Long statisticTypeId) {
        Object resultObj = ticketCateStatisticService.onDeleteStatistic(statisticTypeId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Tạo mới thống kê
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/ticket-cate-statistic-create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCateStatistic(@AuthenticationPrincipal Authentication authentication,
                                                      @RequestBody TicketCateStatisticDTO dataParams) {
        Object resultObj = ticketCateStatisticService.createCateStatistic(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay chi tiet thống kê
     * @param authentication
     * @param statisticTypeId
     * @return
     */
    @GetMapping(value = "/data-detail-statistic/{statisticTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataDetail(@AuthenticationPrincipal Authentication authentication,
                                                @Valid @PathVariable(name = "statisticTypeId") Long statisticTypeId, TicketCateStatisticDTO params) {
        Object resultObj = ticketCateStatisticService.getDataDetailTicketCateStatistic(statisticTypeId, params);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xuat excel danh sach thống kê
     * @param params
     * @param response
     */
    @PostMapping(value = "/export-cate-statistic-log", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportTicket(@RequestBody TicketCateStatisticSearchDTO params, HttpServletResponse response) {
        ticketCateStatisticService.exportFile(params, response);
    }

    /**
     * Tai file template mapping nguyên nhân lỗi
     *
     * @param authentication Ma xac thuc
     * @return File template response
     */
    @PostMapping("/download-template")
    public void downloadTemplate(@AuthenticationPrincipal Authentication authentication, HttpServletResponse response) {
        ticketCateStatisticService.downloadTemplate(authentication, response);
    }

    @PostMapping("/import-file")
    public ResponseEntity<?> importExceptionList(@AuthenticationPrincipal Authentication authentication,
                                                 @RequestPart(name = "file") MultipartFile excelFile) throws IOException {
        return ticketCateStatisticService.importFile(authentication, excelFile);
    }
}