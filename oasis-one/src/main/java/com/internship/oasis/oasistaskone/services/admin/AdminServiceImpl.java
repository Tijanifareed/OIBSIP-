package com.internship.oasis.oasistaskone.services.admin;


import com.internship.oasis.oasistaskone.dtos.requests.AddNewUserRequest;
import com.internship.oasis.oasistaskone.dtos.responses.AddNewUserResponse;
import com.internship.oasis.oasistaskone.entities.User;
import com.internship.oasis.oasistaskone.entities.UserRole;
import com.internship.oasis.oasistaskone.exceptions.EmailAlreadyExists;
import com.internship.oasis.oasistaskone.repositories.UserRepository;
import com.internship.oasis.oasistaskone.services.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageService imageService;


    @Override
    public AddNewUserResponse addNewUser(AddNewUserRequest request) throws IOException {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmailAddress(request.getEmailAddress());
        user.setLibraryCardNumber(generateLibraryNumber());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setHomeAddress(request.getHomeAddress());
        user.setUserRole(UserRole.CUSTOMER);

//        String profilePictureUrl = imageService.uploadImage(request.getProfilePicture());
//        user.setProfilePicture(profilePictureUrl);
        userRepository.save(user);
        AddNewUserResponse response = new AddNewUserResponse();
        response.setMessage("New User Created Successfully");
        response.setLibraryNumber(user.getLibraryCardNumber());
        return response;
    }

    public void validateEmail(String email){
        List<User> users= userRepository.findAll();
        for(User user : users){
            if (email == user.getEmailAddress()) throw new EmailAlreadyExists("A user with this email already exists");
        }
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
