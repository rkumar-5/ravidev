package com.serverless.dao;


import com.serverless.domain.Task;

import java.util.List;
import java.util.Optional;


public interface TaskDao {

    void add(Task Task);
    void update(Task task);
    void delete(String description, Long priority);
    List<Task> findAll();
    List<Task> findAllOrdered();
    
    Optional<Task> findByDescrAndPriority(String description, Long priority);
}
