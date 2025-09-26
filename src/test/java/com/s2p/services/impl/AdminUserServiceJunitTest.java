package com.s2p.services.impl;


import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.services.impl.AdminUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase // use H2 in-memory DB
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // reset DB after each test
class AdminUserServiceFailureTest {

    @Autowired
    private AdminUserService adminUserService;

    @Test
    void test_get_admin_user_not_found_failure() {
        Exception exception = assertThrows(
                ResourceNotFoundException.class,
                () -> adminUserService.getAdminUserByUsername("ghostUser")
        );

        assertEquals("AdminUser not found with username: ghostUser", exception.getMessage());
    }

    @Test
    void test_update_admin_user_not_found_failure() {
        Exception exception = assertThrows(
                ResourceNotFoundException.class,
                () -> adminUserService.updateAdminUserByUsername("ghostUser", null)
        );

        assertEquals("AdminUser not found with username: ghostUser", exception.getMessage());
    }

    @Test
    void test_delete_admin_user_not_found_failure() {
        Exception exception = assertThrows(
                ResourceNotFoundException.class,
                () -> adminUserService.deleteAdminUserByUsername("ghostUser")
        );

        assertEquals("AdminUser not found with username: ghostUser", exception.getMessage());
    }
}
