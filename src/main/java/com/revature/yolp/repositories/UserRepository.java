package com.revature.yolp.repositories;

import com.revature.yolp.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "SELECT username FROM users WHERE username = ?1", nativeQuery = true)
    String findUsernameByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User findUserByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE username = ?1 AND password = ?2", nativeQuery = true)
    User findUserByUsernameAndPassword(String username, String password);
}
