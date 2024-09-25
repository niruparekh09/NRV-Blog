package com.nrv.NrvBlogAPI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * This class is responsible to configure security of our whole server.
 *
 * @author Nirav Parekh
 * @see CustomUserDetailsServiceImpl
 * @see JWTRequestFilter
 * @see JwtUtils
 * @since 1.0
 */
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JWTRequestFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * This method creates a SecurityFilterChain, which is a series of filters that handle
     * security-related concerns for incoming HTTP requests.
     *
     * @param http HttpSecurity object
     * @return http object added with filter
     * @author Nirav Parekh
     * @since 1.0
     */
    @Bean
    public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public access
                        .requestMatchers(HttpMethod.GET, "/api/v1/blog", "/api/v1/blog/**", "/api/v1/user/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/register", "/api/v1/user/login").permitAll()

                        // Restricted access
                        .requestMatchers(HttpMethod.PUT, "/api/v1/user/**").hasAnyRole("USER")

                        // Authentication required
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/user/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/blog/**").authenticated() // Allow only the person who has created blog or is Admin
                        .requestMatchers(HttpMethod.POST, "/api/v1/blog/post").authenticated()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * This method configures AuthenticationProvider. We have configured it with as
     * DaoAuthenticationProvider as provider, Bcrypt as password encoder and
     * our userDetailService {@link CustomUserDetailsServiceImpl}
     *
     * @return provider
     * @author Nirav Parekh
     * @see BCryptPasswordEncoder
     * @see CustomUserDetailsServiceImpl
     * @see DaoAuthenticationProvider
     * @since 1.0
     */
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    /**
     * This method creates and configures an AuthenticationManager
     *
     * @param config Auth Config
     * @author Nirav Parekh
     * @since 1.0
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
