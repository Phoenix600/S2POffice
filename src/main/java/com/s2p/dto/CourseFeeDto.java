package com.s2p.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseFeeDto
{
    private UUID courseFeesID;
    private Long transactionId;
    private Double courseFees;
}
