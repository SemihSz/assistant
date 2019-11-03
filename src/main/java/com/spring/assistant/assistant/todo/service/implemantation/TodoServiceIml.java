package com.spring.assistant.assistant.todo.service.implemantation;

import com.spring.assistant.assistant.todo.Enum;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.repository.TodoRepository;
import com.spring.assistant.assistant.todo.service.TodoService;
import com.spring.assistant.assistant.todo.shared.TodoDto;
import com.spring.assistant.assistant.todo.shared.utils.GenerateNumberUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TodoServiceIml implements TodoService {

    private final int TASK_RANDOM_NUMBER = 15;
    private final int MAX = 100;
    private final int MIN =10;


    @Autowired
    TodoRepository todoRepository;
    @Autowired
    GenerateNumberUtil generateNumberUtil;


    @Override
    public TodoDto createNewTodo(TodoDto todo) {
        TodoEntity entityTodoEntity = new TodoEntity();
        TodoDto returnValue = new TodoDto();
        String userId = null;
        BeanUtils.copyProperties(todo, entityTodoEntity);
        if (controlTheTitleOfTodo(todo.getTitle())){
            if (sizeOfTitle(todo.getTitle())){
                if (descLenght(todo.getDescription())){
                    if (!todo.getUserId().isEmpty() && todo.getUserId().equals("")){
                        userId = todo.getUserId();
                    }
                    else {
                         userId = stringRandom(10);
                    }

                    String taskId = generateRandomTaskId();
                    //String taskId = "k8pdhzNYrOGs9Jm";
                    if (todoRepository.findByTaskId(taskId) !=null){
                        String newTaskId = generateRandomTaskId();
                        if (!newTaskId.equals(taskId)){
                            entityTodoEntity.setTaskId(newTaskId);
                        }
                    }
                    else {
                        entityTodoEntity.setTaskId(taskId);
                    }

                    entityTodoEntity.setUserId(userId);
                    entityTodoEntity.setCreatedDate(new Date());
                    entityTodoEntity.setUpdatedDate(new Date());
                    entityTodoEntity.setExpectFinishDate(new Date());
                    TodoEntity storeTodos =  todoRepository.save(entityTodoEntity);
                    BeanUtils.copyProperties(storeTodos,returnValue);
                }
                else {
                    showRunTimeMessag("Your description is so short");
                }
            }
        }else{
            showRunTimeMessag("Your title is so short");
        }

        return returnValue;
    }

    @Override
    public TodoDto createNewSubTodoTask(TodoDto todo) {

        TodoEntity todoEntity = new TodoEntity();
        TodoDto retrunValue = new TodoDto();
        BeanUtils.copyProperties(todo, todoEntity);
        String userId = null;
        //TODO user id session tututp oradan almayı dene

        if (controlTheTitleOfTodo(todo.getTitle()) && sizeOfTitle(todo.getTitle())){

            if (descLenght(todo.getDescription())){
                if (todoRepository.findByUserId(todo.getUserId())!=null){
                    userId = todo.getUserId();
                }

            }

        }



        return null;
        //TODO Bİr kullanıcının birden fazla todo olabilir.
    }

    @Override
    public TodoDto specialNewTodo(TodoDto todoDto) {
        TodoEntity entityTodoEntity = new TodoEntity();
        TodoDto returnValue = new TodoDto();
        BeanUtils.copyProperties(todoDto, entityTodoEntity);
        TodoEntity storeTodos =  todoRepository.save(entityTodoEntity);
        BeanUtils.copyProperties(storeTodos,returnValue);
        return returnValue;
    }

    @Override
    public TodoDto getUser(String userId) {
        TodoEntity entity =  todoRepository.findByTaskId(userId);
        TodoDto todoDto = new TodoDto();
        BeanUtils.copyProperties(entity, todoDto);
        return todoDto;
    }

    private boolean controlTheTitleOfTodo(String title){
        return !title.isEmpty();
    }

    private boolean sizeOfTitle(String title){
        return title.length() > 5;
    }

    private String stringRandom(int lengt){
        String s = generateNumberUtil.generateUserId(lengt);
        return s;
    }

    private boolean descLenght(String desc){
        return desc.length() >= MIN && MAX >= desc.length();
    }

    private String generateRandomTaskId(){
        return (generateNumberUtil.generateUserId(TASK_RANDOM_NUMBER));

    }

    private void showRunTimeMessag(String message){
        throw new RuntimeException(message);
    }
    //TODO Service'leri ekle
    //TODO Sadece bir kullancı için todo'lar create etme.
    //TODO Her Kategoriye göre tablolar yaratılması ve tablolara bunlara özel todoların eklenmesi. TaskId joincolumn yapacak category tablosuna
    //TODO Bu saye örnek kullanıcı kendisine özel kategori yaratıp bunun tablosu tutulumalıdır.
}
