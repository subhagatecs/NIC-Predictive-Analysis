package com.NIC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stuid;
	
	@Column(name="stu_name")
	private String stu_name;
	
	@Column(name="stu_salary")
	private Float stu_salary;
	
	@Column(name="stu_age")
	private int stu_age;
	
	@Column(name="stu_city")
	private String stu_city;

	public Long getStuid() {
		return stuid;
	}

	public void setStuid(Long stuid) {
		this.stuid = stuid;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public Float getStu_salary() {
		return stu_salary;
	}

	public void setStu_salary(Float stu_salary) {
		this.stu_salary = stu_salary;
	}

	public int getStu_age() {
		return stu_age;
	}

	public void setStu_age(int stu_age) {
		this.stu_age = stu_age;
	}

	public String getStu_city() {
		return stu_city;
	}

	public void setStu_city(String stu_city) {
		this.stu_city = stu_city;
	}

	public Student(Long stuid, String stu_name, Float stu_salary, int stu_age, String stu_city) {
		super();
		this.stuid = stuid;
		this.stu_name = stu_name;
		this.stu_salary = stu_salary;
		this.stu_age = stu_age;
		this.stu_city = stu_city;
	}
	
	public Student() {
		
	}

	@Override
	public String toString() {
		return "Student [stuid=" + stuid + ", stu_name=" + stu_name + ", stu_salary=" + stu_salary + ", stu_age="
				+ stu_age + ", stu_city=" + stu_city + "]";
	}
	
	
}
