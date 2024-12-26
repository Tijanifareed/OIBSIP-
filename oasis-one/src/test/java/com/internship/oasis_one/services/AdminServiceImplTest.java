package com.internship.oasis_one.services;

import com.internship.oasis_one.dtos.requests.AddNewUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {
    private AdminService adminService;


    @Test
    public void testThatAdminCanAddANewUser(){
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Fareed Tijani");
        request.setEmailAddress("fareedtijani2810@gmail.com");
        request.setLibraryCardNumber(2007);
        request.setPhoneNumber("09117474727");
        request.setPassword("freddie");
        request.setHomeAddress("");
        request.setUserRole();
    }


}