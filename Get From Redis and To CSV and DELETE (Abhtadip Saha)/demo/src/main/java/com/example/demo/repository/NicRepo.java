package com.example.demo.repository;
import com.example.demo.model.Nic;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface NicRepo {
    boolean saveNic(Nic nic);

    List<Nic> fetchAllUser();

    void deleteAllUser();
}