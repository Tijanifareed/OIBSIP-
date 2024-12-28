package com.internship.oasis.oasistaskone.services.admin;


import com.internship.oasis.oasistaskone.dtos.requests.AddNewUserRequest;
import com.internship.oasis.oasistaskone.dtos.responses.AddNewUserResponse;
import com.internship.oasis.oasistaskone.entities.User;
import com.internship.oasis.oasistaskone.entities.UserRole;
import com.internship.oasis.oasistaskone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private UserRepository userRepository;





    @Override
    public AddNewUserResponse addNewUser(AddNewUserRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmailAddress(request.getEmailAddress());
        user.setLibraryCardNumber(generateLibraryNumber());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setHomeAddress(request.getHomeAddress());
        user.setUserRole(UserRole.CUSTOMER);
        user.setProfilePicture(request.getProfilePicture());
        userRepository.save(user);
        AddNewUserResponse response = new AddNewUserResponse();
        response.setMessage("New User Created Successfully");
        return response;
    }

    public int generateLibraryNumber(){
        Random random = new Random();
        int randomNumber = 1000 * random.nextInt(9000);
        User user = userRepository.findUserByLibraryCardNumber(randomNumber);
        if(user != null){
            generateLibraryNumber();
        }
        return randomNumber;

    }

}
