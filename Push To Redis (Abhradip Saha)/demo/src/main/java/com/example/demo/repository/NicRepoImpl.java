package com.example.demo.repository;
import com.example.demo.model.Nic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NicRepoImpl implements NicRepo{
    
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean saveNic(Nic nic){
        try{
            redisTemplate.opsForHash().put("KEY", nic.getKey() , nic);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
