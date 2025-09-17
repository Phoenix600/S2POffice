package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(
        name = "ApiResponseLog",
        description = "Entity that logs API response details including status, body, duration, and linked request."
)
public class ApiResponseLog<A> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Unique identifier for the API response log entry",
            example = "501"
    )
    private Long apiResponseId;

    @Schema(
            description = "HTTP status code returned in the response",
            example = "200"
    )
    private int status;

    @Column(columnDefinition = "TEXT")
    @Schema(
            description = "Response body payload (raw text or JSON)",
            example = "{ \"message\": \"Student created successfully\" }"
    )
    private String body;

    @CurrentTimestamp
    @Schema(
            description = "Timestamp when the response was logged",
            example = "2025-09-19T14:35:22"
    )
    private LocalDateTime timestamp;

    @Schema(
            description = "Time taken to process the request in milliseconds",
            example = "120"
    )
    private Long durationMs;

    @Schema(
            description = "Host/server that processed the request",
            example = "student-api-server-1"
    )
    private String host;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = ApiRequestLog.class)
    @JoinColumn(name = "api_response_id", referencedColumnName = "apiResponseId")
    @Schema(
            description = "Associated API request log that generated this response"
    )
    private ApiRequestLog requestLog;
}
