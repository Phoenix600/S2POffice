package com.s2p.services;

import com.s2p.dto.EvaluationRequestDTO;
import com.s2p.dto.ScoreCardDTO;

public interface IScoreCardService {
    ScoreCardDTO generateScoreCard(EvaluationRequestDTO request);
}
