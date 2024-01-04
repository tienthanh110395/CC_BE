package com.viettel.etc.services.impl;

import com.viettel.etc.dto.CategoryDTO;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CategoriesServiceImplTest {

    private CategoriesServiceImpl categoriesServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        categoriesServiceImplUnderTest = new CategoriesServiceImpl();
    }

    @Test
    void testGetValueByCode() {
        // Setup
        final CategoryDTO.Categories categories = new CategoryDTO.Categories(Arrays.asList(new CategoryDTO.Category("name", "code", 0L, "description")));

        // Run the test
        final String result = categoriesServiceImplUnderTest.getValueByCode(categories, "code");

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindCategoriesListIdByCode() {
        // Setup
        final CategoryDTO.Categories expectedResult = new CategoryDTO.Categories(Arrays.asList(new CategoryDTO.Category("name", "code", 0L, "description")));

        // Run the test
        final CategoryDTO.Categories result = categoriesServiceImplUnderTest.findCategoriesListIdByCode("token", "tableName", "code");

        // Verify the results
    }

    @Test
    void testGetConfigOtp() {
        // Setup
        final Authentication authentication = null;
        CategoryDTO.Categories categories = new CategoryDTO.Categories();
        categories.setListData(Collections.singletonList(new CategoryDTO.Category()));

        new MockUp<CategoriesServiceImpl>(){
            @mockit.Mock
            public CategoryDTO.Categories findCategoriesListIdByCode(String token, String tableName, String code) {
                return categories;
            }
        };
        // Run the test
        final Boolean result = categoriesServiceImplUnderTest.getConfigOtp("code", authentication);

        // Verify the results
        assertThat(result).isFalse();
    }
}
