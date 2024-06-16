package com.nic.SaveDataAPI.response;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FetchedResponseData {
    private String requestKey;
    @Type(JsonType.class)
    private Map<String,Object> data;
}
