package com.s2p.constants;

import lombok.Getter;

@Getter
public enum EOperationStatus
{
    RESULT_UNDEFINED("UNDEFINED"),
    RESULT_FAILURE("FAILURE"),
    RESULT_SUCCESS("SUCCESS");

    private final String sData;
    private EOperationStatus(String sData)
    {
        this.sData = sData;
    }

}
