package com.example.serving_web_content.service;


import com.example.serving_web_content.Entity.MaterialEntity;
import com.example.serving_web_content.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final DBRepository DBRepository;

    @Autowired
    public MaterialService(DBRepository DBRepository) {
        this.DBRepository = DBRepository;
    }

    public List<MaterialEntity> getAllItems() {
        return DBRepository.findAll();
    }

    public MaterialEntity saveItem(MaterialEntity materialEntity) {
        return DBRepository.save(materialEntity);
    }


    public MaterialEntity findById(Long id) {
        return DBRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        DBRepository.deleteById(id);
    }

}
