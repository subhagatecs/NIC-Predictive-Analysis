package com.example.demo.service;
import com.example.demo.model.Nic;
import java.util.List;
public interface NicService {
    boolean saveNic(Nic nic);

    List<Nic> fetchAllUser();

    void deleteAllUser();
}
