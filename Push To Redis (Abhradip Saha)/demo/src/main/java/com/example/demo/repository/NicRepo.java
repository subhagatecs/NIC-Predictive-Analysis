package com.example.demo.repository;
import com.example.demo.model.Nic;
import org.springframework.stereotype.Repository;

@Repository
public interface NicRepo {
    boolean saveNic(Nic nic);
}