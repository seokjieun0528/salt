package com.example.salt.service;

import com.example.salt.dto.NewsDTO;
import com.example.salt.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repository;

    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    public long getRowCount(){
        return repository.countRows();
    }

    public NewsDTO selectById(int selectId) {
        return repository.findById(selectId)
                .map(NewsDTO::new)
                .orElse(null);
    }
}
