package com.nrv.NrvBlogAPI.security;

import com.nrv.NrvBlogAPI.custom_exception.ResourceNotFoundException;
import com.nrv.NrvBlogAPI.entities.User;
import com.nrv.NrvBlogAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom user details Implementation service class which
 * retrieve user-specific details based on the provided username.
 * Authentication Provider delegates to this class for this purpose.
 *
 * @author Nirav Parekh
 * @see SecurityConfig
 * @see org.springframework.security.authentication.AuthenticationProvider
 * @since 1.0
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username)
                .orElseThrow(()-> new ResourceNotFoundException("User with this ID not found"));
        return new CustomUserDetails(user);
    }
}
