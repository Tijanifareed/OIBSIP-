package com.internship.oasis.oasistaskone.services.user;

import com.internship.oasis.oasistaskone.dtos.requests.SearchForBookRequest;
import com.internship.oasis.oasistaskone.dtos.responses.SearchForBookResponse;

public interface UserService {

    SearchForBookResponse searchForABookWith(SearchForBookRequest request);
}
