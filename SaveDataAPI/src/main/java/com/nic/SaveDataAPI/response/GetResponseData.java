package com.nic.SaveDataAPI.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetResponseData {
    private Object requestKey;
    private Map<String,Object> data;
}
