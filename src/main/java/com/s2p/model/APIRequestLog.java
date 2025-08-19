package com.s2p.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
public class APIRequestLog{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String method;

    private String uri;

    @Column(columnDefinition = "TEXT")
    private String headers;

    @Column(columnDefinition = "TEXT")
    private String body;

    private int status; // response status code

    @Column(columnDefinition = "TEXT")
    private String responseBody; // optional, if you capture responses too

    @CurrentTimestamp
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String callerName;
}