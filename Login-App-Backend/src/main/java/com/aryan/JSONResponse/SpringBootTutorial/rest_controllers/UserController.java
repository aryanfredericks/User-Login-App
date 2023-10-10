package com.aryan.JSONResponse.SpringBootTutorial.rest_controllers;

import com.aryan.JSONResponse.SpringBootTutorial.models.Login;
import com.aryan.JSONResponse.SpringBootTutorial.models.User;
import com.aryan.JSONResponse.SpringBootTutorial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/test")
    public String testEndpoint(){
        return "Working";
    }


    @PostMapping("/user/register")
    public ResponseEntity registerUser(@RequestParam("name") String name,
                                       @RequestParam("email") String email,
                                       @RequestParam("course") String course,
                                       @RequestParam("password") String password
    ){

        System.out.println("Received parameters: name=" + name + ", email=" + email + ", course=" + course + ", password=" + password);
        //Checks
        if (name.isEmpty()||email.isEmpty()||course.isEmpty()||password.isEmpty()){
            return new ResponseEntity<>("Please Fill Correct Values", HttpStatus.BAD_REQUEST);
        }
        //Encrypting the password
        String hashed_password = BCrypt.hashpw(password,BCrypt.gensalt());
        //Register new User
        int register = service.registerUserService(name, email, course, hashed_password);
        if (register==0){
            return new ResponseEntity<>("Error Occured", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>("Registered User", HttpStatus.OK);
        }
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity deleteUser(@RequestParam("email") String email){
        if (email.isEmpty()){
            return  new ResponseEntity<>("Email cannot be empty",HttpStatus.BAD_REQUEST);
        }
        System.out.println("Email = "+email);

        int delete = service.deleteUser(email);
        if (delete==0){
            return new ResponseEntity<>("Failed to delete\nUser Might not exist",HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>("Deleted User Successfully",HttpStatus.OK);
        }
    }

    @PutMapping("/user/update")
    public ResponseEntity updateUser(@RequestParam("name") String name,
                                     @RequestParam("email") String email,
                                     @RequestParam("course") String course,
                                     @RequestParam("password") String password
                                     ){
        System.out.println("Received parameters: name=" + name + ", email=" + email + ", course=" + course + ", password=" + password);
        //Checks
        if (name.isEmpty()||email.isEmpty()||course.isEmpty()||password.isEmpty()){
            return new ResponseEntity<>("Please Fill Correct Values", HttpStatus.BAD_REQUEST);
        }

        String hashedPass = BCrypt.hashpw(password,BCrypt.gensalt());

        int update=service.updateUserService(name, email, course, hashedPass);
        if (update==0){
            return new ResponseEntity<>("Failed to update\nUser Might not Exist",HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>("Updated User Successfully",HttpStatus.OK);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity loginUser(@RequestBody Login login){ //request-->Json Object
        List<String> email = service.getEmails(login.getEmail());
        //email = [example@gmail.com];
        if(email.isEmpty()|| email==null){
            return new ResponseEntity<>("Email Doesn't Exist",HttpStatus.NOT_FOUND);
        }
        String hashed = service.getPassword(login.getEmail()); //Returning the hashed value of the password which is stored in the db
        if (!BCrypt.checkpw(login.getPassword(),hashed)){
            return new ResponseEntity<>("Incorrect Password",HttpStatus.BAD_REQUEST);
        }
        User user = service.getUserDetails(login.getEmail());
        //returning user as JSON object
        return new ResponseEntity<>(user , HttpStatus.OK);
    }
}