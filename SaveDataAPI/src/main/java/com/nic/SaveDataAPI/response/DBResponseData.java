package com.nic.SaveDataAPI.response;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DBResponseData {
    private String requestKey;
    @Type(JsonType.class)
    private Map<String,Object> resultSet;
    private Timestamp lastAccessedOn;
    private int noOfTimeAccessed;
}
