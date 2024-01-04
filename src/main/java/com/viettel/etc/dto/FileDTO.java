package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDTO {

    String filePath;

    @NotEmpty
    String fileName;

    @NotEmpty
    String base64Data;

    Long attachmentId;
}
