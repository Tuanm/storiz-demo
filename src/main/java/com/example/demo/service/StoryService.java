package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Story;
import com.example.demo.repository.StoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryService implements GeneralService<Story, Integer> {
    @Autowired
    StoryRepository repository;

    @Override
    public Story findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Story> findAll() {
        return repository.findAll();
    }

    @Override
    public Integer save(Story story) {
        return repository.save(story).id;
    }
}
