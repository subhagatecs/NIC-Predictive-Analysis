package com.nic.SaveDataAPI.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostRequestData {
    private String requestKey;
    private Map<String,Object> resultSet;
}
