package com.viettel.etc.utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.entities.ExcellDataEntity;
import com.viettel.etc.xlibrary.core.entities.ExcellHeaderEntity;
import com.viettel.etc.xlibrary.core.entities.ExcellSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class FnCommonTest {

    @Test
    void testTrim() {
        assertThat(FnCommon.trim("s")).isEqualTo("s");
    }

    @Test
    void testRemoveAccent() {
        assertThat(FnCommon.removeAccent("s")).isEqualTo("s");
    }

    @Test
    void testGetUserLogin() {
        // Setup
        final Authentication authentication = null;

        // Run the test
        final String result = FnCommon.getUserLogin(authentication);

        // Verify the results
    }

    @Test
    void testGetStringToken() {
        // Setup
        final Authentication authentication = null;

        // Run the test
        final String result = FnCommon.getStringToken(authentication);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @Test
    void testGetIdUserLogin() {
        // Setup
        final Authentication authentication = null;

        // Run the test
        final String result = FnCommon.getIdUserLogin(authentication);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @Test
    void testIsNullOrEmpty1() {
        assertThat(FnCommon.isNullOrEmpty("toTest")).isFalse();
    }

    @Test
    void testIsNullOrBlank1() {
        assertThat(FnCommon.isNullOrBlank("toTest")).isFalse();
    }

    @Test
    void testIsNullOrBlank2() {
        // Setup
        final Cell toTest = null;

        // Run the test
        final boolean result = FnCommon.isNullOrBlank(toTest);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testCheckDateIsAfter_ThrowsEtcException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> FnCommon.checkDateIsAfter(Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)))).isInstanceOf(EtcException.class);
    }

    @Test
    void testCheckFileExtensionValid() {
        assertThat(FnCommon.checkFileExtensionValid("fileName", "fileExtensions")).isFalse();
    }

    @Test
    void testSetOkHtppClient() {
        // Setup
        final OkHttpClient client = new OkHttpClient();

        // Run the test
        FnCommon.setOkHtppClient(client);

        // Verify the results
    }

    @Test
    void testDoGetRequest() {
        // Setup
        final Map<String, String> params = new HashMap<>();

        // Run the test
        final String result = FnCommon.doGetRequest("url", params, "token");

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @Test
    void testDoPutRequest() {
        // Setup
        final RequestBody requestBody = RequestBody.create(com.squareup.okhttp.MediaType.parse("string"), "content");

        // Run the test
        final Response result = FnCommon.doPutRequest("url", "token", requestBody);

        // Verify the results
    }

    @Test
    void testDoPostRequest() {
        assertThat(FnCommon.doPostRequest("url", "token", "obj")).isEqualTo(null);
    }

    @Test
    void testGetAttribute() {
        // Setup
        final Authentication authentication = null;

        // Run the test
        final Map<String, Object> result = FnCommon.getAttribute(authentication);

        // Verify the results
    }

    @Test
    void testGetRoleId() {
        // Setup
        final Authentication authentication = null;

        // Run the test
        final Set<String> result = FnCommon.getRoleId(authentication);

        // Verify the results
    }

    @Test
    void testGetClientId() {
        // Setup
        final Authentication authentication = null;

        // Run the test
        final String result = FnCommon.getClientId(authentication);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @Test
    void testGetStringValue() {
        // Setup
        final Cell cell = null;

        // Run the test
        final String result = FnCommon.getStringValue(cell);

        // Verify the results
        assertThat(result).isEqualTo("");
    }

    @Test
    void testIsNullObject() {
        assertThat(FnCommon.isNullObject("obj")).isFalse();
    }

    @Test
    void testIsNullOrEmpty2() {
        assertThat(FnCommon.isNullOrEmpty("s")).isFalse();
    }

    @Test
    void testIsNullOrEmpty3() {
        // Setup
        final Collection<?> collection = Arrays.asList();

        // Run the test
        final boolean result = FnCommon.isNullOrEmpty(collection);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testIsNullOrEmpty4() {
        assertThat(FnCommon.isNullOrEmpty(new Object[]{"value"})).isFalse();
    }

    @Test
    void testIsNullOrEmpty5() {
        // Setup
        final Map<?, ?> map = new HashMap<>();

        // Run the test
        final boolean result = FnCommon.isNullOrEmpty(map);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testCreateFileName() {
        assertThat(FnCommon.createFileName("originalFilename", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).isEqualTo("originalFilename_000");
    }

    @Test
    void testFormatVNDCurrency1() {
        assertThat(FnCommon.formatVNDCurrency(new BigDecimal("0.00"))).isEqualTo("0");
    }

    @Test
    void testFormatVNDCurrency2() {
        assertThat(FnCommon.formatVNDCurrency(0.0)).isEqualTo("0");
    }

    @Test
    void testFormatVNDCurrency3() {
        assertThat(FnCommon.formatVNDCurrency(0L)).isEqualTo("0");
    }

    @Test
    void testRound1() {
        assertThat(FnCommon.round(0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testValidateMaxlengthDouble() {
        assertThat(FnCommon.validateMaxlengthDouble(0.0, 0)).isFalse();
    }

    @Test
    void testValidateMaxRow() {
        assertThat(FnCommon.validateMaxRow(0)).isFalse();
    }

    @Test
    void testValidateMaxlengthString() {
        assertThat(FnCommon.validateMaxlengthString("string", 0)).isTrue();
    }

    @Test
    void testFormatPlateBOO1() {
        assertThat(FnCommon.formatPlateBOO1("plate")).isEqualTo("plate");
    }

    @Test
    void testGetPlateTypeBOO1() {
        assertThat(FnCommon.getPlateTypeBOO1("plate")).isEqualTo("1");
    }

    @Test
    void testCheckQuarter() {
        assertThat(FnCommon.checkQuarter(Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "startDate", "endDate")).isFalse();
    }

    @Test
    void testGetDayOfMonth() {
        assertThat(FnCommon.getDayOfMonth(Date.valueOf(LocalDate.of(2020, 1, 1)))).isEqualTo(1);
    }

    @Test
    void testGetMaxDayOfMonth() {
        assertThat(FnCommon.getMaxDayOfMonth(Date.valueOf(LocalDate.of(2020, 1, 1)))).isEqualTo(31);
    }

    @Test
    void testConvertTicketType() {
        assertThat(FnCommon.convertTicketType("ticketType")).isEqualTo("");
    }

    @Test
    void testConvertToTicketType() {
        assertThat(FnCommon.convertToTicketType("servicePlanTypeId")).isEqualTo("");
    }

    @Test
    void testConvertStationType() {
        assertThat(FnCommon.convertStationType(0L)).isEqualTo("C");
    }

    @Test
    void testValidatePlateContainsTVX() {
        assertThat(FnCommon.validatePlateContainsTVX("plate")).isFalse();
    }

    @Test
    void testCheckPlate5Number2Char() {
        assertThat(FnCommon.checkPlate5Number2Char("plate")).isFalse();
    }

    @Test
    void testGetPlateNumberBoo1() {
        assertThat(FnCommon.getPlateNumberBoo1("plate", "plateType")).isEqualTo("plateplateType");
    }

    @Test
    void testResponseFile1_ThrowsIOException() {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> FnCommon.responseFile(response, "fileName")).isInstanceOf(IOException.class);
    }

    @Test
    void testResponseFile2() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        FnCommon.responseFile(response, "content".getBytes(), "fileName");

        // Verify the results
    }

    @Test
    void testGetPlateTypeCodeFromPlateNumber() {
        assertThat(FnCommon.getPlateTypeCodeFromPlateNumber("plateNumber")).isEqualTo("1");
    }

    @Test
    void testMappingPlateTypeBOO2ToBOO1() {
        assertThat(FnCommon.mappingPlateTypeBOO2ToBOO1("plateTypeCode")).isEqualTo("");
    }

    @Test
    void testMappingPlateTypeBOO1ToBOO2() {
        assertThat(FnCommon.mappingPlateTypeBOO1ToBOO2("plateType")).isEqualTo(null);
    }

    @Test
    void testSetTimeOfDate1() {
        assertThat(FnCommon.setTimeOfDate("strDate", 0, 0, 0)).isEqualTo(null);
    }

    @Test
    void testSetTimeOfDate2() {
        assertThat(FnCommon.setTimeOfDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, 0, 0)).isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testUrlDecodeString() {
        assertThat(FnCommon.urlDecodeString("input")).isEqualTo("input");
    }

    @Test
    void testUrlEncodeString() {
        assertThat(FnCommon.urlEncodeString("input")).isEqualTo("input");
    }

    @Test
    void testUrlDecodeCheckSumString() {
        assertThat(FnCommon.urlDecodeCheckSumString("input")).isEqualTo("input");
    }

    @Test
    void testValidateCommonFormatDate() {
        assertThat(FnCommon.validateCommonFormatDate("strDate")).isFalse();
    }

    @Test
    void testDiffDays() {
        assertThat(FnCommon.diffDays(0L, 0L)).isEqualTo(0L);
    }

    @Test
    void testAddDays() {
        assertThat(FnCommon.addDays(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testRound2() {
        assertThat(FnCommon.round(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testAddYears() {
        assertThat(FnCommon.addYears(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testAddYearsSql() {
        assertThat(FnCommon.addYearsSql(Date.valueOf(LocalDate.of(2020, 1, 1)), 0)).isEqualTo(Date.valueOf(LocalDate.of(2020, 1, 1)));
    }

    @Test
    void testReturnFileExcel1() {
        // Setup
        // Run the test
        final ResponseEntity<?> result = FnCommon.returnFileExcel("content".getBytes(), "fileName");

        // Verify the results
    }

    @Test
    void testReturnFileExcel2() {
        // Setup
        // Run the test
        final ResponseEntity<?> result = FnCommon.returnFileExcel("content".getBytes(), "fileName", 0L, 0L);

        // Verify the results
    }

    @Test
    void testRowIsEmpty() {
        // Setup
        final Row row = null;

        // Run the test
        final boolean result = FnCommon.rowIsEmpty(row);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testBytesToHex() {
        assertThat(FnCommon.bytesToHex("content".getBytes())).isEqualTo("636F6E74656E74");
    }

    @Test
    void testAppendSQL() {
        // Setup
        final HashMap<String, Object> hmapParams = new HashMap<>();

        // Run the test
        FnCommon.appendSQL("param", hmapParams, Arrays.asList("value"), new StringBuilder());

        // Verify the results
    }

    @Test
    void testCheckBriefcaseValid() {
        assertThat(FnCommon.checkBriefcaseValid("fileName", "content".getBytes(), 0)).isFalse();
    }

    @Test
    void testCheckAttachFileValid() {
        assertThat(FnCommon.checkAttachFileValid("fileName", "content".getBytes(), 0)).isFalse();
    }

    @Test
    void testGetErrorField() {
        assertThat(FnCommon.getErrorField("field")).isEqualTo("field");
    }

    @Test
    void testGetPhoneNumber() {
        assertThat(FnCommon.getPhoneNumber("phoneNumber")).isEqualTo("phoneNumber");
    }
}
