package com.example.demo.model;
import lombok.Data;
import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("KEY")
public class Nic implements Serializable{
    private String category;

    private String sub_category;

    private int expected_period_in_month;

    private int out_data_pattern;

    private String demand_date;
 
    private int demand_qty;

    private long analysis_year;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}

	public int getExpected_period_in_month() {
		return expected_period_in_month;
	}

	public void setExpected_period_in_month(int expected_period_in_month) {
		this.expected_period_in_month = expected_period_in_month;
	}

	public int getOut_data_pattern() {
		return out_data_pattern;
	}

	public void setOut_data_pattern(int out_data_pattern) {
		this.out_data_pattern = out_data_pattern;
	}

	public String getDemand_date() {
		return demand_date;
	}

	public void setDemand_date(String demand_date) {
		this.demand_date = demand_date;
	}

	public int getDemand_qty() {
		return demand_qty;
	}

	public void setDemand_qty(int demand_qty) {
		this.demand_qty = demand_qty;
	}

	public long getAnalysis_year() {
		return analysis_year;
	}

	public void setAnalysis_year(long analysis_year) {
		this.analysis_year = analysis_year;
	}

	public Nic(String category, String sub_category, int expected_period_in_month, int out_data_pattern,
			String demand_date, int demand_qty, long analysis_year) {
		super();
		this.category = category;
		this.sub_category = sub_category;
		this.expected_period_in_month = expected_period_in_month;
		this.out_data_pattern = out_data_pattern;
		this.demand_date = demand_date;
		this.demand_qty = demand_qty;
		this.analysis_year = analysis_year;
	}
    
    


}
