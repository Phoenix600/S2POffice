package com.s2p.services.impl;

import com.s2p.dto.CourseFeeInstallmentTransactionsDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.CourseFeeInstallmentTransactions;
import com.s2p.repository.CourseFeeInstallmentTransactionsRepository;
import com.s2p.repository.StudentUserRepository;
import com.s2p.services.ICourseFeeInstallmentTransactions;
import com.s2p.util.CourseFeeInstallmentTransactionsUtility;
import com.s2p.util.StudentUsersUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseFeeInstallmentTransactionsServiceimpl implements ICourseFeeInstallmentTransactions
{
    @Autowired
    CourseFeeInstallmentTransactionsRepository courseFeeInstallmentTransactionsRepository;

    @Autowired
    CourseFeeInstallmentTransactionsUtility courseFeeInstallmentTransactionsUtility;

    @Autowired
    StudentUserRepository studentUserRepository;

    @Autowired
    StudentUsersUtility studentUsersUtility;


    @Override
    public CourseFeeInstallmentTransactionsDto createTransaction(CourseFeeInstallmentTransactionsDto dto) {
        CourseFeeInstallmentTransactions entity = courseFeeInstallmentTransactionsUtility.toCourseFeeInstallmentTransactionsEntity(dto);
        CourseFeeInstallmentTransactions saved = courseFeeInstallmentTransactionsRepository.save(entity);
        return courseFeeInstallmentTransactionsUtility.toCourseFeeInstallmentTransactionsDto(saved);
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
    public List<CourseFeeInstallmentTransactionsDto> getTransactionsByCourseName(String courseName) {
        List<CourseFeeInstallmentTransactions> transactions = courseFeeInstallmentTransactionsRepository.findAll();
        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions available");
        }

        List<CourseFeeInstallmentTransactionsDto> dtos = new ArrayList<>();
        for (CourseFeeInstallmentTransactions tx : transactions) {
            dtos.add(courseFeeInstallmentTransactionsUtility.toCourseFeeInstallmentTransactionsDto(tx));
        }
        return dtos;
    }

}

