package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContractDetailDTOTest {

    private ContractDetailDTO contractDetailDTOUnderTest;

    @BeforeEach
    void setUp() {
        contractDetailDTOUnderTest = new ContractDetailDTO();
        contractDetailDTOUnderTest.cusTypeId = 0L;
        contractDetailDTOUnderTest.userName = "userName";
        contractDetailDTOUnderTest.userId = "userId";
        contractDetailDTOUnderTest.birth = "birth";
        contractDetailDTOUnderTest.gender = "gender";
        contractDetailDTOUnderTest.address = "address";
        contractDetailDTOUnderTest.identifier = "identifier";
        contractDetailDTOUnderTest.repIdentifier = "repIdentifier";
        contractDetailDTOUnderTest.repIdentifierType = 0L;
        contractDetailDTOUnderTest.dateOfIssue = "dateOfIssue";
        contractDetailDTOUnderTest.placeOfIssue = "placeOfIssue";
        contractDetailDTOUnderTest.contractNo = "contractNo";
        contractDetailDTOUnderTest.signDate = "signDate";
        contractDetailDTOUnderTest.effDate = "effDate";
        contractDetailDTOUnderTest.expDate = "expDate";
        contractDetailDTOUnderTest.phone = "phone";
        contractDetailDTOUnderTest.email = "email";
        contractDetailDTOUnderTest.customerId = "customerId";
        contractDetailDTOUnderTest.contractId = "contractId";
        contractDetailDTOUnderTest.documentType = 0L;
        contractDetailDTOUnderTest.isAdditional = 0L;
        contractDetailDTOUnderTest.isAlertMoney = 0L;
        contractDetailDTOUnderTest.alertMoney = 0L;
        contractDetailDTOUnderTest.smsNotification = "smsNotification";
        contractDetailDTOUnderTest.accountAlias = "accountAlias";
        contractDetailDTOUnderTest.billCycle = "billCycle";
        contractDetailDTOUnderTest.billCycleMergeType = "billCycleMergeType";
        contractDetailDTOUnderTest.noticeAreaName = "noticeAreaName";
        contractDetailDTOUnderTest.noticeStreet = "noticeStreet";
        contractDetailDTOUnderTest.noticeAreaCode = "noticeAreaCode";
        contractDetailDTOUnderTest.noticeEmail = "noticeEmail";
        contractDetailDTOUnderTest.noticePhoneNumber = "noticePhoneNumber";
    }

    @Test
    void testEquals() {
        final boolean result = contractDetailDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = contractDetailDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = contractDetailDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = contractDetailDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
