package com.example.demo.repository;

import com.example.demo.model.Nic;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NicRepoImpl implements NicRepo{
    
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String KEY = UUID.randomUUID().toString() + "27201" + Integer.toString((int) (Math.random() * 900) + 100);
    @Override
    public boolean saveNic(Nic nic){
        try{
            redisTemplate.opsForHash().put("KEY", KEY, nic);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
