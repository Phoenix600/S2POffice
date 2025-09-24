package com.s2p.dto;

import com.s2p.model.ApiRequestLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing an API response log")
public class APIResponseLogDto {

    @Schema(description = "Unique identifier of the API response", example = "201")
    private Long apiResponseId;

    @Schema(description = "HTTP status code of the response", example = "200")
    private int status;

    @Schema(description = "Response body in string format", example = "{\"success\": true, \"message\": \"Student created\"}")
    private String body;

    @CurrentTimestamp
    @Schema(description = "Timestamp when the response was logged", example = "2025-09-22T12:45:30")
    private LocalDateTime timestamp;

    @Schema(description = "Time taken to process the request in milliseconds", example = "152")
    private Long durationMs;

    @Schema(description = "Host that processed the request", example = "server-1.internal.local")
    private String host;

    @Schema(description = "Associated request log that triggered this response")
    private APIRequestLogDto requestLogDto;
}
