package com.internship.oasis.oasistaskone.services.admin;


import com.internship.oasis.oasistaskone.dtos.requests.AddNewUserRequest;
import com.internship.oasis.oasistaskone.dtos.responses.AddNewUserResponse;

public interface AdminService {
    AddNewUserResponse addNewUser(AddNewUserRequest request);
}
