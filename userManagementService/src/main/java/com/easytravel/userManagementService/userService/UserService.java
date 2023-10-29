package com.easytravel.userManagementService.userService;

import com.easytravel.userManagementService.model.User;
import com.easytravel.userManagementService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //TODO: Add SWAGGER and test all the APIs you created so far.

    @Autowired
    private UserRepository repository;

    public List<User> findUsers(){
        return repository.findAll();
    }
    public Optional<User> findUser(Long id){
        return repository.findById(Long.valueOf(id));
    }

      public void createUser(User user) {
        Optional<User> userByUserName= Optional.ofNullable(repository.findByUserName(user.getUserName()));
        Optional<User> userByEmail= Optional.ofNullable(repository.findByEmail((user.getEmail())));
        LocalDate now= LocalDate.now();
        LocalDate userBirthday= user.getBirthday();
        Period period= Period.between(userBirthday, now );
        boolean hasValidAge= period.getYears() >= 18;
        if(!userByUserName.isPresent() && !userByEmail.isPresent() && hasValidAge ){
            repository.save(user);
        }else if(userByUserName.isPresent() || userByEmail.isPresent()){
            throw new RuntimeException("Username or email already Exist!");
        }else if(!hasValidAge){
            throw new RuntimeException("user is under 18 years old!");
        }
    }

    public void updateUser(Long id, User updatedUser) {

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

}
