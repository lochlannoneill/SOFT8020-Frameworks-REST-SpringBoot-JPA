package com.lochlann.assignment2.security;

import com.lochlann.assignment2.dao.StaffDao;
import com.lochlann.assignment2.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private StaffDao staffDao;

    // Creating our own loadUserByUsername() method so that the user is loaded from our database for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Staff> optionalStaff = staffDao.findById(username);
        if(optionalStaff.isPresent()) {
            Staff staff = optionalStaff.get();
            //Create an instance of Spring's User class using data from our database
            return User.builder()
                    .username(staff.getEmail())
                    .password(staff.getPassword())
                    .roles(staff.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("Staff {" + username + "} not found");
        }
    }
}
