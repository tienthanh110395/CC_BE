package com.viettel.etc.services;

import com.viettel.etc.dto.CategoryDTO;
import org.springframework.security.core.Authentication;

public interface CategoriesService {
    Boolean getConfigOtp(String code, Authentication authentication);

    String getValueByCode(CategoryDTO.Categories categories, String code);

    CategoryDTO.Categories findCategoriesListIdByCode(String token, String tableName, String code);
}
