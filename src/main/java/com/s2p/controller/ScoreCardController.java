package com.s2p.controller;

import com.s2p.dto.EvaluationRequestDTO;
import com.s2p.dto.ScoreCardDTO;
import com.s2p.services.IScoreCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scorecard")
@RequiredArgsConstructor
public class ScoreCardController {

    private final IScoreCardService scoreCardService;

    @PostMapping("/generate")
    public ResponseEntity<ScoreCardDTO> generateScoreCard(@RequestBody EvaluationRequestDTO request) {
        ScoreCardDTO scoreCard = scoreCardService.generateScoreCard(request);
        return ResponseEntity.ok(scoreCard);
    }
}
