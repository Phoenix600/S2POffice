package com.s2p.controller;

import com.s2p.dto.EvaluationRequestDTO;
import com.s2p.dto.EvaluationResultDTO;
import com.s2p.services.EvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // Endpoint to submit student answers and get evaluation
    //http://localhost:8080/api/evaluation/submit
    @PostMapping("/submit")
    public ResponseEntity<EvaluationResultDTO> submitEvaluation(@RequestBody EvaluationRequestDTO request) {
        // Call the service to evaluate
        EvaluationResultDTO result = evaluationService.evaluate(request);
        return ResponseEntity.ok(result);
    }

    // Optional: endpoint to test if service is running
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Evaluation Service is up and running!");
    }
}
