package com.viettel.etc.services.impl;

import com.google.gson.Gson;
import com.viettel.etc.dto.CategoryDTO;
import com.viettel.etc.services.CategoriesService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Value("${ws.dmdc.categories}")
    private String categoryUrl;

    @Override
    public Boolean getConfigOtp(String code, Authentication authentication) {
        String otpConfig = getValueByCode(findCategoriesListIdByCode(FnCommon.getStringToken(authentication), Constants.OTP_CATEGORY.TABLE_NAME, code), code);
        return Constants.OTP_CATEGORY.ON.equals(otpConfig);
    }

    @Override
    public String getValueByCode(CategoryDTO.Categories categories, String code) {
        if (!FnCommon.isNullOrEmpty(categories.getListData())) {
            for (CategoryDTO.Category category : categories.getListData()) {
                if (code.equals(category.getCode())) {
                    return category.getName();
                }
            }
        }
        return code;
    }

    @Override
    public CategoryDTO.Categories findCategoriesListIdByCode(String token, String tableName, String code) {
        Map<String, String> params = new HashMap<>();
        params.put("table_name", tableName);
        params.put("code", code);
        CategoryDTO.Categories result = null;
        String strResp = FnCommon.doGetRequest(categoryUrl, params, token);
        CategoryDTO categoryDTO = new Gson().fromJson(strResp, CategoryDTO.class);
        if (categoryDTO != null && categoryDTO.getData() != null) {
            result = categoryDTO.getData();
        }
        return result;
    }
}
