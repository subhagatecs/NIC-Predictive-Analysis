package com.nic.SaveDataAPI.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseData {
    private String requestKey;
    private Map<String,Object> resultSet;
    private Timestamp lastAccessedOn;
    private int noOfTimeAccessed;
}
