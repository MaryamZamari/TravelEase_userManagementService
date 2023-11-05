package com.easytravel.userManagementService.security;

import com.easytravel.userManagementService.model.Role;
import com.easytravel.userManagementService.userService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    @Lazy //lazy initialization to avoid circular dependency with springSecurity
    private UserService userService ;

    @Autowired
    private RoleLoggingFilter roleLoggingFilter;
    public static final Logger logger= LoggerFactory.getLogger(SecurityConfig.class);


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserService userService) throws Exception{ //using configureGlobal which will use userService to configure authentication
      logger.info("configureGlobal method invoked");
      auth.userDetailsService(userService);
    }


    //Configuring access control
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        logger.info("securityFilterChain method invoked. checking for correct roles based on path.");
        http.csrf(x-> x.disable());  //Cross-site Request Forgery
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.POST,  "/public/**").permitAll()
                               // .requestMatchers(HttpMethod.GET,  "/public/**").permitAll()
                                .requestMatchers("/user/users/**").hasAnyRole(Role.AUTH_USER.name(), Role.ADMIN.name())
                                .requestMatchers("/admin/users/**").hasRole(Role.ADMIN.name())
                                .anyRequest().authenticated()
                );
        http.httpBasic(withDefaults());
        return http.build();
    }

    /*
    DaoAuthenticationProvider automatically retrieves the user's encoded password from the database, decodes it,
     and then compares it to the plain text password provided during the authentication process. If they match, authentication is successful.
     The DaoAuthenticationProvider handles the encoding and verification of passwords automatically.
     */
    @Bean         //setting authentication with user's username and password that configured in the userService class.
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService){
        logger.info("hitting DaoAuthenticationProvider");
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}