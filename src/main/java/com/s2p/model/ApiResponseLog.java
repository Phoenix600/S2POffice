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
public class ApiResponseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apiResponseId;

    private int status;

    @Column(columnDefinition = "TEXT")
    private String body;

    @CurrentTimestamp
    private LocalDateTime timestamp;

    private Long durationMs; // how long it took

    private String host;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,targetEntity = APIRequestLog.class)
    @JoinColumn(name = "api_response_id", referencedColumnName = "apiResponseId")
    private APIRequestLog requestLog;
}