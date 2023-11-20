package com.sparta.todocard.signup;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface SignupRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUserName(String userName);

}
