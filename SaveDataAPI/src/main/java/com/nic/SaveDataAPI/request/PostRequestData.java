package com.nic.SaveDataAPI.request;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostRequestData {
    private String requestKey;
    @Type(JsonType.class)
    private Map<String,Object> resultSet;
}
