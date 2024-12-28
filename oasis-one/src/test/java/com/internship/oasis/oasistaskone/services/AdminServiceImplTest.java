package com.internship.oasis.oasistaskone.services;

import com.internship.oasis.oasistaskone.dtos.requests.AddNewUserRequest;
import com.internship.oasis.oasistaskone.dtos.responses.AddNewUserResponse;
import com.internship.oasis.oasistaskone.exceptions.EmailAlreadyExists;
import com.internship.oasis.oasistaskone.exceptions.PhoneNumberAlreadyExists;
import com.internship.oasis.oasistaskone.repositories.UserRepository;
import com.internship.oasis.oasistaskone.services.admin.AdminService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

public class AdminServiceImplTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
    }
    @Autowired
    private AdminService adminService;


    @Test
    public void testThatAdminCanAddANewUser() throws IOException {
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Fareed Tijani");
        request.setEmailAddress("fareedtijani2810@gmail.com");
        request.setPhoneNumber("09117474727");
        request.setPassword("freddie");
        request.setHomeAddress("sabo, yaba");
        AddNewUserResponse response =  adminService.addNewUser(request);
        assertThat(response.getMessage()).isEqualTo("New User Created Successfully");
    }

    @Test
    public void testThatUserLibraryNumberIsUnique() throws IOException {
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Austine Joy");
        request.setEmailAddress("austinejoyz@gmail.coms");
        request.setPhoneNumber("09117474727");
        request.setHomeAddress("sabo, yaba");
        AddNewUserResponse response = adminService.addNewUser(request);
        AddNewUserRequest request1 = new AddNewUserRequest();
        request1.setUserName("Austine Joy");
        request1.setEmailAddress("austinejoyz@email.com");
        request1.setPhoneNumber("09117474728");
        request1.setHomeAddress("sabo, yaba");
        AddNewUserResponse response1 = adminService.addNewUser(request1);
        assertThat(response1.getLibraryNumber()).isNotEqualTo(response.getLibraryNumber());
    }


    @Test
    public void testThatUsersDoNotHaveDuplicateEmails() throws IOException {
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Austine Joy");
        request.setEmailAddress("austinejoyz@gmail.com");
        request.setPhoneNumber("0909998876");
        request.setHomeAddress("sabo, yaba");
        AddNewUserResponse response = adminService.addNewUser(request);
        AddNewUserRequest request1 = new AddNewUserRequest();
        request1.setUserName("Austine Joy");
        request1.setEmailAddress("austinejoyz@gmail.com");
        request1.setPhoneNumber("09117474727");
        request1.setHomeAddress("sabo, yaba");
        assertThrows(EmailAlreadyExists.class,()->adminService.addNewUser(request1));
    }

    @Test
    public void testThatUsersDoNotHaveDuplicatePhoneNumbers() throws IOException {
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Austine Joy");
        request.setEmailAddress("austinejoyz@gmail.coms");
        request.setPhoneNumber("0909998876");
        request.setHomeAddress("sabo, yaba");
        AddNewUserResponse response = adminService.addNewUser(request);
        AddNewUserRequest request1 = new AddNewUserRequest();
        request1.setUserName("Austine Joy");
        request1.setEmailAddress("austinejoyz@gmail.com");
        request1.setPhoneNumber("0909998876");
        request1.setHomeAddress("sabo, yaba");
        assertThrows(PhoneNumberAlreadyExists.class,()->adminService.addNewUser(request1));
    }





}