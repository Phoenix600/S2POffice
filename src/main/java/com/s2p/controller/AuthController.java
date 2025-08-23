package com.s2p.controller;

import com.s2p.constants.ApplicationConstants;
import com.s2p.dto.AdminUserDto;
import com.s2p.dto.LoginResponseDto;
import com.s2p.dto.RegisterResponseDto;
import com.s2p.dto.SuperAdminUserDto;
import com.s2p.master.dto.StudentUsersDto;
import com.s2p.model.*;
import com.s2p.repository.*;
import com.s2p.util.AdminUserUtility;
import com.s2p.util.RolesUtility;
import com.s2p.util.SuperAdminUserUtility;
import com.s2p.util.StudentUsersUtility;
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
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final SuperAdminRepository superAdminRepository;
    private final AdminUsersRepository adminUsersRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    //POST:-  http://localhost:8080/api/v1/auth/admin/register
    @PostMapping("/superAdmin/register")
    public ResponseEntity<RegisterResponseDto> registerSuperAdmin(
            @Parameter(description = "User registration data", required = true)
            @RequestBody Users users)
    {
        Roles roles = rolesRepository.findByRolesName("ROLE_SUPER_ADMIN");
        SuperAdminUsers superAdminUsers = new SuperAdminUsers();

        // Populating Admin User Details
        superAdminUsers.setUsername("S2P" + UUID.randomUUID().toString()); // Unique username
        superAdminUsers.setEmail(users.getEmail());
        superAdminUsers.setPwd(passwordEncoder.encode(users.getPwd()));
        superAdminUsers.setRoles(roles);

        superAdminUsers = superAdminRepository.save(superAdminUsers);
        SuperAdminUserDto superDtoResponse = SuperAdminUserUtility.toSuperAdminUserDto(superAdminUsers);


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
		registerResponseDto.setRolesDto(RolesUtility.toRolesDto(superAdminUsers.getRoles()));
        registerResponseDto.setToken(jwt);

		return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwt).body(registerResponseDto);


        // ==================================

//        return ResponseEntity.status(HttpStatus.CREATED).body(superDtoResponse);
    }

    //POST:-  http://localhost:8080/api/v1/auth/admin/register
    @PostMapping("/admin/register")
    public ResponseEntity<AdminUserDto> registerAdmin(
            @Parameter(description = "User registration data", required = true)
            @RequestBody Users users)
    {
        Roles roles = rolesRepository.findByRolesName("ROLE_ADMIN");
        AdminUsers adminUser = new AdminUsers();

        // Populating Admin User Details
        adminUser.setUsername("S2P" + UUID.randomUUID().toString()); // Unique username
        adminUser.setEmail(users.getEmail());
        adminUser.setPwd(passwordEncoder.encode(users.getPwd()));
        adminUser.setRoles(roles);

        adminUser = adminUsersRepository.save(adminUser);
        AdminUserDto adminUserDto = AdminUserUtility.toAdminUserDto(adminUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(adminUserDto);
    }

    // POST   http://localhost:8080/api/v1/public/student/register
    @PostMapping("student/register")
    public ResponseEntity<StudentUsersDto> registerStudent(
            @Parameter(description = "User registration data", required = true)
           @RequestBody Users users)
    {
        Roles roles = rolesRepository.findByRolesName("ROLE_STUDENT");
        StudentUsers studentUser = new StudentUsers();

        // Populating Student User Details
        studentUser.setUsername("S2P" + UUID.randomUUID().toString()); // Unique username
        studentUser.setEmail(users.getEmail());
        studentUser.setPwd(passwordEncoder.encode(users.getPwd()));
        studentUser.setRoles(roles);

        studentUser = studentRepository.save(studentUser);
        StudentUsersDto savedStudent = StudentUsersUtility.toStudentUserDto(studentUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

//    //    http://localhost/8080/api/v1/public/apiLogin
//    @PostMapping("/apiLogin")
//    public ResponseEntity<LoginResponseDto> apiLogin (@RequestBody LoginRequestDto loginRequest)
//    {
//        String jwt = "";
//        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(),
//                loginRequest.getPassword());
//        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
//        if(null != authenticationResponse && authenticationResponse.isAuthenticated())
//        {
//
//            String secret = ApplicationConstants.JWT_SECRET_DEFAULT_VALUE;
//            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//            jwt = Jwts.builder().issuer("S2P-Dev-Team").subject("JWT TOKEN")
//                    .claim("username", authenticationResponse.getName())
//                    .claim("authorities", authenticationResponse.getAuthorities().stream().map(
//                            GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
//                    .issuedAt(new java.util.Date())
//                    .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
//                    .signWith(secretKey).compact();
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwt)
//                .body(new LoginResponseDto(SecurityContextHolder.getContext().getAuthentication().getName(), jwt));
//    }
}
