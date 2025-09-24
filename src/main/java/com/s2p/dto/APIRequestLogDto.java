package com.s2p.dto;

import com.s2p.model.ApiResponseLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing an API request log")
public class APIRequestLogDto {

    @Schema(description = "Unique identifier of the API request", example = "101")
    private Long apiRequestId;

    @Schema(description = "HTTP method of the request", example = "POST")
    private String method;

    @Schema(description = "Request URI", example = "/api/v1/students")
    private String uri;

    @Schema(description = "Request headers in string format", example = "{Content-Type: application/json}")
    private String headers;

    @Schema(description = "Request body", example = "{\"name\": \"John Doe\", \"age\": 22}")
    private String body;

    @Schema(description = "Name of the caller or service", example = "StudentService")
    private String callerName;

    @Schema(description = "Response details corresponding to the request")
    private APIResponseLogDto responseLogDto;
}
