package com.viettel.etc.controllers;

import com.viettel.etc.dto.MapErrorCauseSearchDTO;
import com.viettel.etc.dto.MapErrorUpdateDTO;
import com.viettel.etc.dto.TicketSLANewDTO;
import com.viettel.etc.repositories.tables.entities.MapErrorEntity;
import com.viettel.etc.services.MapErrorCauseService;
import com.viettel.etc.services.TicketCateConfigService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Autogen class: Lớp thao tác map nguyên nhân lỗi
 *
 * @author ThaiBQ
 * @date 07/06/2023
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class MapTicketErrorCauseController {

    @Autowired
    MapErrorCauseService mapErrorCauseService;

    @Autowired
    TicketCateConfigService ticketCateConfigService;

    /***
     * Lấy danh sách nguyên nhân lỗi
     * @param authentication
     * @param dataParams
     * @return
     */

    @PostMapping(value = "/search-data-map-error-cause", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> doSearch(@AuthenticationPrincipal Authentication authentication,
                                           @RequestBody MapErrorCauseSearchDTO dataParams) {
        Object result = mapErrorCauseService.searchMapErrorCause(authentication, dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /***
     * Thêm mới Map nguyên nhân lỗi
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/map-error-cause-create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveMapError(@AuthenticationPrincipal Authentication authentication,
                                               @RequestBody List<MapErrorEntity> dataParams) {
        Object resultObj = mapErrorCauseService.saveMapError(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xóa 1 Map nguyen nhan loi
     * @param authentication
     * @param mapErrorCauseId
     * @return
     */
    @DeleteMapping(value = "/delete-map-error-cause/{mapErrorCauseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> onDelete(@AuthenticationPrincipal Authentication authentication,
                                           @Valid @PathVariable(name = "mapErrorCauseId") Long mapErrorCauseId) {
        Object resultObj = mapErrorCauseService.onDelete(mapErrorCauseId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Lấy danh sách thể loại phản ánh ở màn Tạo mới Map nguyên nhân lỗi
     *
     * @param authentication
     * @param parentId
     * @return
     */
    @GetMapping(value = "/ticket-type-by-parent-id-for-map/{parentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketTypeByParentIdForMap(@AuthenticationPrincipal Authentication authentication,
                                                                @PathVariable Long parentId) {
        Object resultObj = ticketCateConfigService.getTicketTypeByParentIdForMapping(parentId);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Lấy danh sách data đã map ở màn Cập nhât nguyên nhân lỗi
     *
     * @param authentication
     * @param ticketGenreId  (ID thể loại phản ánh)
     * @return
     */
    @PostMapping(value = "/search-data-update_map-error-cause/{ticketGenreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> doSearchForUpdate(@AuthenticationPrincipal Authentication authentication,
                                                    @Valid @PathVariable(name = "ticketGenreId") Long ticketGenreId) {
        Object resultObj = mapErrorCauseService.searchDataMapForUpdate(ticketGenreId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xuat file excel map nguyên nhân lỗi
     * @param authentication
     * @param dataParams
     * @param response
     * @throws IOException
     */
    @PostMapping(value = "/map-error/exports", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportMapErrorCause(@AuthenticationPrincipal Authentication authentication,
                                    @RequestBody MapErrorCauseSearchDTO dataParams, HttpServletResponse response) {
        mapErrorCauseService.exportMapErrorCause(dataParams, response);
    }

    /**
     * Tai file template mapping nguyên nhân lỗi
     *
     * @param authentication Ma xac thuc
     * @return File template response
     */
    @PostMapping("/map-error/download-template")
    public void downloadMapErrorCauseTemplate(@AuthenticationPrincipal Authentication authentication, HttpServletResponse response) {
        mapErrorCauseService.downloadMapErrorCauseTemplate(authentication, response);
    }

    /**
     * Lấy danh sách nguyên nhân lỗi theo parent_id
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/error-cause-by-parent-id-for-update-map", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getErrorCauseByParentIdForUpdateMap(@AuthenticationPrincipal Authentication authentication,
                                                                      @RequestBody MapErrorCauseSearchDTO dataParams) {
        Object resultObj = mapErrorCauseService.getErrorCauseByParentIdForUpdateMap(authentication, dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Cập nhật Map nguyên nhân lỗi
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/map-error-cause-update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMapError(@AuthenticationPrincipal Authentication authentication,
                                                 @RequestBody List<MapErrorUpdateDTO> dataParams) {
        Object resultObj = mapErrorCauseService.updateMapError(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
