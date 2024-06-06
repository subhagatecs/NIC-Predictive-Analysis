package com.example.demo.model;
import lombok.Data;
import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("KEY")
public class Nic implements Serializable{
    private String key=UUID.randomUUID().toString() + "27201" + ((int) (Math.random() * 900) + 100);

    private String category;

    private String sub_category;

    private int expected_period_in_month;

    private int out_data_pattern;

    private String demand_date;
 
    private int demand_qty;

    private long analysis_year;
}
