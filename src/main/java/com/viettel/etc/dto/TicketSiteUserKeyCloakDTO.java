package com.viettel.etc.dto;

import lombok.Data;

@Data
public class TicketSiteUserKeyCloakDTO {
    String id;
    String username;
    String createdTimestamp;
    Boolean enabled;
    Boolean emailVerified;
    String lastName;
}
