package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class NotifyConfigEntityTest {

    private NotifyConfigEntity notifyConfigEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        notifyConfigEntityUnderTest = new NotifyConfigEntity();
        notifyConfigEntityUnderTest.notifyConfigId = 0L;
        notifyConfigEntityUnderTest.name = "name";
        notifyConfigEntityUnderTest.code = "code";
        notifyConfigEntityUnderTest.createUser = "createUser";
        notifyConfigEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        notifyConfigEntityUnderTest.updateUser = "updateUser";
        notifyConfigEntityUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        notifyConfigEntityUnderTest.contentSms = "contentSms";
        notifyConfigEntityUnderTest.contentMail = "contentMail";
        notifyConfigEntityUnderTest.isSms = 0L;
        notifyConfigEntityUnderTest.isMail = 0L;
        notifyConfigEntityUnderTest.type = 0L;
    }

    @Test
    void testEquals() throws Exception {
        assertThat(notifyConfigEntityUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(notifyConfigEntityUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = notifyConfigEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = notifyConfigEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
