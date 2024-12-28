package com.internship.oasis.oasistaskone.services;

import com.internship.oasis.oasistaskone.dtos.requests.AddNewUserRequest;
import com.internship.oasis.oasistaskone.dtos.responses.AddNewUserResponse;
import com.internship.oasis.oasistaskone.services.admin.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;


    @Test
    public void testThatAdminCanAddANewUser(){
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Fareed Tijani");
        request.setEmailAddress("fareedtijani2810@gmail.com");
        request.setLibraryCardNumber(2007);
        request.setPhoneNumber("09117474727");
        request.setPassword("freddie");
        request.setHomeAddress("sabo, yaba");
        AddNewUserResponse response =  adminService.addNewUser(request);
        assertThat(response.getMessage()).isEqualTo("New User Created Successfully");
    }





}