package com.internship.oasis.oasistaskone.services.user;

import com.internship.oasis.oasistaskone.dtos.requests.SearchForBookRequest;
import com.internship.oasis.oasistaskone.dtos.responses.SearchForBookResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testThatUserCanSearchForBook(){
        SearchForBookRequest request = new SearchForBookRequest();
        request.setBookDetail("BeautY and the beAst");
        SearchForBookResponse response = userService.searchForABookWith(request);
        System.out.println(response.getBook().toString());
        assertThat(response).isNotNull();
    }

}