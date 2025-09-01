package com.s2p.services.impl;

import com.s2p.dto.AdminUserDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.AdminUsers;
import com.s2p.repository.AdminUsersRepository;
import com.s2p.util.AdminUserUtility;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Epic("Admin User Management")
@Feature("AdminUserService Unit Tests")
@ExtendWith(AllureJunit5.class)
class AdminUserServiceTest {

    @Mock
    private AdminUsersRepository adminUsersRepository;

    @Mock
    private AdminUserUtility adminUserUtility;

    @InjectMocks
    private AdminUserService adminUserService;

    private AutoCloseable closeable;

    private AdminUserDto sampleAdminUserDto;
    private AdminUsers sampleAdminUser;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        // Sample DTO
        sampleAdminUserDto = new AdminUserDto();
        sampleAdminUserDto.setAdminUserId(UUID.randomUUID());
        sampleAdminUserDto.setEmail("admin@example.com");
        sampleAdminUserDto.setUsername("adminUser");
        sampleAdminUserDto.setRoles(null); // replace with Roles if needed

        // Sample Entity
        sampleAdminUser = new AdminUsers();
        sampleAdminUser.setAdminUserId(sampleAdminUserDto.getAdminUserId());
        sampleAdminUser.setEmail("admin@example.com");
        sampleAdminUser.setUsername("adminUser");
        sampleAdminUser.setRoles(null);

        log.info("🔹 Test setup complete");
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @Story("Create new admin user")
    @Description("Verify that a new admin user is created successfully")
    @DisplayName("Create Admin User - Success")
    void testCreateAdminUser() {
        when(adminUserUtility.toAdminUserEntity(sampleAdminUserDto)).thenReturn(sampleAdminUser);
        when(adminUsersRepository.save(sampleAdminUser)).thenReturn(sampleAdminUser);
        when(adminUserUtility.toAdminUserDto(sampleAdminUser)).thenReturn(sampleAdminUserDto);
        AdminUserDto result = adminUserService.createAdminUser(sampleAdminUserDto);
        assertNotNull(result);
        assertEquals("adminUser", result.getUsername());
        verify(adminUsersRepository, times(1)).save(sampleAdminUser);
    }

    @Test
    @Story("Fetch admin user by username")
    @Description("Verify admin user is fetched correctly by username")
    @DisplayName("Get Admin User By Username - Found")
    void testGetAdminUserByUsername_WhenFound() {
        when(adminUsersRepository.findByUsername("adminUser")).thenReturn(Optional.of(sampleAdminUser));
        when(adminUserUtility.toAdminUserDto(sampleAdminUser)).thenReturn(sampleAdminUserDto);
        AdminUserDto result = adminUserService.getAdminUserByUsername("adminUser");
        assertNotNull(result);
        assertEquals("adminUser", result.getUsername());
    }

    @Test
    @Story("Fetch admin user by username")
    @Description("Verify exception is thrown when admin user does not exist")
    @DisplayName("Get Admin User By Username - Not Found")
    void testGetAdminUserByUsername_WhenNotFound() {
        when(adminUsersRepository.findByUsername("unknown")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () ->
                adminUserService.getAdminUserByUsername("unknown")
        );
    }

    @Test
    @Story("Fetch all admin users")
    @Description("Verify that all admin users are returned successfully")
    @DisplayName("Get All Admin Users - Success")
    void testGetAllAdminUsers() {
        when(adminUsersRepository.findAll()).thenReturn(List.of(sampleAdminUser));
        when(adminUserUtility.toAdminUserDto(sampleAdminUser)).thenReturn(sampleAdminUserDto);
        Set<AdminUserDto> result = adminUserService.getAllAdminUsers();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.stream().anyMatch(dto -> dto.getUsername().equals("adminUser")));
    }

    @Test
    @Story("Update admin user by username")
    @Description("Verify admin user can be updated when username exists")
    @DisplayName("Update Admin User By Username - Success")
    void testUpdateAdminUserByUsername_WhenFound() {
        when(adminUsersRepository.findByUsername("adminUser")).thenReturn(Optional.of(sampleAdminUser));
        when(adminUsersRepository.save(sampleAdminUser)).thenReturn(sampleAdminUser);
        when(adminUserUtility.toAdminUserDto(sampleAdminUser)).thenReturn(sampleAdminUserDto);
        AdminUserDto result = adminUserService.updateAdminUserByUsername("adminUser", sampleAdminUserDto);
        assertNotNull(result);
        assertEquals("adminUser", result.getUsername());
        verify(adminUsersRepository, times(1)).save(sampleAdminUser);
    }

    @Test
    @Story("Update admin user by username")
    @Description("Verify exception is thrown when updating non-existent admin user")
    @DisplayName("Update Admin User By Username - Not Found")
    void testUpdateAdminUserByUsername_WhenNotFound() {
        when(adminUsersRepository.findByUsername("unknown")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () ->
                adminUserService.updateAdminUserByUsername("unknown", sampleAdminUserDto)
        );
    }

    @Test
    @Story("Delete admin user by username")
    @Description("Verify admin user can be deleted when username exists")
    @DisplayName("Delete Admin User By Username - Success")
    void testDeleteAdminUserByUsername_WhenFound() {
        when(adminUsersRepository.findByUsername("adminUser")).thenReturn(Optional.of(sampleAdminUser));
        when(adminUserUtility.toAdminUserDto(sampleAdminUser)).thenReturn(sampleAdminUserDto);
        AdminUserDto result = adminUserService.deleteAdminUserByUsername("adminUser");
        assertNotNull(result);
        assertEquals("adminUser", result.getUsername());
        verify(adminUsersRepository, times(1)).delete(sampleAdminUser);
    }

    @Test
    @Story("Delete admin user by username")
    @Description("Verify exception is thrown when deleting non-existent admin user")
    @DisplayName("Delete Admin User By Username - Not Found")
    void testDeleteAdminUserByUsername_WhenNotFound() {
        when(adminUsersRepository.findByUsername("unknown")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () ->
                adminUserService.deleteAdminUserByUsername("unknown")
        );
    }
}
