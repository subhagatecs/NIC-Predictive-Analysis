package com.nic.SaveDataAPI.controller;

import com.nic.SaveDataAPI.response.GetResponseData;
import com.nic.SaveDataAPI.response.PostResponseData;
import com.nic.SaveDataAPI.services.ExternalDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nic.SaveDataAPI.entity.PredictiveAnalysisResult;
import com.nic.SaveDataAPI.request.PostRequestData;
import com.nic.SaveDataAPI.services.ResultDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private ResultDataService resultDataService;
    @Autowired
    private ExternalDataService externalDataService;


    // save data in postgres db
    @PostMapping("data/save")
    public ResponseEntity saveData(@RequestBody PostRequestData postRequestData){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String resultSetString = mapper.writeValueAsString(postRequestData.getResultSet());
            PredictiveAnalysisResult savedResult = resultDataService.saveResultData(postRequestData.getRequestKey(),resultSetString);
            return new ResponseEntity<>(PostResponseData.builder()
                    .requestKey(savedResult.getRequestKey())
                    .resultSet(postRequestData.getResultSet())
                    .noOfTimeAccessed(savedResult.getNoOfTimeAccessed())
                    .lastAccessedOn(savedResult.getLastAccessedOn()).build(),HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Exception is Post Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // fetch data from external endpoint (python application)
    @GetMapping("fetch/{requestKey}")
    public ResponseEntity getData(@PathVariable String requestKey){
        try{
            // check for the key in Redis - if not present return
            String fetchedData = externalDataService.fetchData(requestKey);
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> parsedData = mapper.readValue(fetchedData,Map.class);
            // may save directly to postgres db
            return new ResponseEntity<>(GetResponseData.builder().requestKey(requestKey).data(parsedData).build(),HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Exception in fetching service",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get Data from postgres DB
    @GetMapping("data/{requestKey}")
    public ResponseEntity getDataFromDB(@PathVariable String requestKey){
        try{

            PredictiveAnalysisResult fetchedData = resultDataService.getResultData(requestKey);

            // check if null
            if(Objects.isNull(fetchedData)){
                return new ResponseEntity<>("No saved data found",HttpStatus.BAD_REQUEST);
            }
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> parsedData = mapper.readValue(fetchedData.getResultSet(),Map.class);


            return new ResponseEntity<>(PostResponseData.builder()
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
