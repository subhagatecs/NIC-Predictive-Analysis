package com.NIC.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NIC.model.Student;
import com.NIC.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentController {

@Autowired
StudentRepository studentRepository;

@PostMapping("/student")
public String createNewStudent(@RequestBody Student student) {
	studentRepository.save(student);
	return "Student Details Saved";
}

@GetMapping("/student")
public ResponseEntity<List<Student>> getAllStudents(){
	List<Student> stuList=new ArrayList<>();
	studentRepository.findAll().forEach(stuList::add);
	return new ResponseEntity<List<Student>>(stuList,HttpStatus.OK);
}
}
