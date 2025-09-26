package com.s2p.services;

import com.s2p.dto.EvaluationRequestDTO;
import com.s2p.dto.EvaluationResultDTO;

public interface EvaluationService {
    EvaluationResultDTO evaluate(EvaluationRequestDTO request);
}
