package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MapErrorUpdateDTO {
    List<Long> mapErrorCauseId;
    Long ticketTypeId;
    Long ticketErrorId;
    Long ticketErrorLv2Id;
    Long ticketErrorLv3Id;

}

