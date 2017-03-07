package com.serverless.dao;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.serverless.domain.Task;
import com.serverless.manager.DynamoDBManager;
import org.apache.log4j.Logger;

import java.util.*;


public class DynamoDBTaskDao implements TaskDao {

    private static final Logger log = Logger.getLogger(DynamoDBTaskDao.class);

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    private static volatile DynamoDBTaskDao instance;


    private DynamoDBTaskDao() { }

    public static DynamoDBTaskDao instance() {

        if (instance == null) {
            synchronized(DynamoDBTaskDao.class) {
                if (instance == null)
                    instance = new DynamoDBTaskDao();
            }
        }
        return instance;
    }
    
    @Override
    public void add(Task Task) {

        mapper.save(Task);
    }
            
    @Override
    public void update(Task Task) {

        mapper.save(Task);
    }
    
    @Override
    public void delete(String description, Long priority) {

        Optional<Task> oTask = findByDescrAndPriority(description, priority);
        if (oTask.isPresent()) {
            mapper.delete(oTask.get());
        }
        else {
            log.error("Could not delete Task, no such team and date combination");
            throw new IllegalArgumentException("Delete failed for nonexistent Task");
        }
    }

    @Override
    public List<Task> findAll() {
        return mapper.scan(Task.class, new DynamoDBScanExpression());
    }

    	    
    @Override
    public List<Task> findAllOrdered() {
        List<Task> allTasks = findAll();

        // sort by Completion - list already ordered by priority.
        Collections.sort(allTasks);

        return allTasks;
    }

    @Override
    public Optional<Task> findByDescrAndPriority(String description, Long priority) {

        Task task = mapper.load(Task.class, description, priority);

        return Optional.ofNullable(task);
    }

}
