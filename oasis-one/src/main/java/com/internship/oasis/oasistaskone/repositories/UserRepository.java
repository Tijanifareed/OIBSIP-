package com.internship.oasis.oasistaskone.repositories;

import com.internship.oasis.oasistaskone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLibraryCardNumber(int libraryCardNumber);

}
