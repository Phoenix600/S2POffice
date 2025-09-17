package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(
        name = "ApiRequestLog",
        description = "Entity that logs API request details including method, URI, headers, body, caller info, and links to the response."
)
public class ApiRequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Unique identifier for the API request log entry",
            example = "101"
    )
    private Long apiRequestId;

    @Schema(
            description = "HTTP method used in the request",
            example = "POST"
    )
    private String method;

    @Schema(
            description = "Requested URI path",
            example = "/api/v1/students/123"
    )
    private String uri;

    @Column(columnDefinition = "TEXT")
    @Schema(
            description = "HTTP headers of the request in raw text or JSON format",
            example = "{ \"Content-Type\": \"application/json\", \"Authorization\": \"Bearer token\" }"
    )
    private String headers;

    @Column(columnDefinition = "TEXT")
    @Schema(
            description = "Request body payload (if applicable)",
            example = "{ \"name\": \"John Doe\", \"course\": \"Java\" }"
    )
    private String body;

    @Column(nullable = false)
    @Schema(
            description = "Name or system identifier of the caller making the API request",
            example = "StudentPortalService"
    )
    private String callerName;

    @OneToOne
    @JoinColumn(
            name = "api_response_id",
            referencedColumnName = "apiResponseId",
            nullable = false
    )
    @Schema(
            description = "Associated API response log for this request"
    )
    private ApiResponseLog responseLog;
}
