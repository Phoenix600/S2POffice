package com.s2p.services.impl;

import com.s2p.dto.CourseFeeInstallmentTransactionsDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.CourseFeeInstallmentTransactions;
import com.s2p.repository.CourseFeeInstallmentTransactionsRepository;
import com.s2p.services.ICourseFeeInstallmentTransactions;
import com.s2p.util.CourseFeeInstallmentTransactionsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseFeeInstallmentTransactionsService implements ICourseFeeInstallmentTransactions
{
    @Autowired
    CourseFeeInstallmentTransactionsRepository courseFeeInstallmentTransactionsRepository;

    @Autowired
    CourseFeeInstallmentTransactionsUtility courseFeeInstallmentTransactionsUtility;


    @Override
    public CourseFeeInstallmentTransactionsDto getCourseFeeInstallmentTransactionById(UUID courseFeeInstallmentTransactionsId) {
        Optional<CourseFeeInstallmentTransactions> optionalTransaction = courseFeeInstallmentTransactionsRepository.findById(courseFeeInstallmentTransactionsId);

        if (optionalTransaction.isEmpty()) {
            throw new ResourceNotFoundException("CourseFeeInstallmentTransaction", "id", courseFeeInstallmentTransactionsId.toString());
        }

        return courseFeeInstallmentTransactionsUtility.toCourseFeeInstallmentTransactionsDto(optionalTransaction.get());
    }

    @Override
    public Set<CourseFeeInstallmentTransactionsDto> getAllCourseFeeInstallmentTransactions() {
        List<CourseFeeInstallmentTransactions> transactions = courseFeeInstallmentTransactionsRepository.findAll();
        Set<CourseFeeInstallmentTransactionsDto> result = new HashSet<>();

        for (CourseFeeInstallmentTransactions courseFeeInstallmentTransactions : transactions) {
            result.add(courseFeeInstallmentTransactionsUtility.toCourseFeeInstallmentTransactionsDto(courseFeeInstallmentTransactions));
        }

        return result;
    }

    @Override
    public CourseFeeInstallmentTransactionsDto deleteCourseFeeInstallmentTransactionById(UUID courseFeeInstallmentTransactionsId) {
        Optional<CourseFeeInstallmentTransactions> optionalTransaction = courseFeeInstallmentTransactionsRepository.findById(courseFeeInstallmentTransactionsId);

        if (optionalTransaction.isEmpty()) {
            throw new ResourceNotFoundException("CourseFeeInstallmentTransaction", "id", courseFeeInstallmentTransactionsId.toString());
        }

        CourseFeeInstallmentTransactions courseFeeInstallmentTransactions = optionalTransaction.get();
        courseFeeInstallmentTransactionsRepository.delete(courseFeeInstallmentTransactions);

        return courseFeeInstallmentTransactionsUtility.toCourseFeeInstallmentTransactionsDto(courseFeeInstallmentTransactions);
    }
}
