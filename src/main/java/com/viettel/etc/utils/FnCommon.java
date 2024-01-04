package com.viettel.etc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.squareup.okhttp.*;
import com.viettel.etc.dto.ResKeycloakDTO;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.*;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Phuong thuc chung cho toan bo project
 */
@Log4j
public class FnCommon extends FunctionCommon {

    private static final Logger LOGGER = Logger.getLogger(FnCommon.class);
    private static final org.slf4j.Logger LOGNEW = LoggerFactory.getLogger(FnCommon.class);


    public static Boolean exportFileExcell(ExcellSheet sheetExprort, String strPathExportFile, String strTitle, String fontName) {
        if (sheetExprort != null && strPathExportFile != null && strPathExportFile.trim().length() > 0) {
            try {
                Workbook workbook = new XSSFWorkbook();
                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);
                CellStyle headerStyleHeader = workbook.createCellStyle();
                headerStyleHeader.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
                headerStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyleHeader.setAlignment(HorizontalAlignment.CENTER);
                headerStyleHeader.setWrapText(true);
                XSSFFont font = ((XSSFWorkbook) workbook).createFont();
                if (!FnCommon.isNullOrEmpty(fontName)) {
                    font.setFontName(fontName);
                } else {
                    font.setFontName("Arial");
                }
                font.setFontHeightInPoints((short) 14);
                font.setBold(true);
                headerStyle.setFont(font);
                headerStyleHeader.setFont(font);
                String strNameSheet = sheetExprort.getStrSheetName() != null && sheetExprort.getStrSheetName().trim().length() > 0 ? sheetExprort.getStrSheetName() : "Sheet1";
                Sheet sheet = workbook.createSheet(strNameSheet);
                List<ExcellHeaderEntity> listHeader = sheetExprort.getListHeader();

                int rowsIndex;
                int j;
                for (rowsIndex = 0; rowsIndex < listHeader.size(); ++rowsIndex) {
                    ExcellHeaderEntity excellHeaderEntity = (ExcellHeaderEntity) listHeader.get(rowsIndex);
                    j = 6000;
                    if (excellHeaderEntity.getWidth() != null && excellHeaderEntity.getWidth() > 0) {
                        j = excellHeaderEntity.getWidth();
                    }

                    sheet.setColumnWidth(rowsIndex, j);
                }

                rowsIndex = 0;
                Row header;
                if (strTitle != null && strTitle.trim().length() > 0) {
                    header = sheet.createRow(0);
                    Pattern p = Pattern.compile("\n");
                    Matcher m = p.matcher(strTitle);

                    for (j = 2; m.find(); ++j) {
                    }

                    header.setHeightInPoints((float) j * sheet.getDefaultRowHeightInPoints());
                    Cell cellHeader = header.createCell(0);
                    cellHeader.setCellValue(strTitle);
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, listHeader.size() - 1));
                    cellHeader.setCellStyle(headerStyleHeader);
                    ++rowsIndex;
                }

                header = sheet.createRow(rowsIndex);
                ++rowsIndex;

                for (j = 0; j < listHeader.size(); ++j) {
                    ExcellHeaderEntity excellHeaderEntity = (ExcellHeaderEntity) listHeader.get(j);
                    Cell headerCell = header.createCell(j);
                    headerCell.setCellValue(excellHeaderEntity.getHeaderName());
                    headerCell.setCellStyle(headerStyle);
                }

                CellStyle style = workbook.createCellStyle();
                style.setWrapText(false);
                ExcellDataEntity listData = sheetExprort.getExcellDataEntity();

                for (j = 0; j < listData.getListData().size(); ++j) {
                    List<Object> listObjectRow = (List) listData.getListData().get(j);
                    Row row = sheet.createRow(rowsIndex);
                    ++rowsIndex;

                    for (int k = 0; k < listObjectRow.size(); ++k) {
                        Cell cell = row.createCell(k);
                        if (checkIsNumber(listObjectRow.get(k))) {
                            cell.setCellValue(Double.valueOf(listObjectRow.get(k).toString()));
                        } else if (listObjectRow.get(k) == null) {
                            cell.setCellValue("");
                        } else {
                            cell.setCellValue(String.valueOf(listObjectRow.get(k)));
                        }

                        cell.setCellStyle(style);
                    }
                }

                FileOutputStream outputStream = new FileOutputStream(strPathExportFile);
                workbook.write(outputStream);
                workbook.close();
                return true;
            } catch (IOException var19) {
                LOGGER.error(var19);
                return false;
            }
        } else {
            return false;
        }
    }

    private static Boolean checkIsNumber(Object o) {
        return o instanceof Integer || o instanceof Double || o instanceof Float || o instanceof Long;
    }

    /**
     * Convert class to json string
     *
     * @param object
     * @return
     */
    public static String toStringJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("FnCommon toStringJson error: ", e);
            throw new EtcException("common.error.convert.json");
        }
    }

    /**
     * Trim string
     *
     * @param s string
     * @return
     */
    public static String trim(String s) {
        return s == null ? null : s.trim();
    }

    /**
     * Go bo dau tieng viet
     *
     * @param s
     * @return
     */
    public static String removeAccent(String s) {
        if (s == null) {
            return "";
        }
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace("đ", "d").replace("Đ", "D");
    }

    /***
     * tra du lieu ve client
     * @param code
     * @param description
     * @return
     */

    public static Object responseToClient(int code, String description) {
        ResponseEntity responseEntity = new ResponseEntity();
        MessEntity itemEntity = new MessEntity();
        itemEntity.setCode(code);
        itemEntity.setDescription(description);
        responseEntity.setMess(itemEntity);
        return responseEntity;
    }

    /**
     * lay thong tin nguoi dung
     *
     * @param authentication
     * @return
     */
    public static String getUserLogin(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            return principal.getKeycloakSecurityContext().getToken().getPreferredUsername().toUpperCase();
        } catch (Exception e) {
            LOGGER.error("Loi! getUserLogin: ", e);
            return null;
        }
    }

    /**
     * lay chuoi string token
     *
     * @param authentication
     * @return
     */
    public static String getStringToken(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            return principal.getKeycloakSecurityContext().getTokenString();
        } catch (Exception e) {
            LOGGER.error("Loi! getUserLogin: ", e);
            return null;
        }
    }

    public static String getIdUserLogin(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            return principal.getKeycloakSecurityContext().getToken().getSubject();
        } catch (Exception e) {
            LOGGER.error("Loi! getIdUserLogin: ", e);
            return null;
        }
    }

    /**
     * kiem tra truong hop value == null return ""
     *
     * @param obj
     * @return
     */
    public static String retNull(Object obj) {
        return obj == null ? null : obj.toString();
    }

    /**
     * kiem tra truong hop value == null return 0l
     *
     * @param obj
     * @return
     */
    public static Long retlong(Object obj) {
        return obj == null ? 0L : Long.parseLong(obj.toString());
    }

    /**
     * kiem tra truong hop value == null return null
     *
     * @param obj
     * @return
     */
    public static Long checkLong(Object obj) {
        return obj == null ? null : Long.valueOf(obj.toString());
    }

    /**
     * Convert String => list String by : ","
     *
     * @param stringArr
     * @return
     */
    public static List<String> convertStringToList(String stringArr) {
        if (stringArr != null && !"".equals(stringArr)) {
            return Arrays.asList(stringArr.split(","));
        }
        return new ArrayList<>();
    }

    public static boolean isNullOrEmpty(String toTest) {

        return toTest == null || toTest.isEmpty();
    }

    public static boolean isNullOrBlank(String toTest) {
        return StringUtils.isBlank(toTest);
    }

    public static boolean isNullOrBlank(Cell toTest) {
        return toTest == null || toTest.getCellType().equals(CellType.BLANK);
    }

    /**
     * kiem tra ngay truyen vao param 1 co lon hon ngay cua param 2 hay khong
     *
     * @param effDate
     * @param expDate
     * @return
     */
    public static void checkDateIsAfter(Date effDate, Date expDate) throws EtcException {
        if (Objects.isNull(expDate)) {
            return;
        }
        if (Objects.isNull(effDate) || effDate.getTime() >= expDate.getTime()) {
            throw new EtcException("common.invalid.expire-date");
        }
    }

    /**
     * Su dung: download file, tra ve MediaType
     *
     * @param servletContext
     * @param fileName
     * @return
     */
    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        String mineType = servletContext.getMimeType(fileName);
        try {
            return MediaType.parseMediaType(mineType);
        } catch (Exception e) {
            LOGGER.error("Has ERROR ", e);
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public static boolean checkFileExtensionValid(String fileName, String... fileExtensions) {
        Objects.requireNonNull(fileName);
        for (String fileExtension : fileExtensions) {
            if (fileName.toLowerCase().endsWith(fileExtension.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkFileValid(String fileName, byte[] file, Integer maxFileSizeMb) {
        if (Objects.isNull(maxFileSizeMb)) {
            maxFileSizeMb = 15;
        }
        Objects.requireNonNull(file);
        long fileSizeMb = file.length / (1024 * 1024);
        return checkFileExtensionValid(fileName, ".JPG", ".PNG", ".TIFF", ".BMP", ".PDF", ".JPEG", ".WEBP", "RAR", "ZIP", "XLSX", "XLS", "TXT", "DOC", "DOCX") && fileSizeMb <= maxFileSizeMb;
    }

    public static void setOkHtppClient(OkHttpClient client) {
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
    }

    /**
     * Gui request den server categories
     *
     * @param url
     * @param params
     * @param token
     * @return
     */
    public static String doGetRequest(String url, Map<String, String> params, String token) {

        String strRes = null;
        OkHttpClient client = new OkHttpClient();

        try {

            LOGNEW.info("GET USER KEYCLOAK START");

            setOkHtppClient(client);
            HttpUrl.Builder httpBuider = HttpUrl.parse(url).newBuilder();

            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    httpBuider.addQueryParameter(param.getKey(), param.getValue());
                }
            }
            Request request = null;
            if (token != null) {
                request = new Request.Builder()
                        .header("Accept", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .url(httpBuider.build())
                        .get()
                        .build();
            } else {
                request = new Request.Builder()
                        .url(httpBuider.build())
                        .get()
                        .build();
            }
            Response response = client.newCall(request).execute();
            strRes = response.body().string();
            Gson gson = new Gson();
            LOGNEW.info("GET USER KEYCLOAK SUCCESS: {}", gson.toJson(response));
            return strRes.replace("null", "\"\"");
        } catch (Exception e) {
            LOGNEW.info("GET USER KEYCLOAK ERROR: {}", e.getMessage());
        }
        return strRes;
    }

    /**
     * Gui request den server keycloak
     *
     * @param url
     * @param token
     * @param requestBody
     * @return
     */
    public static Response doPutRequest(String url, String token, RequestBody requestBody) {
        OkHttpClient client = new OkHttpClient();
        try {
            setOkHtppClient(client);
            HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
            Request request = new Request.Builder()
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .url(httpBuilder.build())
                    .put(requestBody)
                    .build();
            return client.newCall(request).execute();
        } catch (Exception e) {
            LOGGER.error("Has error", e);
        }
        return null;
    }

    public static String doPostRequest(String url, String token, Object obj) {
        OkHttpClient client = new OkHttpClient();
        try {
            setOkHtppClient(client);
            HttpUrl.Builder httpBuider = HttpUrl.parse(url).newBuilder();
            RequestBody body = RequestBody.create(Constants.JSON, FnCommon.toStringJson(obj));
            Request request = new Request.Builder()
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .url(httpBuider.build())
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            LOGGER.error("Has error", e);
        }
        return null;
    }

    /**
     * Lấy thông tin attribute của user trong token
     *
     * @param authentication
     * @return
     */
    public static Map<String, Object> getAttribute(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            AccessToken token = principal.getKeycloakSecurityContext().getToken();
            return token.getOtherClaims();
        } catch (Exception e) {
            LOGGER.error("Loi! getAttribute: ", e);
            return null;
        }
    }

    /**
     * lay role nguoi dung
     *
     * @param authentication
     * @return
     */
    public static Set<String> getRoleId(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            Set<String> roleId = principal.getKeycloakSecurityContext().getToken().getResourceAccess().get(FnCommon.getClientId(authentication)).getRoles();
            return roleId;
        } catch (Exception e) {
            LOGGER.error("Loi! getUserLogin: ", e);
            return null;
        }
    }


    /**
     * lay thong tin nguoi dung
     *
     * @param authentication
     * @return
     */
    public static String getClientId(Authentication authentication) {
        try {
            KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
            String clientId = principal.getKeycloakSecurityContext().getToken().getIssuedFor();
            return clientId;
        } catch (Exception e) {
            LOGGER.error("Loi! getUserLogin: ", e);
            return null;
        }
    }


    /**
     * Lay gia tri String cua cell
     *
     * @param cell
     */
    public static String getStringValue(Cell cell) {
        try {
            if (cell == null) {
                return "";
            } else if (CellType.BLANK == cell.getCellType()) {
                return "";
            } else if (CellType.BOOLEAN == cell.getCellType()) {
                return cell.getBooleanCellValue() + "";
            } else if (CellType.ERROR == cell.getCellType()) {
                return null;
            } else if (CellType.FORMULA == cell.getCellType()) {
                return cell.getCellFormula();
            } else if (CellType.NUMERIC == cell.getCellType()) {
                return String.format("%.0f", cell.getNumericCellValue());
            } else if (CellType.STRING == cell.getCellType()) {
                return cell.getStringCellValue();
            }
        } catch (Exception e) {
            LOGGER.error("Error when cast value to String", e);
        }
        return "";
    }

    /**
     * kiem tra obj co null khong
     *
     * @param obj
     * @return
     */
    public static boolean isNullObject(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            return isNullOrEmpty(obj.toString());
        }
        return false;
    }

    /**
     * kiem tra null hoac rong
     *
     * @param s
     * @return
     */
    public static boolean isNullOrEmpty(CharSequence s) {
        int strLengt;
        if (s == null || (strLengt = s.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLengt; i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * kiem tra null hoac rong
     *
     * @param collection
     * @return
     */
    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * kiem tra null hoac rong
     *
     * @param objects
     * @return
     */
    public static boolean isNullOrEmpty(final Object[] objects) {
        return objects == null || objects.length == 0;
    }

    /**
     * kiem tra null hoac rong
     *
     * @param map
     * @return
     */
    public static boolean isNullOrEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Create file name
     *
     * @param originalFilename
     * @param date
     * @return
     */
    public static String createFileName(String originalFilename, java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return originalFilename + "_" + calendar.get(Calendar.HOUR_OF_DAY) + ""
                + calendar.get(Calendar.MINUTE) + "" + calendar.get(Calendar.SECOND);
    }

    /**
     * Dinh dang tien te
     *
     * @param currency
     * @return
     */
    public static String formatVNDCurrency(BigDecimal currency) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        if (currency == null) {
            currency = new BigDecimal(0);
        }
        return formatter.format(currency);
    }

    /**
     * Dinh dang tien te
     *
     * @param currency
     * @return
     */
    public static String formatVNDCurrency(Double currency) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        if (currency == null) {
            currency = new Double(0);
        }
        return formatter.format(currency);
    }

    /**
     * Dinh dang tien te
     *
     * @param currency
     * @return
     */
    public static String formatVNDCurrency(Long currency) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        if (currency == null) {
            currency = new Long(0);
        }
        return formatter.format(currency);
    }

    //region kiem tra date
    public static boolean isDate(String date, String pattern) {
        if (isNullOrEmpty(date)) {
            return false;
        }

        try {
            DateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    //endregion

    public static boolean isAfterDay(String dateStr1, String dateStr2, String pattern) {
        String patternDefault = isNullOrEmpty(pattern) ? Constants.COMMON_DATE_FORMAT : pattern;
        DateFormat df = new SimpleDateFormat(patternDefault);
        if (isNullOrEmpty(dateStr1) || isNullOrEmpty(dateStr2)) {
            return false;
        }
        try {
            java.util.Date date1 = df.parse(dateStr1);
            java.util.Date date2 = df.parse(dateStr2);
            return date1.after(date2);
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Convert date date to string date
     *
     * @param date
     * @param isFullDateTime:true: full date time, false: date sort
     * @return
     */
    public static String convertDateToString(Date date, Boolean isFullDateTime, String prefix) {
        String strDate;
        if (date == null) {
            return "";
        }
        if (isFullDateTime) {
            strDate = new SimpleDateFormat("dd" + prefix + "MM" + prefix + "yyyy HH:mm:ss").format(date);
        } else {
            strDate = new SimpleDateFormat("dd" + prefix + "MM" + prefix + "yyyy").format(date);
        }
        return strDate;
    }

    /**
     * Them ngay thang cho ten file
     *
     * @param fileName ten file goc
     * @return Ten file moi
     */
    public static String replaceFileName(String fileName) {
        if (!FnCommon.isNullOrEmpty(fileName)) {
            long date = new Date(System.currentTimeMillis()).getTime();
            return fileName + "_" + date;
        }
        return "";
    }

    /**
     * Convert string date to java.sql.date
     *
     * @param strDate Chuoi string date
     * @param format  Dinh dang mong muon
     * @return
     */
    public static Date convertStringToDate(String strDate, String format) {
        if (!FnCommon.isNullOrEmpty(strDate)) {
            SimpleDateFormat sdFormat = new SimpleDateFormat(format);
            sdFormat.setLenient(false);
            try {
                java.util.Date date = sdFormat.parse(strDate);
                return new Date(date.getTime());
            } catch (ParseException e) {
                LOGGER.error("Convert string to date fail", e);
                return null;
            }
        }
        return null;
    }

    public static byte[] zipFiles(Map<String, byte[]> listFile) {
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        try (ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            for (Map.Entry<String, byte[]> file : listFile.entrySet()) {
                ZipEntry zipEntry = new ZipEntry(file.getKey());
                zipOut.putNextEntry(zipEntry);
                zipOut.write(file.getValue());
            }
        } catch (IOException ex) {
            log.info(ex);
        }
        return fos.toByteArray();
    }

    /**
     * Lam tron gia tri kieu double
     *
     * @param value
     * @return
     */
    public static Double round(Double value) {
        DecimalFormat newFormat = new DecimalFormat("#.##");
        return Double.valueOf(newFormat.format(value));
    }

    /**
     * validate do lon cua gia tri
     *
     * @param value
     * @param maxlength
     * @return
     */
    public static boolean validateMaxlengthDouble(Double value, int maxlength) {
        return Math.log10(value) > maxlength;
    }

    /**
     * Validate so dong trong file excel
     *
     * @param row
     * @return
     */
    public static boolean validateMaxRow(int row) {
        return (row > 1000);
    }

    /**
     * Kiem tra do dai chuoi
     *
     * @param string
     * @param maxlength
     * @return
     */
    public static boolean validateMaxlengthString(String string, int maxlength) {
        return (string.length() > maxlength);
    }

    /***
     * Format plate BOO1
     */

    public static String formatPlateBOO1(String plate) {
        if (!isNullOrEmpty(plate)) {
            Pattern p = Pattern.compile("(?<=\\D)[\\d]\\w+");
            Matcher m = p.matcher(plate);
            String plateCheck = "";

            if (m.find()) {
                plateCheck = m.group();
            }
            if (plateCheck.length() == 4 && StringUtils.isNumeric(plate.substring(plate.length() - 1))) {
                return plate;
            } else if (plateCheck.length() >= 5) {
                if (plate.endsWith("T") || plate.endsWith("X") || plate.endsWith("V")) {
                    return plate.substring(0, plate.length() - 1);
                }
            }
        }
        return plate;
    }

    /***
     * Format plate BOO1
     * T Trang
     * X xanh
     * V vang
     */

    public static String getPlateTypeBOO1(String plate) {
        if (!isNullOrEmpty(plate)) {
            Pattern p = Pattern.compile("(?<=\\D)[\\d]\\w+");
            Matcher m = p.matcher(plate);
            String plateCheck = "";
            if (m.find()) {
                plateCheck = m.group();
            }
            if (plateCheck.length() == 4 && StringUtils.isNumeric(plate.substring(plate.length() - 1))) {
                return "1";
            } else if (plateCheck.length() > 5) {
                String plateType = plate.substring(plate.length() - 1);
                switch (plateType) {
                    case "T":
                        return "1";
                    case "X":
                        return "2";
                    case "V":
                        return "6";
                }
            }
        }
        return "1";
    }

    /***
     * Validate start-date end-date mua ve thang quy BOO1
     */
    public static boolean validateDateChargeTicket(long methodCharge, String ticketType, String startDate, String
            endDate, String formatDate) {
        Date start = FnCommon.convertStringToDate(startDate, formatDate);
        Date end = FnCommon.convertStringToDate(endDate, formatDate);
        if (start == null || end == null) {
            return false;
        }

        if (methodCharge == 1L) {
            // tinh thuong
            if (getDayOfMonth(start) != 1) {
                // khong phai ngay dau thang
                return false;
            } else {
                int diffMonth = monthsBetween(start, end);
                if (diffMonth == 0 && "T".equals(ticketType)) {
                    return getDayOfMonth(end) == getMaxDayOfMonth(end);
                } else if ("Q".equals(ticketType)) {
                    return checkQuarter(start, end, startDate, endDate);
                }
            }
        } else if (methodCharge == 2L) {
            // tinh block
            long diff = end.getTime() - start.getTime();
            long numberOfDay = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            return (numberOfDay == 29 && "T".equals(ticketType)) || (numberOfDay == 89 && "Q".equals(ticketType));
        }
        return false;
    }


    public static boolean checkQuarter(Date start, Date end, String startDate, String endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(end);

        if (cal.get(Calendar.YEAR) == cal1.get(Calendar.YEAR) && end.getTime() > System.currentTimeMillis()) {
            String s1 = startDate.substring(4);
            String s2 = endDate.substring(4);
            return "0101".equals(s1) && "0331".equals(s2) || "0401".equals(s1) && "0630".equals(s2) || "0701".equals(s1) && "0930".equals(s2) || "1001".equals(s1) && "1231".equals(s2);
        }
        return false;
    }

    /***
     * Get number of day by date
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /***
     * Get max of day by month
     * @param date
     * @return
     */
    public static int getMaxDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /***
     * diff month between date
     * @param s1
     * @param s2
     * @return
     */
    private static int monthsBetween(Date s1, Date s2) {
        Calendar d1 = Calendar.getInstance();
        d1.setTime(s1);
        Calendar d2 = Calendar.getInstance();
        d2.setTime(s2);
        return (d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR)) * 12 + d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH);
    }

    /***
     * Convert ticketType BOO1 to BOO2
     * @param ticketType
     * @return
     */
    public static String convertTicketType(String ticketType) {
        switch (ticketType) {
            case "L":
                return "1";
            case "T":
                return "4";
            case "Q":
                return "5";
            case "N":
                return "6";
            default:
                return "";
        }
    }

    /***
     * Convert ticketType BOO1 to BOO2
     * @param servicePlanTypeId
     * @return
     */
    public static String convertToTicketType(String servicePlanTypeId) {
        switch (servicePlanTypeId) {
            case "1":
                return "L";
            case "4":
                return "T";
            case "5":
                return "Q";
            case "6":
                return "N";
            default:
                return "";
        }
    }

    public static String convertStationType(Long stationType) {
        if (stationType == 1) {
            return "O";
        }
        if (stationType == 0) {
            return "C";
        }
        return "";
    }

    /***
     * Validate end of plateNumber
     */

    public static boolean validatePlateContainsTVX(String plate) {
        Pattern p = Pattern.compile("(?<=\\D)[\\d]\\w+");
        Matcher m = p.matcher(plate);
        String plateCheck = "";
        boolean result = false;
        if (m.find()) {
            plateCheck = m.group();
        }
        if (!FnCommon.isNullOrEmpty(plateCheck)) {
            boolean isSuffix = (plate.endsWith("T") || plate.endsWith("X") || plate.endsWith("V"));
            if (plateCheck.length() == 4 && StringUtils.isNumeric(plate.substring(plate.length() - 1))) {
                result = true;
            } else if (plateCheck.length() == 5 && !StringUtils.isNumeric(plate.substring(plate.length() - 1))) {
                return true;
            } else if (plateCheck.length() == 5 && StringUtils.isNumeric(plate.substring(plate.length() - 1))) {
                /***
                 * Đối với biển số dãy số có 5 chữ số nếu là biển trắng thêm chữ T, biển Xanh thêm (X) vào cuối
                 * trừ các biển có 02 chữ cái sau mã tỉnh ví dụ: LD, NN, LB, KT, NG
                 */
                p = Pattern.compile("(?<=\\d)\\D{2}");
                m = p.matcher(plate);
                if (m.find()) {
                    plateCheck = m.group();
                }
                if (plateCheck.length() == 2) {
                    return !isSuffix;
                } else {
                    return false;
                }

            } else if (plateCheck.length() > 5) {
                /***
                 * Đối với biển số dãy số có 5 chữ số nếu là biển trắng thêm chữ T, biển Xanh thêm (X) vào cuối
                 * trừ các biển có 02 chữ cái sau mã tỉnh ví dụ: LD, NN, LB, KT, NG
                 */
                p = Pattern.compile("(?<=\\d)\\D{2}");
                m = p.matcher(plate);
                if (m.find()) {
                    plateCheck = m.group();
                }
                if (plateCheck.length() == 2) {
                    return false;
                } else {
                    return isSuffix;
                }
            }
        }
        return result;
    }

    /***
     * Check bien so co phai 5so 2chu khong
     * @param plate
     * @return
     */
    public static boolean checkPlate5Number2Char(String plate) {
        Pattern p = Pattern.compile("(?<=\\d)\\D{2}");
        Matcher m = p.matcher(plate);
        String plateCheck = "";
        if (m.find()) {
            plateCheck = m.group();
        }

        return plateCheck.length() == 2;
    }

    /***
     * Validate end of plateNumber
     */

    public static String getPlateNumberBoo1(String plate, String plateType) {
        Pattern p = Pattern.compile("(?<=\\D)[\\d]\\w+");
        Matcher m = p.matcher(plate);
        String plateCheck = "";
        if (m.find()) {
            plateCheck = m.group();
        }
        if (!FnCommon.isNullOrEmpty(plateCheck)) {
            if (plateCheck.length() == 4 && StringUtils.isNumeric(plate.substring(plate.length() - 1))) {
                return plate;
            } else if (plateCheck.length() == 5 && !StringUtils.isNumeric(plate.substring(plate.length() - 1))) {
                // neu 4 so va so cuoi la chu thi cat di
                return plate.substring(0, plate.length() - 1);
            } else if (plateCheck.length() >= 5 && StringUtils.isNumeric(plate.substring(plate.length() - 1))) {
                /***
                 * Đối với biển số dãy số có 5 chữ số nếu là biển trắng thêm chữ T, biển Xanh thêm (X) vào cuối
                 * trừ các biển có 02 chữ cái sau mã tỉnh ví dụ: LD, NN, LB, KT, NG
                 */
                p = Pattern.compile("(?<=\\d)\\D{2}");
                m = p.matcher(plate);
                if (m.find()) {
                    plateCheck = m.group();
                }
                if (plateCheck.length() == 2) {
                    return plate;
                } else {
                    return plate + plateType;
                }

            }
        }
        return plate + plateType;
    }

    public static void responseFile(HttpServletResponse response, String fileName) throws IOException {
        File fileToDownload = new File(System.getProperty("user.dir") + File.separator + fileName);
        try (InputStream inputStream = new FileInputStream(fileToDownload)) {
            response.setContentType("application/force-download");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        }
    }

    public static void responseFile(HttpServletResponse response, byte[] file, String fileName) throws IOException {
        response.setContentType("application/force-download");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.getOutputStream().write(file);
        response.flushBuffer();
    }


    public static String getPlateTypeCodeFromPlateNumber(String plateNumber) {
        if (!isNullOrEmpty(plateNumber)) {
            if (plateNumber.endsWith("T")) {
                return "1";
            } else if (plateNumber.endsWith("X")) {
                return "2";
            } else if (plateNumber.endsWith("V")) {
                return "6";
            }
        }
        return "1";
    }

    public static String mappingPlateTypeBOO2ToBOO1(String plateTypeCode) {
        if (!isNullOrEmpty(plateTypeCode)) {
            switch (plateTypeCode) {
                case "1":
                    return "T";
                case "2":
                    return "X";
                case "6":
                    return "V";
            }
        }
        return "";
    }

    public static String mappingPlateTypeBOO1ToBOO2(String plateType) {
        if (!isNullOrEmpty(plateType)) {
            switch (plateType) {
                case "T":
                    return "1";
                case "X":
                    return "2";
                case "V":
                    return "6";
            }
        }
        return null;
    }

    /**
     * @param strDate
     * @param hours
     * @param minutes
     * @param seconds
     * @return
     * @throws ParseException
     */
    public static java.util.Date setTimeOfDate(String strDate, int hours, int minutes, int seconds) {
        if (!FnCommon.isNullOrEmpty(strDate)) {
            try {
                java.util.Date timeOfDate = new SimpleDateFormat(Constants.COMMON_DATE_FORMAT).parse(strDate);
                timeOfDate.setHours(hours);
                timeOfDate.setMinutes(minutes);
                timeOfDate.setSeconds(seconds);
                return timeOfDate;
            } catch (ParseException e) {
                LOGGER.error("Convert string to date fail", e);
                return null;
            }
        }
        return null;

    }

    public static java.util.Date setTimeOfDate(java.util.Date date, int hours, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static String urlDecodeString(String input) {
        try {
            return URLDecoder.decode(input, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error("urlDecode ", e);
        }
        return input;
    }

    public static String urlEncodeString(String input) {
        try {
            return URLEncoder.encode(input, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error("urlDecode ", e);
        }
        return input;
    }

    public static String urlDecodeCheckSumString(String input) {
        try {
            return URLDecoder.decode(input, StandardCharsets.UTF_8.name()).replaceAll(" ", "+");
        } catch (UnsupportedEncodingException e) {
            log.error("urlDecode ", e);
        }
        return input;
    }

    /***
     * validate stringDate by format dd/MM/yyyy HH:mm:ss
     * @param strDate
     * @return
     */
    public static boolean validateCommonFormatDate(String strDate) {
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(strDate).matches();
    }

    /**
     * Lay khoang cach giua 2 ngay
     *
     * @param sysDate
     * @param effDate
     * @return
     */
    public static long diffDays(Long sysDate, Long effDate) {
        Long i = Math.abs(sysDate - effDate);
        return TimeUnit.DAYS.convert(i, TimeUnit.MILLISECONDS);
    }

    /**
     * Cong them so ngay config
     *
     * @param date
     * @param days
     * @return
     */
    public static java.util.Date addDays(java.util.Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new java.util.Date(c.getTimeInMillis());
    }

    public static java.util.Date round(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static java.util.Date addYears(java.util.Date date, int years) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, years);
        return new java.util.Date(c.getTimeInMillis());
    }

    public static java.sql.Date addYearsSql(java.sql.Date date, int years) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, years);
        return new Date(c.getTimeInMillis());
    }


    /**
     * Tra ve file ket qua
     *
     * @param data du lieu noi dung file dang byte[]
     */
    public static org.springframework.http.ResponseEntity<?> returnFileExcel(byte[] data, String fileName) {
        return returnFileExcel(data, fileName, null, null);
    }

    public static org.springframework.http.ResponseEntity<?> returnFileExcel(byte[] data, String fileName, Long dataFailed, Long dataSuccess) {
        if (fileName.endsWith(".xlsx")) {
            fileName = fileName.substring(0, fileName.length() - 5);
            fileName = FnCommon.replaceFileName(fileName) + "-result.xlsx";
        }
        if (fileName.endsWith(".xls")) {
            fileName = fileName.substring(0, fileName.length() - 4);
            fileName = FnCommon.replaceFileName(fileName) + "-result.xls";
        }
        ByteArrayResource resource = new ByteArrayResource(data);
        HttpHeaders header = new HttpHeaders();
        if (dataFailed != null && dataSuccess != null) {
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + "; Failed-lines=" + dataFailed.toString() + "; Success-lines=" + dataSuccess.toString());
        } else {
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        }
        header.add("Access-Control-Expose-Headers", "Content-Disposition");
        return org.springframework.http.ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    public static boolean rowIsEmpty(Row row) {
        if (row == null || row.getLastCellNum() <= 0) {
            return true;
        }
        for (int i = row.getFirstCellNum(); i <= row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }


    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString().toUpperCase();
    }

    public static void appendSQL(String param, HashMap<String, Object> hmapParams, List<Object> lstValue, StringBuilder sql) {
        for (int i = 0; i < lstValue.size(); i++) {
            if (i == 0 && i == lstValue.size() - 1) {
                sql.append(" AND ( " + param + i + " ) \n");
            } else if (i == 0) {
                sql.append(" AND ( " + param + i + "  \n");
            } else if (i == lstValue.size() - 1) {
                sql.append(" OR " + param + i + " ) \n");
            } else {
                sql.append(" OR " + param + i + " \n");
            }
            hmapParams.put(param.substring(param.indexOf(":") + 1) + i, lstValue.get(i));
        }
    }

    public static boolean checkBriefcaseValid(String fileName, byte[] file, Integer maxFileSizeMb) {
        if (Objects.isNull(maxFileSizeMb)) {
            maxFileSizeMb = 15;
        }
        Objects.requireNonNull(file);
        long fileSizeMb = file.length / (1024 * 1024);
        return checkFileExtensionValid(fileName, ".JPG", ".PNG", ".TIFF", ".BMP", ".PDF", ".JPEG", ".WEBP", ".HEIC") && fileSizeMb <= maxFileSizeMb;
    }

    public static boolean checkAttachFileValid(String fileName, byte[] file, Integer maxFileSizeMb) {
        if (Objects.isNull(maxFileSizeMb)) {
            maxFileSizeMb = 15;
        }
        Objects.requireNonNull(file);
        long fileSizeMb = file.length / (1024 * 1024);
        return checkFileExtensionValid(fileName, ".JPG", ".PNG", ".TIFF", ".BMP", ".PDF", ".JPEG", ".WEBP", ".HEIC", ".DOCX", ".DOC", ".XLS", ".XLSX") && fileSizeMb <= maxFileSizeMb;
    }

    public static String getErrorField(String field) {
        String error = Constants.PROPERTY_MAPPING.get(field);
        if (error == null) {
            return field;
        } else {
            return error;
        }
    }

    public static String getPhoneNumber(String phoneNumber) {
        phoneNumber = StringUtils.trim(phoneNumber);
        if (StringUtils.startsWith(phoneNumber, "84")) {
            return "0" + phoneNumber.substring(2);
        } else {
            return phoneNumber;
        }
    }

    /**
     * Convert date date to string date
     *
     * @param date
     * @param isFullDateTime:true: full date time, false: date sort
     * @return
     */
    public static String convertDateToString(java.util.Date date, Boolean isFullDateTime) {
        String strDate;
        if (date == null) {
            return "";
        }
        if (isFullDateTime) {
            strDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
        } else {
            strDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
        }
        return strDate;
    }

    public static boolean validateFileName(String fileName) {
        return fileName.endsWith(".xls") || fileName.endsWith(".xlsx");

    }

    /**
     * Validate tieu de
     *
     * @param sheet
     */
    public static boolean validateHeaderCell(Sheet sheet, int indexRow, int cellNumber, String headerString) {
        boolean isAccepted = false;
        if (sheet != null) {
            Row row = sheet.getRow(indexRow);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < cellNumber; i++) {
                builder.append(row.getCell(i).getStringCellValue().trim());
            }
            if (headerString.equals(builder.toString())) {
                isAccepted = true;
            }
        }
        return isAccepted;
    }

    /**
     * Lay gia tri id cua danh muc
     *
     * @param source
     * @return
     */
    public static String getIdFromString(String source) {
        String result = "";
        if (!FnCommon.isNullOrEmpty(source)) {
            String[] arrResult = source.split("-");
            if (arrResult.length > 1) {
                result = arrResult[arrResult.length - 1];
            }
        }
        return result;
    }

    /**
     * Tao va set style cho column cua excel
     *
     * @param wb
     */
    public static CellStyle createAndSetStyleCell(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return cellStyle;
    }

    /**
     * Set style for header
     */

    public static void setFontHeaderCell(Workbook wb, CellStyle cellStyle) {
        Font fontHeader = wb.createFont();
        fontHeader.setBold(true);
        fontHeader.setCharSet(FontCharset.VIETNAMESE.getNativeId());
        cellStyle.setFont(fontHeader);
    }

    public static Object getMemberForGroup(String urlGetMember, String urlLogin, String clientId, String userName, String userPass, String secret) {

        String adminToken = getAdminToken(urlLogin, clientId, userName, userPass, secret);
        LOGNEW.info("GET USER KEYCLOCAK TOKEN ADMIN SUCCESS");
        Map<String, String> params = new HashMap<>();
        params.put("briefRepresentation", "true");
        return FnCommon.doGetRequest(urlGetMember, params, adminToken);
    }

    private static String accessBody(String clientId, String userName, String userPass, String secret) {
        try {
            return "grant_type=password&" +
                    "client_id=" + clientId +
                    "&client_secret=" + secret + "&" +
                    "username=" + userName + "&" +
                    "password=" + URLEncoder.encode(userPass, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Has Error");
            return "";
        }
    }

    private static String getAdminToken(String urlLogin, String clientId, String userName, String userPass, String secret) {

        RequestBody body = RequestBody.create(Constants.FORM_URL_ENCODED, accessBody(clientId, userName, userPass, secret));

        OkHttpClient client = new OkHttpClient();
        try {
            FnCommon.setOkHtppClient(client);
            HttpUrl.Builder httpBuilder = HttpUrl.parse(urlLogin).newBuilder();
            Request request = new Request.Builder()
                    .header("Accept", "*/*")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .url(httpBuilder.build())
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                String strRes;
                try (ResponseBody responseBody = response.body()) {
                    strRes = responseBody.string();
                }
                ResKeycloakDTO res = new Gson().fromJson(strRes, ResKeycloakDTO.class);
                return res.getAccess_token();
            }
        } catch (Exception e) {
            System.out.println("Method getAdminToken error");
        }

        return null;
    }

    public static <T extends Serializable> List<T> clone(List<T> original) {
        List<T> copy = new ArrayList<>();
        for (T item : original) {
            copy.add(SerializationUtils.clone(item));
        }
        return copy;
    }

    public enum LOG_CAU_HINH_DANH_MUC {
        TICKET_PROCESSING_TIME_CONFIG(4008L),
        TICKET_TYPE_GROUP(4009L),
        TICKET_TYPE_GENRE(4010L),
        TICKET_TYPE(4011L),
        TICKET_PRIORITIES(4012L),
        TICKET_EXPIRE_ERROR(4013L),
        TICKET_EXPIRE_CAUSE(4014L),
        TICKET_SITE(4015L),
        MAPPING_EXPIRE_ERROR(4016L),
        STATISTIC_TYPE(4017L),
        NOTIFITY_CONFIG(4018L);
        public final Long value;

        LOG_CAU_HINH_DANH_MUC(Long value) {
            this.value = value;
        }
    }

}
