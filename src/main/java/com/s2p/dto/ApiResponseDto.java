package com.s2p.dto;

import com.s2p.constants.EOperationStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponseDto<T>
{
    private String message;
    private EOperationStatus status;
    private T data;
}
