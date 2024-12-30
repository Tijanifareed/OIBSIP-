package com.internship.oasis.oasistaskone.services.admin;


import com.internship.oasis.oasistaskone.dtos.requests.*;
import com.internship.oasis.oasistaskone.dtos.responses.AddBookResponse;
import com.internship.oasis.oasistaskone.dtos.responses.AddNewUserResponse;
import com.internship.oasis.oasistaskone.dtos.responses.DeleteUserResponse;
import com.internship.oasis.oasistaskone.dtos.responses.EditBookResponse;
import com.internship.oasis.oasistaskone.entities.Book;
import com.internship.oasis.oasistaskone.entities.User;
import com.internship.oasis.oasistaskone.entities.UserRole;
import com.internship.oasis.oasistaskone.exceptions.BookNotFoundExeption;
import com.internship.oasis.oasistaskone.exceptions.EmailAlreadyExists;
import com.internship.oasis.oasistaskone.exceptions.PhoneNumberAlreadyExists;
import com.internship.oasis.oasistaskone.repositories.BookRepository;
import com.internship.oasis.oasistaskone.repositories.UserRepository;
import com.internship.oasis.oasistaskone.services.DeleteBookResponse;
import com.internship.oasis.oasistaskone.services.image.ImageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ImageService imageService;


    @Override
    public AddNewUserResponse addNewUser(AddNewUserRequest request) throws IOException {
        validateRequest(request);
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmailAddress(request.getEmailAddress());
        user.setLibraryCardNumber(generateLibraryNumber());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setHomeAddress(request.getHomeAddress());
        user.setUserRole(UserRole.CUSTOMER);

//        String profilePictureUrl = imageService.uploadImage(request.getProfilePicture());
//        user.setProfilePicture(profilePictureUrl);
        userRepository.save(user);
        AddNewUserResponse response = new AddNewUserResponse();
        response.setMessage("New User Created Successfully");
        response.setLibraryNumber(user.getLibraryCardNumber());
        return response;
    }

    @Override
    public AddBookResponse addNewBookWith(AddBookRequest request) {
        Book book = new Book();
        book.setTitle(request.getBookTitle());
        book.setAuthor(request.getAuthor());
        book.setTotalNumber(request.getTotalNumber());
        book.setBookCategory(request.getBookCategory());
        book.setAvaliableCopies(request.getTotalNumber());
        book.setPublicationDate(request.getPublicationDate());
        bookRepository.save(book);
        AddBookResponse response = new AddBookResponse();
        response.setBookId(book.getBookId());
        response.setMessage("New Book added successfully");
        return response;
    }

    @Override
    public DeleteBookResponse deleteExistingBook(DeleteBookRequest request1) {
        List<Book> books = bookRepository.findAll();
        for (Book book : books)if (book.getBookId() == request1.getBookId()) {
                books.remove(book);
                bookRepository.saveAll(books);
                DeleteBookResponse response = new DeleteBookResponse();
                response.setMessage("Book successfully deleted");
                return response;
                                }

        throw new BookNotFoundExeption("Book not found. Check the spelling.");

    }

    @Override
    public DeleteUserResponse deleteExistingUser(DeleteUserRequest request1) {
        List<User> users = userRepository.findAll();
        for (User user : users)if (user.getUserId() == request1.getUserId()) {
            users.remove(user);
            userRepository.saveAll(users);
            DeleteUserResponse response = new DeleteUserResponse();
            response.setMessage("User successfully deleted");
            return response;
        }

        throw new BookNotFoundExeption("User not found. Check the spelling.");

    }


    public void validateRequest(AddNewUserRequest request){
        User user1 = userRepository.findUserByEmailAddress(request.getEmailAddress());
        User user = userRepository.findUserByPhoneNumber(request.getPhoneNumber());
        if(user1 != null)throw new EmailAlreadyExists("A user with this email address already exists");
        else if(user != null) throw new PhoneNumberAlreadyExists("A user with this phone number already exists");
    }

    public int generateLibraryNumber(){
        Random random = new Random();
        int randomNumber = 1000 * random.nextInt(9000);
        User user = userRepository.findUserByLibraryCardNumber(randomNumber);
        if(user != null){
            generateLibraryNumber();
        }
        return randomNumber;

    }

}
