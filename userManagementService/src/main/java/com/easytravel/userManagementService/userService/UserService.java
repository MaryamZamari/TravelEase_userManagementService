package com.easytravel.userManagementService.userService;

import com.easytravel.userManagementService.config.SecurityConfig;
import com.easytravel.userManagementService.model.RegistrationRequest;
import com.easytravel.userManagementService.model.User;
import com.easytravel.userManagementService.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {
    //TODO: Add SWAGGER and test all the APIs you created so far.

    @Autowired
    private UserRepository repository;
    private static final SecurityConfig securityConfig = new SecurityConfig();
    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    public List<User> findUsers(){
        return repository.findAll();
    }
    public Optional<User> findUser(Long id){
        return repository.findById(Long.valueOf(id));
    }

    public void registerUser(RegistrationRequest registration) {
        logger.info("Starting to create a new user from request: {}", registration);
        Optional<User> userByUserName = Optional.ofNullable(repository.findByUserName(registration.getUserName()));
        Optional<User> userByEmail = Optional.ofNullable(repository.findByEmail((registration.getEmail())));
        LocalDate now = LocalDate.now();
        LocalDate userBirthday = registration.getBirthday();
        Period period = Period.between(userBirthday, now);
        boolean hasValidAge = period.getYears() >= 18;

        if (!userByUserName.isPresent() && !userByEmail.isPresent() && hasValidAge) {
            User newUser = new User();
                  newUser.setFirstName(registration.getFirstName());
                    newUser.setLastName(registration.getLastName());
                    newUser.setUserName(registration.getUserName());
                    newUser.setPassword(securityConfig.passwordEncoder().encode(registration.getPassword()));
                    //newUser.setPassword(registration.getPassword());
                    newUser.setBirthday(registration.getBirthday());
                    newUser.setEmail(registration.getEmail());

            repository.save(newUser);
            logger.info("Created User is:{}", newUser.toString());
        } else if (userByUserName.isPresent() || userByEmail.isPresent()) {
            throw new RuntimeException("Username or email already Exist!");
        } else if (!hasValidAge) {
            throw new RuntimeException("user is under 18 years old!");
        }

    }

    public void updatePassword(Long id, User updatedUser) {

        Optional<User> currentUser= repository.findById(id);
        if(currentUser.isPresent()){
            User user = currentUser.get();
                user.setPassword(updatedUser.getPassword());
                repository.save(user);
            }else{
            throw new RuntimeException("User does not exist!");
             }
        }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public void login(User user){

    }

    @Override //for Spring Security and authentication measures using username and password credentials.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("trying to load user info using username {}", username);
        User user= repository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        logger.info("Loaded user: {} with role: {}", user.getUsername(), user.getRole());
        // Create and return a UserDetails object based on the retrieved User entity
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().name())
        );

    }


}
