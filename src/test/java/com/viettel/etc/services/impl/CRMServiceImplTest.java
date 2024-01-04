package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ContractDetailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;

class CRMServiceImplTest {

    private CRMServiceImpl crmServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        crmServiceImplUnderTest = new CRMServiceImpl();
        crmServiceImplUnderTest.wsContractDetails = "wsContractDetails";
    }

    @Test
    void testGetContractDetails() {
        // Setup
        final Authentication authentication = null;
        final ContractDetailDTO expectedResult = new ContractDetailDTO();
        expectedResult.setCusTypeId(0L);
        expectedResult.setUserName("userName");
        expectedResult.setUserId("userId");
        expectedResult.setBirth("birth");
        expectedResult.setGender("gender");
        expectedResult.setAddress("address");
        expectedResult.setIdentifier("identifier");
        expectedResult.setRepIdentifier("repIdentifier");
        expectedResult.setRepIdentifierType(0L);
        expectedResult.setDateOfIssue("dateOfIssue");

        // Run the test
        final ContractDetailDTO result = crmServiceImplUnderTest.getContractDetails(authentication);

        // Verify the results
    }
}
