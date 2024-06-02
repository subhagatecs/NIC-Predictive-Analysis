package com.example.demo.controller;
import com.example.demo.model.Nic;
import com.example.demo.service.NicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NicController {

    @Autowired
    private NicService nicService;

    @PostMapping("/nic")
    public ResponseEntity<String> saveNic(@RequestBody Nic nic){
        boolean result = nicService.saveNic(nic);
        if(result)
            return ResponseEntity.ok("Data Pushed To Redis");
        else
            return ResponseEntity.status(HttpStatus.valueOf(0)).build();
    }
}

