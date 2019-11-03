package com.spring.assistant.assistant.todo.model.request;

public class TodoTaskIdRequestModel extends TodoRequestModel {

    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
