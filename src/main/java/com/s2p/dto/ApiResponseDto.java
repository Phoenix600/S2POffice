package com.s2p.dto;

import com.s2p.constants.EOperationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "Generic API Response Wrapper")
public class ApiResponseDto<T> {

    @Schema(description = "Response message providing additional details", example = "Operation completed successfully")
    private String message;

    @Schema(description = "Status of the operation", example = "SUCCESS")
    private EOperationStatus status;

    @Schema(description = "Response data payload")
    private T data;
}
