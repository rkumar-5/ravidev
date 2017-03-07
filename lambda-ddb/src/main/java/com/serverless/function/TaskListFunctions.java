package com.serverless.function;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.serverless.dao.DynamoDBTaskDao;
import com.serverless.util.Consts;
import org.apache.log4j.Logger;

import com.serverless.domain.Task;


public class TaskListFunctions {

    private static final Logger log = Logger.getLogger(TaskListFunctions.class);

    private static final DynamoDBTaskDao taskDao = DynamoDBTaskDao.instance();


    public List<Task> getAllTasksHandler() {

        log.info("GetAllTasks invoked to scan table for ALL tasks");
        List<Task> tasks = taskDao.findAll();
        log.info("Found " + tasks.size() + " total tasks.");
        return tasks;
    }

    public List<Task> getAllTasksOrdered() {

        log.info("GetAllTasksOrdered invoked to scan table for ALL tasks");
        List<Task> tasks = taskDao.findAllOrdered();
        log.info("Found " + tasks.size() + " total tasks.");
        return tasks;
    }
    
     
    public void addTask(Task task) {

        if (null == task) {
            log.error("addTask received null input");
            throw new IllegalArgumentException("Cannot add null object");
        }

        log.info("Adding a task = " + task.getTaskDescription() + " , priority = " + task.getTaskPriority());
        taskDao.add(task);
        log.info("Successfully saved/updated task");
    }

    public void updateTask(Task task) {

        if (null == task) {
            log.error("SaveTask received null input");
            throw new IllegalArgumentException("Cannot save null object");
        }

        log.info("Saving or updating task for team = " + task.getTaskDescription() + " , completed date = " + task.getCompleted());
        taskDao.update(task);
        log.info("Successfully saved/updated task");
    }

    public void deleteTask(Task task) {

        if (null == task) {
            log.error("DeleteTask received null input");
            throw new IllegalArgumentException("Cannot delete null object");
        }

        log.info("Deleting task for team = " + task.getTaskDescription() + " , date = " + task.getTaskPriority());
        taskDao.delete(task.getTaskDescription(), task.getTaskPriority());
        log.info("Successfully deleted task");
    }
}
