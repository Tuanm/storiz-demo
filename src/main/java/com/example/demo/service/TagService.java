package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Tag;
import com.example.demo.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService implements GeneralService<Tag, String> {
    @Autowired
    TagRepository repository;

    @Override
    public Tag findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Tag> findAll() {
        return repository.findAll();
    }

    @Override
    public String save(Tag tag) {
        return repository.save(tag).name;
    }
}
