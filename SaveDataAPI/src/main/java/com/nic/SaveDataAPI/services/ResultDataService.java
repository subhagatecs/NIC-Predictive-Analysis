package com.nic.SaveDataAPI.services;

import com.nic.SaveDataAPI.entity.PredictiveAnalysisResult;
import com.nic.SaveDataAPI.repo.PredictiveAnalysisResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

@Service
public class ResultDataService {
    @Autowired
    PredictiveAnalysisResultRepo predictiveAnalysisResultRepo;

    public PredictiveAnalysisResult saveResultData(String requestKey, Map<String,Object> resultSet){

             // check if already present
            PredictiveAnalysisResult alreadyPresent = predictiveAnalysisResultRepo.findByRequestKey(requestKey);

            // update if already present
            if(!Objects.isNull(alreadyPresent)){
                alreadyPresent.setResultSet(resultSet);
                alreadyPresent.setLastAccessedOn(new Timestamp(System.currentTimeMillis()));
                alreadyPresent.setNoOfTimeAccessed(0);
                return predictiveAnalysisResultRepo.save(alreadyPresent);
            }

            // create new
            PredictiveAnalysisResult predictiveAnalysisResult = PredictiveAnalysisResult.builder()
                    .requestKey(requestKey)
                    .resultSet(resultSet)
                    .lastAccessedOn(new Timestamp(System.currentTimeMillis()))
                    .build();

            return predictiveAnalysisResultRepo.save(predictiveAnalysisResult);

    }

    public PredictiveAnalysisResult getResultData(String requestKey){

        PredictiveAnalysisResult predictiveAnalysisResult = predictiveAnalysisResultRepo.findByRequestKey(requestKey);
        if(Objects.isNull(predictiveAnalysisResult)) return null;

        predictiveAnalysisResult.setLastAccessedOn(new Timestamp(System.currentTimeMillis()));
        predictiveAnalysisResult.setNoOfTimeAccessed(predictiveAnalysisResult.getNoOfTimeAccessed()+1); // updating here
        return predictiveAnalysisResultRepo.save(predictiveAnalysisResult);
    }


}
