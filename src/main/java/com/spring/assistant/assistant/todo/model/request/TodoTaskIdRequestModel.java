package com.spring.assistant.assistant.todo.model.request;

public class TodoTaskIdRequestModel extends TodoRequestModel {

    private String taskId;
    private Boolean isFinnished;
    public String getTaskId() {
        return taskId;
    }


    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getFinnished() {
        return isFinnished;
    }

    public void setFinnished(Boolean finnished) {
        isFinnished = finnished;
    }
}
