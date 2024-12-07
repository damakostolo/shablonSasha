package com.example.serving_web_content.service;


import com.example.serving_web_content.Entity.CryptoEntity;
import com.example.serving_web_content.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServingWebContentApplication {

    private final CryptoRepository cryptoRepository;

    @Autowired
    public ServingWebContentApplication(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public List<CryptoEntity> getAllItems() {
        return cryptoRepository.findAll();
    }

    public CryptoEntity saveItem(CryptoEntity cryptoEntity) {
        return cryptoRepository.save(cryptoEntity);
    }

    public void deleteBook(Long id) {
        cryptoRepository.deleteById(id);
    }

    public CryptoEntity findById(Long id) {
        return cryptoRepository.findById(id).orElse(null);
    }
}
