package com.serverless.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.io.Serializable;


@DynamoDBTable(tableName = "tasks")
public class Task implements Serializable, Comparable<Task> {

    private static final long serialVersionUID = -6243145427439016232L;

    @DynamoDBHashKey
    private String description;

    @DynamoDBRangeKey
    private Long priority;
    
    @DynamoDBAttribute
    private String user;
    
    @DynamoDBAttribute
    private String completed;

    public Task() { }

    public Task(String description, Long priority) {
        this.description = description;
        this.priority = priority;
    }

    public Task(String description, Long priority, String user, String completedDate) {
        this.description = description;
        this.priority = priority;
        this.user = user;
        this.completed = completedDate;
    }


    public String getTaskDescription() {
        return description;
    }

    public void setTaskDescription(String description) {
        this.description = description;
    }

    public Long getTaskPriority() {
        return priority;
    }

    public void setTaskPriority(Long eventPriority) {
        this.priority = eventPriority;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public int compareTo(Task another) {
        return this.getCompleted().compareTo(another.getCompleted());
    }
}
