package com.nic.SaveDataAPI.repo;

import com.nic.SaveDataAPI.entity.PredictiveAnalysisResult;
import org.springframework.data.repository.CrudRepository;

public interface PredictiveAnalysisResultRepo extends CrudRepository<PredictiveAnalysisResult,Long> {
    public PredictiveAnalysisResult findByRequestKey(String requestKey);
}
