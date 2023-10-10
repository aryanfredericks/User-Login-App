package com.aryan.JSONResponse.SpringBootTutorial.repository;

import com.aryan.JSONResponse.SpringBootTutorial.models.Login;
import com.aryan.JSONResponse.SpringBootTutorial.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    @Transactional
    @Modifying//used to indicate that a method defined in a repository interface (typically a custom query method) is intended to modify the database by executing an update, delete, or insert operation.
    @Query( value = "insert into users(name,email,course,password) values(:name,:email,:course,:password)" , nativeQuery = true)
    int registerNewUser(@Param("name") String name,
                        @Param("email") String email,
                        @Param("course") String course,
                        @Param("password") String password
                        );

    @Transactional
    @Modifying
    @Query(value="delete from users where email= :email",nativeQuery = true)
    int deleteUser(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value="update users set name= :name,course=:course,password=:password where email=:email",nativeQuery = true)
    int updateUser(@Param("name")String name,
                   @Param("email") String email,
                   @Param("course") String course,
                   @Param("password") String password
                   );

    @Query(value = "select email from users where email = :email",nativeQuery = true)
    List<String> getEmails(@Param("email") String email); // -->  [<desired Email>] size=1;

    @Query(value = "select password from users where email = :email",nativeQuery = true)
    String getPasswords(@Param("email") String email);

    @Query(value = "select * from users where email = :email",nativeQuery = true)
    User getUser(@Param("email") String email);
}
