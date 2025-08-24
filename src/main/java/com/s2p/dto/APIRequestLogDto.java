package com.s2p.dto;

import com.s2p.model.ApiResponseLog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class APIRequestLogDto
{
    private Long apiRequestId;

    private String method;

    private String uri;

    @Column(columnDefinition = "TEXT")
    private String headers;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    private String callerName;

    @OneToOne
    @JoinColumn(
            name = "api_response_id", referencedColumnName = "apiResponseId",
            nullable = false
    )
    private ApiResponseLog responseLog;
}
