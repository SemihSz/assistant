package com.spring.assistant.assistant.todo.service.implemantation;

import com.spring.assistant.assistant.todo.entity.SubTodoEntity;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.model.request.SubTodoRequestModel;
import com.spring.assistant.assistant.todo.repository.SubTodoRepository;
import com.spring.assistant.assistant.todo.service.SubTodoService;
import com.spring.assistant.assistant.todo.service.TodoService;
import com.spring.assistant.assistant.todo.shared.SubTodoDto;
import com.spring.assistant.assistant.todo.shared.TodoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//@Component("sub")

public class SubTodoServiceIml extends TodoServiceIml implements SubTodoService {


    @Autowired
    SubTodoRepository subTodoRepository;
    @Override
    public SubTodoDto createNewSubTodo(SubTodoRequestModel subTodoRequestModel) {

        SubTodoEntity subTodoEntity = new SubTodoEntity();
        SubTodoDto returnValue = new SubTodoDto();
        subTodoEntity.setSubTodoTitle(subTodoRequestModel.getSubTodoTitle());
        subTodoEntity.setSubTodoDescription(subTodoRequestModel.getSubTodoDescription());
        subTodoEntity.setFinished(false);
        subTodoEntity.setSubTodoCategory(subTodoRequestModel.getSubTodoCategory());
        subTodoEntity.setSubTodoCreatedDate(subTodoRequestModel.getSubTodoCreatedDate());
        subTodoEntity.setSubTodoFinishDate(subTodoRequestModel.getSubTodoFinishDate());
        subTodoEntity.setSubTodoUpdateDate(new Date());
        subTodoEntity.setTaskId(subTodoRequestModel.getTaskId());
        subTodoEntity.setUserId(subTodoRequestModel.getUserId());
        SubTodoEntity storeEntity = subTodoRepository.save(subTodoEntity);
        BeanUtils.copyProperties(storeEntity, returnValue);

        return returnValue;

    }

    @Override
    public TodoDto createNewTodo(TodoDto todo) {
        return super.createNewTodo(todo);
    }

    @Override
    public TodoDto createNewSubTodoTask(TodoDto todo) {
        return super.createNewSubTodoTask(todo);
    }

    @Override
    public TodoDto specialNewTodo(TodoDto todoDto) {
        return super.specialNewTodo(todoDto);
    }

    @Override
    public TodoDto getUser(String userId) {
        return super.getUser(userId);
    }

    @Override
    public List<TodoEntity> showTodoList() {
        return super.showTodoList();
    }

    @Override
    public List<TodoEntity> showSpecifTodoAndSubTask(String taskId) {
        return super.showSpecifTodoAndSubTask(taskId);
    }
}
