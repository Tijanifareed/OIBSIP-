package com.internship.oasis.oasistaskone.services.admin;


import com.internship.oasis.oasistaskone.dtos.requests.*;
import com.internship.oasis.oasistaskone.dtos.responses.*;
import com.internship.oasis.oasistaskone.services.DeleteBookResponse;

import java.io.IOException;

public interface AdminService {
    AddNewUserResponse addNewUser(AddNewUserRequest request) throws IOException;

    AddBookResponse addNewBookWith(AddBookRequest request);

    DeleteBookResponse deleteExistingBook(DeleteBookRequest request1);

    DeleteUserResponse deleteExistingUser(DeleteUserRequest request1);

    EditExistingBookResponse editExistingBook(EditExistingBookRequest request2);
    EditExistingUserResponse editExistingUser(EditExistingUserRequest request2);


}
