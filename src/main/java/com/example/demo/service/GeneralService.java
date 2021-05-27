package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface GeneralService<T, K> {
    T findById(K id);
    List<T> findAll();
    K save(T t);
}
