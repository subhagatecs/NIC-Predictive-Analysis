package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Nic;
import com.example.demo.repository.NicRepo;

@Service
public class NicServiceImpl implements NicService {

    @Autowired
    private NicRepo nicRepo;

    @Override
    public boolean saveNic(Nic nic){
        return nicRepo.saveNic(nic);
    }

    @Override
    public List<Nic> fetchAllUser() {
        return nicRepo.fetchAllUser();
    }

    @Override
    public void deleteAllUser() {
        nicRepo.deleteAllUser();
    }
}
