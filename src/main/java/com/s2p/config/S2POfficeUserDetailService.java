package com.s2p.config;

import com.s2p.model.SuperAdminUsers;
import com.s2p.model.Users;
import com.s2p.repository.AdminUsersRepository;
import com.s2p.repository.StudentRepository;
import com.s2p.repository.SuperAdminRepository;
import com.s2p.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class S2POfficeUserDetailService implements UserDetailsService {


        @Autowired
        private final SuperAdminRepository superAdminRepository;

        @Autowired
        private final AdminUsersRepository adminUsersRepository;

        @Autowired
        private final TeacherRepository teacherRepository;

        @Autowired
        private final StudentRepository studentRepository;



        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            Users applicationUser = fetchUserByEmail(email);

            // Exception Handling
            if(null == applicationUser)
            {
                throw new UsernameNotFoundException("User Detail Not Found For The User : " + email);
            }

            List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(applicationUser.getRoles().getRolesName()));

            User userDetails = new User(applicationUser.getUsername(),applicationUser.getPwd(),authorityList);

            return userDetails;

        }

        private Users fetchUserByEmail(String email)
        {
            Users applicationUser = null;

            if(null == applicationUser)
            {
                applicationUser = superAdminRepository.findByEmail(email).orElse(null);
            }

            if(null == applicationUser)
            {
                applicationUser = adminUsersRepository.findByEmail(email).orElse(null);
            }

            // Business Logic To Fetch Teacher
            if(null == applicationUser) {
                applicationUser = teacherRepository.findByEmail(email).orElse(null);
            }

            // Business Logic To Fetch Student
            if(null == applicationUser)
            {
                applicationUser = studentRepository.findByEmail(email).orElse(null);
            }

            return applicationUser;
        }
    }

