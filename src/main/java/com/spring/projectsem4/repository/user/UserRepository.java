package com.spring.projectsem4.repository.user;

import com.spring.projectsem4.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    @Query(value = "SELECT * FROM users WHERE users.user_id =: id", nativeQuery = true)
    Optional<User> findById(@Param("user_id") Long id);

    @Query(value = "SELECT * FROM users WHERE users.username =: username OR users.email =: email", nativeQuery = true)
    Optional<User> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

    @Query(value = "SELECT u FROM User u")
    List<User> findAllUser(Sort sort);

    @Query(value = "SELECT * FROM users WHERE users.email =: email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "SELECT CASE WHEN count(u) > 0 THEN true ELSE false END FROM User u WHERE u.username =: username")
    Boolean existsByUsername(@Param("username") String username);

    @Query(value = "SELECT CASE WHEN count(u) > 0 THEN true ELSE false END FROM User u WHERE u.email =: email")
    Boolean existsByEmail(@Param("email") String email);
}
