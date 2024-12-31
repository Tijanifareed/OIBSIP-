package com.internship.oasis.oasistaskone.services.user;

import com.internship.oasis.oasistaskone.dtos.ReturnBookResponse;
import com.internship.oasis.oasistaskone.dtos.requests.BrowseBooksRequest;
import com.internship.oasis.oasistaskone.dtos.requests.ReturnBookRequest;
import com.internship.oasis.oasistaskone.dtos.requests.SearchForBookRequest;
import com.internship.oasis.oasistaskone.dtos.responses.BorrowBookRequest;
import com.internship.oasis.oasistaskone.dtos.responses.BorrowBookResponse;
import com.internship.oasis.oasistaskone.dtos.responses.BrowseBooksResponse;
import com.internship.oasis.oasistaskone.dtos.responses.SearchForBookResponse;

public interface UserService {

    SearchForBookResponse searchForABookWith(SearchForBookRequest request);

    BrowseBooksResponse browseBookByCategory(BrowseBooksRequest request);

    BorrowBookResponse borrowBook(BorrowBookRequest request1);

    ReturnBookResponse returnBook(ReturnBookRequest request3);
}
