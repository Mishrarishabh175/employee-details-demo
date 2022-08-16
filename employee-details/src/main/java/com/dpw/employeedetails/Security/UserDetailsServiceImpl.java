package com.dpw.employeedetails.Security;

import com.dpw.employeedetails.entity.User;
import com.dpw.employeedetails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Username does not exist");
        }
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("admin"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }

}
