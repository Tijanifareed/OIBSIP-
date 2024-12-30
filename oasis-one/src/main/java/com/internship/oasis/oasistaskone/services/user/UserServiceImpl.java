package com.internship.oasis.oasistaskone.services.user;


import com.internship.oasis.oasistaskone.dtos.requests.SearchForBookRequest;
import com.internship.oasis.oasistaskone.dtos.responses.SearchForBookResponse;
import com.internship.oasis.oasistaskone.entities.Book;
import com.internship.oasis.oasistaskone.exceptions.BookNotFoundExeption;
import com.internship.oasis.oasistaskone.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private  BookRepository bookRepository;

    public UserServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public SearchForBookResponse searchForABookWith(SearchForBookRequest request) {
        List<Book> books = bookRepository.findAll();
        System.out.println(books.toString());
        List<Book> filteredBook = new ArrayList<>();
        for(Book book : books) {
            if (book.getTitle().equalsIgnoreCase(request.getBookDetail()) || book.getAuthor().equalsIgnoreCase(request.getBookDetail()))
                filteredBook.add(book);
        }
        if(filteredBook.isEmpty())throw new BookNotFoundExeption("we dont have this book check again later");
        SearchForBookResponse response = new SearchForBookResponse();
        response.setBook(filteredBook);
        return response;
    }
}
