package com.s2p.controller;

import com.s2p.constants.ApplicationConstants;
import com.s2p.dto.*;
import com.s2p.model.*;
import com.s2p.repository.*;
import com.s2p.services.impl.OtpService;
import com.s2p.util.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final SuperAdminRepository superAdminRepository;
    private final AdminUsersRepository adminUsersRepository;
    private final TeacherUserRepository teacherUserRepository;
    private final StudentUserRepository studentUserRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SuperAdminUserUtility superAdminUserUtility;
    private final RolesUtility rolesUtility;
    private final AdminUserUtility adminUserUtility;
    private final StudentUsersUtility studentUsersUtility;
    private final TeacherUsersUtility teacherUsersUtility;
    private final OtpService otpService;
    private final EmailUtility emailUtility;

    //POST:-  http://localhost:8080/api/v1/auth/superAdmin/register
    @PostMapping("/superAdmin/register")
    public ResponseEntity<RegisterResponseDto> registerSuperAdmin(
            @Parameter(description = "User registration data", required = true)
            @RequestBody Users users)
    {
        Roles roles = rolesRepository.findByRolesName("ROLES_SUPER_ADMIN");
        SuperAdminUsers superAdminUsers = new SuperAdminUsers();

        // Populating Admin User Details
        superAdminUsers.setUsername("S2P" + UUID.randomUUID().toString()); // Unique username
        superAdminUsers.setEmail(users.getEmail());
        superAdminUsers.setPwd(passwordEncoder.encode(users.getPwd()));
        superAdminUsers.setRoles(roles);

        superAdminUsers = superAdminRepository.save(superAdminUsers);
        SuperAdminUserDto superDtoResponse = superAdminUserUtility.toSuperAdminUserDto(superAdminUsers);


        // =================================
        String jwt = "";
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(superAdminUsers.getEmail(),
                users.getPwd());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        if(null != authenticationResponse && authenticationResponse.isAuthenticated())
        {

            String secret = ApplicationConstants.JWT_SECRET_DEFAULT_VALUE;
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            jwt = Jwts.builder().issuer("S2P-Dev-Team").subject("JWT TOKEN")
                    .claim("username", authenticationResponse.getName())
                    .claim("authorities", authenticationResponse.getAuthorities().stream().map(
                            GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                    .issuedAt(new java.util.Date())
                    .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                    .signWith(secretKey).compact();
        }
//
//        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwt)
//                .body(new LoginResponseDto(SecurityContextHolder.getContext().getAuthentication().getName(), jwt));
		
	    RegisterResponseDto registerResponseDto = new RegisterResponseDto();
		registerResponseDto.setEmail(superAdminUsers.getEmail());
		registerResponseDto.setRolesDto(rolesUtility.toRolesDto(superAdminUsers.getRoles()));
        registerResponseDto.setToken(jwt);

		return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwt).body(registerResponseDto);


        // ==================================

//        return ResponseEntity.status(HttpStatus.CREATED).body(superDtoResponse);
    }

    //POST:-  http://localhost:8080/api/v1/auth/admin/register
    @PostMapping("/admin/register")
    public ResponseEntity<RegisterResponseDto> registerAdmin(
            @Parameter(description = "Admin registration data", required = true)
            @RequestBody Users users) {

        // 1. Fetch the Admin Role from DB
        Roles roles = rolesRepository.findByRolesName("ROLE_ADMIN");
        AdminUsers adminUsers = new AdminUsers();

        // 2. Populate Admin User Details
        adminUsers.setUsername("ADM" + UUID.randomUUID().toString()); // Unique username prefix for Admin
        adminUsers.setEmail(users.getEmail());
        adminUsers.setPwd(passwordEncoder.encode(users.getPwd()));
        adminUsers.setRoles(roles);

        // 3. Save Admin User in DB
        adminUsers = adminUsersRepository.save(adminUsers);
        AdminUserDto adminDtoResponse = adminUserUtility.toAdminUserDto(adminUsers);

        // 4. Authenticate and Generate JWT Token
        String jwt = "";
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(
                adminUsers.getEmail(), users.getPwd());

        Authentication authenticationResponse = authenticationManager.authenticate(authentication);

        if (authenticationResponse != null && authenticationResponse.isAuthenticated()) {
            String secret = ApplicationConstants.JWT_SECRET_DEFAULT_VALUE;
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            jwt = Jwts.builder()
                    .issuer("S2P-Dev-Team")
                    .subject("JWT TOKEN")
                    .claim("username", authenticationResponse.getName())
                    .claim("authorities", authenticationResponse.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(",")))
                    .issuedAt(new java.util.Date())
                    .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                    .signWith(secretKey)
                    .compact();
        }

        // 5. Prepare Response DTO
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        registerResponseDto.setEmail(adminUsers.getEmail());
        registerResponseDto.setRolesDto(rolesUtility.toRolesDto(adminUsers.getRoles()));
        registerResponseDto.setToken(jwt);

        // 6. Return Response with JWT in header
        return ResponseEntity.status(HttpStatus.OK)
                .header(ApplicationConstants.JWT_HEADER, jwt)
                .body(registerResponseDto);
    }


    // POST   http://localhost:8080/api/v1/public/student/register
    @PostMapping("/student/register")
    public ResponseEntity<RegisterResponseDto> registerStudent(
            @Parameter(description = "Student registration data", required = true)
            @RequestBody Users users) {

        // 1. Fetch the Student Role from DB
        Roles roles = rolesRepository.findByRolesName("ROLE_STUDENT");
        StudentUsers studentUsers = new StudentUsers();

        // 2. Populate Student User Details
        studentUsers.setUsername("STU" + UUID.randomUUID().toString()); // Unique username prefix for Student
        studentUsers.setEmail(users.getEmail());
        studentUsers.setPwd(passwordEncoder.encode(users.getPwd()));
        studentUsers.setRoles(roles);

        // 3. Save Student User in DB
        studentUsers = studentUserRepository.save(studentUsers);
        StudentUserDto studentDtoResponse = studentUsersUtility.toStudentUserDto(studentUsers);

        // 4. Authenticate and Generate JWT Token
        String jwt = "";
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(
                studentUsers.getEmail(), users.getPwd());

        Authentication authenticationResponse = authenticationManager.authenticate(authentication);

        if (authenticationResponse != null && authenticationResponse.isAuthenticated()) {
            String secret = ApplicationConstants.JWT_SECRET_DEFAULT_VALUE;
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            jwt = Jwts.builder()
                    .issuer("S2P-Dev-Team")
                    .subject("JWT TOKEN")
                    .claim("username", authenticationResponse.getName())
                    .claim("authorities", authenticationResponse.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(",")))
                    .issuedAt(new java.util.Date())
                    .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                    .signWith(secretKey)
                    .compact();
        }

        // 5. Prepare Response DTO
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        registerResponseDto.setEmail(studentUsers.getEmail());
        registerResponseDto.setRolesDto(rolesUtility.toRolesDto(studentUsers.getRoles()));
        registerResponseDto.setToken(jwt);

        // 6. Return Response with JWT in header
        return ResponseEntity.status(HttpStatus.OK)
                .header(ApplicationConstants.JWT_HEADER, jwt)
                .body(registerResponseDto);
    }

    @PostMapping("/teacher/register")
    public ResponseEntity<RegisterResponseDto> registerTeacher(
            @Parameter(description = "Teacher registration data", required = true)
            @RequestBody Users users) {

        // 1. Fetch the Teacher Role from DB
        Roles roles = rolesRepository.findByRolesName("ROLE_TEACHER");
        TeacherUsers teacherUsers = new TeacherUsers();

        // 2. Populate Teacher User Details
        teacherUsers.setUsername("TEA" + UUID.randomUUID().toString()); // Unique username prefix for Teacher
        teacherUsers.setEmail(users.getEmail());
        teacherUsers.setPwd(passwordEncoder.encode(users.getPwd()));
        teacherUsers.setRoles(roles);

        // 3. Save Teacher User in DB
        teacherUsers = teacherUserRepository.save(teacherUsers);
        TeacherUserDto teacherDtoResponse = teacherUsersUtility.toTeacherUsersDto(teacherUsers);

        // 4. Authenticate and Generate JWT Token
        String jwt = "";
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(
                teacherUsers.getEmail(), users.getPwd());

        Authentication authenticationResponse = authenticationManager.authenticate(authentication);

        if (authenticationResponse != null && authenticationResponse.isAuthenticated()) {
            String secret = ApplicationConstants.JWT_SECRET_DEFAULT_VALUE;
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            jwt = Jwts.builder()
                    .issuer("S2P-Dev-Team")
                    .subject("JWT TOKEN")
                    .claim("username", authenticationResponse.getName())
                    .claim("authorities", authenticationResponse.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(",")))
                    .issuedAt(new java.util.Date())
                    .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                    .signWith(secretKey)
                    .compact();
        }

        // 5. Prepare Response DTO
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        registerResponseDto.setEmail(teacherUsers.getEmail());
        registerResponseDto.setRolesDto(rolesUtility.toRolesDto(teacherUsers.getRoles()));
        registerResponseDto.setToken(jwt);

        // 6. Return Response with JWT in header
        return ResponseEntity.status(HttpStatus.OK)
                .header(ApplicationConstants.JWT_HEADER, jwt)
                .body(registerResponseDto);
    }
//

// ================= LOGIN APIs =================

    @PostMapping("/superAdmin/login")
    public ResponseEntity<?> loginSuperAdmin(@RequestBody Users users) {
        return handleLogin(users, superAdminRepository.findByEmail(users.getEmail()));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Users users) {
        return handleLogin(users, adminUsersRepository.findByEmail(users.getEmail()));
    }

    @PostMapping("/student/login")
    public ResponseEntity<?> loginStudent(@RequestBody Users users) {
        return handleLogin(users, studentUserRepository.findByEmail(users.getEmail()));
    }

    @PostMapping("/teacher/login")
    public ResponseEntity<?> loginTeacher(@RequestBody Users users) {
        return handleLogin(users, teacherUserRepository.findByEmail(users.getEmail()));
    }

// ================= COMMON LOGIN HANDLER =================

    private ResponseEntity<?> handleLogin(Users users, Object userEntity) {
        try {
            // 1. Authenticate
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPwd())
            );

            if (authentication != null && authentication.isAuthenticated() && userEntity != null) {
                // 2. Generate JWT
                String jwt = generateJwt(authentication);

                // 3. Prepare response
                LoginResponseDto response = new LoginResponseDto();

                if (userEntity instanceof SuperAdminUsers) {
                    SuperAdminUsers superAdmin = (SuperAdminUsers) userEntity;
                    response.setEmail(superAdmin.getEmail());
                    response.setUsername(superAdmin.getUsername());
                    response.setRolesDto(rolesUtility.toRolesDto(superAdmin.getRoles()));
                } else if (userEntity instanceof AdminUsers) {
                    AdminUsers admin = (AdminUsers) userEntity;
                    response.setEmail(admin.getEmail());
                    response.setUsername(admin.getUsername());
                    response.setRolesDto(rolesUtility.toRolesDto(admin.getRoles()));
                } else if (userEntity instanceof StudentUsers) {
                    StudentUsers student = (StudentUsers) userEntity;
                    response.setEmail(student.getEmail());
                    response.setUsername(student.getUsername());
                    response.setRolesDto(rolesUtility.toRolesDto(student.getRoles()));
                } else if (userEntity instanceof TeacherUsers) {
                    TeacherUsers teacher = (TeacherUsers) userEntity;
                    response.setEmail(teacher.getEmail());
                    response.setUsername(teacher.getUsername());
                    response.setRolesDto(rolesUtility.toRolesDto(teacher.getRoles()));
                }

                response.setToken(jwt);

                // 4. Return response with JWT in header
                return ResponseEntity.ok()
                        .header(ApplicationConstants.JWT_HEADER, jwt)
                        .body(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }

    // ================= JWT GENERATOR =================
    private String generateJwt(Authentication authentication) {
        String secret = ApplicationConstants.JWT_SECRET_DEFAULT_VALUE;
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .issuer("S2P-Dev-Team")
                .subject("JWT TOKEN")
                .claim("username", authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .issuedAt(new java.util.Date())
                .expiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8)) // 8 hrs
                .signWith(secretKey)
                .compact();
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Users users) {
        try {
            // 1. Check if user exists (check in all repositories)
            Object userEntity = superAdminRepository.findByEmail(users.getEmail());
            if (userEntity == null) userEntity = adminUsersRepository.findByEmail(users.getEmail());
            if (userEntity == null) userEntity = studentUserRepository.findByEmail(users.getEmail());
            if (userEntity == null) userEntity = teacherUserRepository.findByEmail(users.getEmail());

            if (userEntity == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + users.getEmail());
            }

            // 2. Generate OTP
            String otp = otpService.generateOtp(users.getEmail());

            // 3. Send OTP via email
            emailUtility.sendOtpEmail(users.getEmail(), otp);

            return ResponseEntity.ok("OTP sent to email: " + users.getEmail());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            // 1. Validate OTP
            if (!otpService.validateOtp(request.getEmail(), request.getOtp())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP");
            }

            // 2. Find user by email
            Object userEntity = superAdminRepository.findByEmail(request.getEmail());
            if (userEntity == null) userEntity = adminUsersRepository.findByEmail(request.getEmail());
            if (userEntity == null) userEntity = studentUserRepository.findByEmail(request.getEmail());
            if (userEntity == null) userEntity = teacherUserRepository.findByEmail(request.getEmail());

            if (userEntity == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // 3. Update password
            String encodedPwd = passwordEncoder.encode(request.getNewPassword());

            if (userEntity instanceof SuperAdminUsers) {
                SuperAdminUsers u = (SuperAdminUsers) userEntity;
                u.setPwd(encodedPwd);
                superAdminRepository.save(u);
            } else if (userEntity instanceof AdminUsers) {
                AdminUsers u = (AdminUsers) userEntity;
                u.setPwd(encodedPwd);
                adminUsersRepository.save(u);
            } else if (userEntity instanceof StudentUsers) {
                StudentUsers u = (StudentUsers) userEntity;
                u.setPwd(encodedPwd);
                studentUserRepository.save(u);
            } else if (userEntity instanceof TeacherUsers) {
                TeacherUsers u = (TeacherUsers) userEntity;
                u.setPwd(encodedPwd);
                teacherUserRepository.save(u);
            }

            return ResponseEntity.ok("Password reset successful!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
