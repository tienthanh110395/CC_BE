package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MapErrorCauseUpdateDTO {

    Long mapErrorCauseId;
    Long ticketGenreId;
    String ticketGenre;
    Long ticketGroupId;
    String ticketGroup;
    Long ticketErrorCauseId;
    Long ticketErrorCauseId2;
    Long ticketErrorCauseId3;
    String ticketErrorName1;
    String ticketErrorName2;
    String ticketErrorName3;
    Long levelTec2;
    Long levelTec3;
    Long parentIdLv2;
    Long parentIdLv3;

}
