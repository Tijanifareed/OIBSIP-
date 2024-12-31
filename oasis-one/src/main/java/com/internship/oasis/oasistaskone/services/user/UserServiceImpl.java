package com.internship.oasis.oasistaskone.services.user;


import com.internship.oasis.oasistaskone.dtos.ReturnBookResponse;
import com.internship.oasis.oasistaskone.dtos.requests.BrowseBooksRequest;
import com.internship.oasis.oasistaskone.dtos.requests.ReturnBookRequest;
import com.internship.oasis.oasistaskone.dtos.requests.SearchForBookRequest;
import com.internship.oasis.oasistaskone.dtos.responses.BorrowBookRequest;
import com.internship.oasis.oasistaskone.dtos.responses.BorrowBookResponse;
import com.internship.oasis.oasistaskone.dtos.responses.BrowseBooksResponse;
import com.internship.oasis.oasistaskone.dtos.responses.SearchForBookResponse;
import com.internship.oasis.oasistaskone.entities.Book;
import com.internship.oasis.oasistaskone.entities.Loans;
import com.internship.oasis.oasistaskone.entities.User;
import com.internship.oasis.oasistaskone.exceptions.AllFieldsRequiredException;
import com.internship.oasis.oasistaskone.exceptions.BookNotFoundExeption;
import com.internship.oasis.oasistaskone.repositories.BookRepository;
import com.internship.oasis.oasistaskone.repositories.LoanRepository;
import com.internship.oasis.oasistaskone.repositories.UserRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private  BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;

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

    @Override
    public BrowseBooksResponse browseBookByCategory(BrowseBooksRequest request) {
        List<Book> books = bookRepository.findBookByBookCategory(request.getBookCategory());
        BrowseBooksResponse response = new BrowseBooksResponse();
        response.setBooks(books);
        return response;
    }

    @Override
    public BorrowBookResponse borrowBook(BorrowBookRequest request1) {
        if(request1.getLiabaryNumber() == 0 || request1.getBookId() == null) throw new AllFieldsRequiredException("All fields are required for this process");
        Optional<Book> book = bookRepository.findById(request1.getBookId());
        User user = userRepository.findUserByLibraryCardNumber(request1.getLiabaryNumber());
        Loans loan = new Loans();
        if(book.get().getAvaliableCopies() < 1) throw new BookNotFoundExeption("Out of stock please try again later or try borrow another book");
        loan.setBookId(book.get().getBookId());
        loan.setUserId(user.getUserId());
        loan.setBorrowDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusWeeks(3));
        loan.setBookStatus("Borrowed");
        book.get().setAvaliableCopies(book.get().getAvaliableCopies() - 1);
        bookRepository.save(book.get());
        Loans l =  loanRepository.save(loan);
        BorrowBookResponse response = new BorrowBookResponse();
        response.setLoanId(l.getLoanId());
        response.setMessage("Thanks for borrowing this book Proceed to the librarian to collect The book");

        return response;
    }

    @Override
    public ReturnBookResponse returnBook(ReturnBookRequest request3) {
        Optional<Loans> loan = loanRepository.findById(request3.getLoanId());
        Optional<Book> book = bookRepository.findById(loan.get().getBookId());
        loan.ifPresent(loans -> loans.setBookStatus("Returned"));
        loan.ifPresent(loans -> loans.setTimeReturned(LocalDateTime.now()));
        book.get().setAvaliableCopies(book.get().getAvaliableCopies() + 1);
        ReturnBookResponse response = new ReturnBookResponse();
        response.setMessage("Book returned successfully");
        return response;
    }


}
