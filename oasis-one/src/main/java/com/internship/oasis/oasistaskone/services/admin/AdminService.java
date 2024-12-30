package com.internship.oasis.oasistaskone.services.admin;


import com.internship.oasis.oasistaskone.dtos.requests.*;
import com.internship.oasis.oasistaskone.dtos.responses.AddBookResponse;
import com.internship.oasis.oasistaskone.dtos.responses.AddNewUserResponse;
import com.internship.oasis.oasistaskone.dtos.responses.DeleteUserResponse;
import com.internship.oasis.oasistaskone.dtos.responses.EditBookResponse;
import com.internship.oasis.oasistaskone.services.DeleteBookResponse;

import java.io.IOException;

public interface AdminService {
    AddNewUserResponse addNewUser(AddNewUserRequest request) throws IOException;

    AddBookResponse addNewBookWith(AddBookRequest request);

    DeleteBookResponse deleteExistingBook(DeleteBookRequest request1);

    DeleteUserResponse deleteExistingUser(DeleteUserRequest request1);


//    EditBookResponse editExistingBookWith(EditBookRequest request1);
}
