package com.s2p.dto;

import com.s2p.model.ApiRequestLog;
import jakarta.persistence.*;
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
public class APIResponseLogDto
{
    private Long apiResponseId;

    private int status;

    @Column(columnDefinition = "TEXT")
    private String body;

    @CurrentTimestamp
    private LocalDateTime timestamp;

    private Long durationMs; // how long it took

    private String host;

    @JoinColumn(name = "api_response_id", referencedColumnName = "apiResponseId")
    private ApiRequestLog requestLog;
}
