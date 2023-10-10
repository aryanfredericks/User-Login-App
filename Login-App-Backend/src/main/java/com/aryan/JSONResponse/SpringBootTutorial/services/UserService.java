package com.aryan.JSONResponse.SpringBootTutorial.services;

import com.aryan.JSONResponse.SpringBootTutorial.models.Login;
import com.aryan.JSONResponse.SpringBootTutorial.models.User;
import com.aryan.JSONResponse.SpringBootTutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Performs the methods mentioned in Repository
@Service
public class UserService {

    @Autowired
    UserRepository rep;

    public int registerUserService(String name , String email , String course , String password){
        return rep.registerNewUser(name, email, course, password);
    }

    public int deleteUser(String email){
        return rep.deleteUser(email);
    }

    public int updateUserService(String name ,String email,String course,String password){
        return rep.updateUser(name, email, course, password);
    }


    //Login
    public List<String> getEmails(String email){
        return rep.getEmails(email);
    }

    public String getPassword(String email){
        return rep.getPasswords(email);
    }

    public User getUserDetails(String email){
        return rep.getUser(email);
    }


}
