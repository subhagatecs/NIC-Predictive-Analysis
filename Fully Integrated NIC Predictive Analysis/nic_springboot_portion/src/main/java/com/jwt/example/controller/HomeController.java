package com.jwt.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.example.entities.PredictiveAnalysisResult;
import com.jwt.example.entities.User;
import com.jwt.example.models.GetResponseData;
import com.jwt.example.services.ResultDataService;
import com.jwt.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResultDataService resultDataService;

  //we need to secure the path
    //http://localhost:8081/home/users

    @GetMapping("/users")
    public List<User> getUser(){
        System.out.println("getting users");

        return userService.getUsers();
    }


    @GetMapping ("/current-user")
    public  String getLoggedInUser(Principal principal ){

        return principal.getName();
    }

    @GetMapping("data/{requestKey}")
    public ResponseEntity getDataFromDB(@PathVariable String requestKey){
        try{

            PredictiveAnalysisResult fetchedData = resultDataService.getResultData(requestKey);

            if(Objects.isNull(fetchedData)){
                return new ResponseEntity<>("No saved data found", HttpStatus.BAD_REQUEST);
            }
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> parsedData = mapper.readValue(fetchedData.getResultSet(),Map.class);


            return new ResponseEntity<>(GetResponseData.builder()
                    .requestKey(fetchedData.getRequestKey())
                    .resultSet(parsedData)
                    .noOfTimeAccessed(fetchedData.getNoOfTimeAccessed())
                    .lastAccessedOn(fetchedData.getLastAccessedOn()).build(),HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Exception in Database fetching service",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
