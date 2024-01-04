package com.viettel.etc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketPrioritySlaDTO implements Serializable {
    private static final long serialVersionUID = -6486302394927965722L;

    private Long priorityId;
    private String priorityCode;
    private String priorityName;
    private Long timeAll;
    private Long timeL1;
    private Long timeL2;
    private Long timeRe;
}
