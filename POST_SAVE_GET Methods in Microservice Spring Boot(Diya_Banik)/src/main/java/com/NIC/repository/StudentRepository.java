package com.NIC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.NIC.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
