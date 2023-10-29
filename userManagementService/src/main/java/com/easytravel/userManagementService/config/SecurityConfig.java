package com.easytravel.userManagementService.config;


import javax.sql.DataSource;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/{

 /*   @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(auth ->
                        auth.anyRequest()
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }*/

  /*  @Bean                                                               //TODO: Make it communicate to the db
    public UserDetailsManager userDetailsManager(DataSource dataSource){ //TODO: Hash the passwords later
                                                                        //this is a simplified example and is not communicating with actual db. fix it.
        UserDetails userDetails= User.withDefaultPasswordEncoder()
                .username("USER")
                .password("password")
                .roles("user")
                .build();
        JdbcUserDetailsManager users= new JdbcUserDetailsManager(dataSource);
        users.createUser(userDetails);
        return users;
    }
*/
}