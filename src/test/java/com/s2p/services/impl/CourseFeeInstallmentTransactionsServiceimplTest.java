package com.s2p.services.impl;

import com.s2p.dto.CourseFeeInstallmentTransactionsDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.CourseFeeInstallmentTransactions;
import com.s2p.repository.CourseFeeInstallmentTransactionsRepository;
import com.s2p.repository.StudentUserRepository;
import com.s2p.util.CourseFeeInstallmentTransactionsUtility;
import com.s2p.util.StudentUsersUtility;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(AllureJunit5.class)
@Epic("Finance Module")
@Feature("Course Fee Installment Transactions Service")
@Slf4j
class CourseFeeInstallmentTransactionsServiceimplTest {

    @Mock
    private CourseFeeInstallmentTransactionsRepository courseFeeInstallmentTransactionsRepository;

    @Mock
    private CourseFeeInstallmentTransactionsUtility courseFeeInstallmentTransactionsUtility;

    @Mock
    private StudentUserRepository studentUserRepository;

    @Mock
    private StudentUsersUtility studentUsersUtility;

    @InjectMocks
    private CourseFeeInstallmentTransactionsServiceimpl service;

    private CourseFeeInstallmentTransactions entity;
    private CourseFeeInstallmentTransactionsDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        log.info("CourseFeeInstallmentTransactionsServiceimplTest Setup Called");

        entity = new CourseFeeInstallmentTransactions();
        entity.setCourseFeeInstallmentTransactionsId(UUID.randomUUID());
        entity.setPaidAmount(null);

        dto = new CourseFeeInstallmentTransactionsDto();
        dto.setCourseFeeInstallmentTransactionsId(entity.getCourseFeeInstallmentTransactionsId());
        dto.setPaidAmount(entity.getPaidAmount());
    }

    @Test
    @Story("Create Transaction")
    @Description("Verify that a new Course Fee Installment Transaction is created successfully.")
    @Severity(SeverityLevel.CRITICAL)
    void testCreateTransaction_Success() {
        when(courseFeeInstallmentTransactionsUtility.toCourseFeeInstallmentTransactionsEntity(dto)).thenReturn(entity);
        when(courseFeeInstallmentTransactionsRepository.save(entity)).thenReturn(entity);
        when(courseFeeInstallmentTransactionsUtility.toCourseFeeInstallmentTransactionsDto(entity)).thenReturn(dto);

        CourseFeeInstallmentTransactionsDto result = service.createTransaction(dto);

        assertNotNull(result);
        assertEquals(dto.getPaidAmount(), result.getPaidAmount());
        verify(courseFeeInstallmentTransactionsRepository, times(1)).save(entity);
    }

    @Test
    @Story("Get All Transactions")
    @Description("Verify that all Course Fee Installment Transactions can be fetched successfully.")
    @Severity(SeverityLevel.NORMAL)
    void testGetAllTransactions_Success() {
        when(courseFeeInstallmentTransactionsRepository.findAll()).thenReturn(List.of(entity));
        when(courseFeeInstallmentTransactionsUtility.toCourseFeeInstallmentTransactionsDto(entity)).thenReturn(dto);

        Set<CourseFeeInstallmentTransactionsDto> result = service.getAllCourseFeeInstallmentTransactions();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(courseFeeInstallmentTransactionsRepository, times(1)).findAll();
    }

    @Test
    @Story("Get Transactions By Course Name")
    @Description("Verify fetching Course Fee Installment Transactions by course name works correctly.")
    @Severity(SeverityLevel.CRITICAL)
    void testGetTransactionsByCourseName_Success() {
        when(courseFeeInstallmentTransactionsRepository.findAll()).thenReturn(List.of(entity));
        when(courseFeeInstallmentTransactionsUtility.toCourseFeeInstallmentTransactionsDto(entity)).thenReturn(dto);

        List<CourseFeeInstallmentTransactionsDto> result = service.getTransactionsByCourseName("Java");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(courseFeeInstallmentTransactionsRepository, times(1)).findAll();
    }

}
