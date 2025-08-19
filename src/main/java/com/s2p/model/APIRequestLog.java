package com.s2p.model;

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
public class APIRequestLog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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