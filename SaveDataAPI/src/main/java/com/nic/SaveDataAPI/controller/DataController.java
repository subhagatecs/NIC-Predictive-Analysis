package com.nic.SaveDataAPI.controller;

import com.nic.SaveDataAPI.response.FetchedResponseData;
import com.nic.SaveDataAPI.response.DBResponseData;
import com.nic.SaveDataAPI.services.ExternalDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nic.SaveDataAPI.entity.PredictiveAnalysisResult;
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
    @Autowired
    private ObjectMapper mapper;

    // fetch data from external endpoint (python application)
    @GetMapping("fetch/{requestKey}")
    public ResponseEntity getData(@PathVariable String requestKey){
        try{
            // check for the key in Redis - if not present return
            String fetchedData = externalDataService.fetchData(requestKey);
            Map<String,Object> parsedData = mapper.readValue(fetchedData,Map.class);
            // may save directly to postgres db
            PredictiveAnalysisResult savedResult = resultDataService.saveResultData(requestKey,parsedData);
            return new ResponseEntity<>(FetchedResponseData.builder().requestKey(requestKey).data(parsedData).build(),HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Exception in fetching the processed data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get Data from postgres DB
    @GetMapping("data/{requestKey}")
    public ResponseEntity getDataFromDB(@PathVariable String requestKey){
        try{
            PredictiveAnalysisResult fetchedData = resultDataService.getResultData(requestKey);
            if(Objects.isNull(fetchedData)){
                return new ResponseEntity<>("No saved data found",HttpStatus.BAD_REQUEST);
            }

            Map<String,Object> parsedData =  fetchedData.getResultSet();

            return new ResponseEntity<>(DBResponseData.builder()
                    .requestKey(fetchedData.getRequestKey())
                    .resultSet(parsedData)
                    .noOfTimeAccessed(fetchedData.getNoOfTimeAccessed())
                    .lastAccessedOn(fetchedData.getLastAccessedOn()).build(),HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Exception in DB service",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
