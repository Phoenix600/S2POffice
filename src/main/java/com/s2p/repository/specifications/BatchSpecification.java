package com.s2p.repository.specifications;

import com.s2p.model.Batch;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;

public class BatchSpecification {

    public static Specification<Batch> hasBatchName(String batchName) {
        return (root, query, cb) ->
                batchName == null ? null : cb.like(cb.lower(root.get("batchName")), "%" + batchName.toLowerCase() + "%");
    }

    public static Specification<Batch> hasStartTime(LocalTime startTime) {
        return (root, query, cb) ->
                startTime == null ? null : cb.equal(root.get("startTime"), startTime);
    }

    public static Specification<Batch> hasEndTime(LocalTime endTime) {
        return (root, query, cb) ->
                endTime == null ? null : cb.equal(root.get("endTime"), endTime);
    }
}
