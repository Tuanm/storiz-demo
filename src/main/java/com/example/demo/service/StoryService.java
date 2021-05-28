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
        List<Story> stories = repository.findAll();
        stories.sort((s1, s2) -> -s1.postAt.compareTo(s2.postAt));
        return stories;
    }

    @Override
    public Integer save(Story story) {
        return repository.save(story).id;
    }
}
