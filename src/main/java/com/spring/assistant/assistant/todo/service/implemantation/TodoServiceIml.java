package com.spring.assistant.assistant.todo.service.implemantation;

import com.spring.assistant.assistant.todo.Enum;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.model.request.TodoRequestModel;
import com.spring.assistant.assistant.todo.model.request.TodoTaskIdRequestModel;
import com.spring.assistant.assistant.todo.model.response.TodoResponseModel;
import com.spring.assistant.assistant.todo.repository.TodoRepository;
import com.spring.assistant.assistant.todo.service.TodoService;
import com.spring.assistant.assistant.todo.shared.TodoDto;
import com.spring.assistant.assistant.todo.shared.utils.GenerateNumberUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Primary
@Service
@Qualifier("todo")
public class TodoServiceIml implements TodoService {

    private final int TASK_RANDOM_NUMBER = 15;
    private final int MAX = 100;
    private final int MIN =10;

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    GenerateNumberUtil generateNumberUtil;

    //TODO mail yap 05.11.19
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
                    //TODO Kontroller yaz
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
                    entityTodoEntity.setCreatedDate(todo.getCreatedDate());
                    entityTodoEntity.setUpdatedDate(todo.getCreatedDate());
                    LocalDate localDate = LocalDate.now();
                    entityTodoEntity.setExpectFinishDate(localDate);
                    entityTodoEntity.setFinnished(false);
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
    /**
     * Update Specif Todos
     * */

    @Override
    public TodoDto updateSpecifTask(TodoRequestModel todoRequestModel) {

        List<TodoEntity> todos = todoRepository.findByTaskId(todoRequestModel.getTaskId());
        todoRequestModel.setUserId(todos.get(0).getUserId());
        TodoEntity todoEntity = new TodoEntity();
        BeanUtils.copyProperties(todos, todoEntity);
        if (todoRequestModel.getTitle().isEmpty()){
            todoEntity.setTitle(todos.get(0).getTitle());
        }
        else {
            todoEntity.setTitle(todoRequestModel.getTitle());
        }
        if (todoRequestModel.getDescription().isEmpty()){
            todoEntity.setDescription(todos.get(0).getDescription());
        }
        else {
            todoEntity.setDescription(todoRequestModel.getDescription());
        }
        if (todoRequestModel.getCategory().isEmpty()){
            todoEntity.setCategory(todos.get(0).getCategory());
        }
        else {
            todoEntity.setCategory(todoRequestModel.getCategory());
        }
        if (todoRequestModel.getImportantLevel().isEmpty()){
            todoEntity.setImportantLevel(todos.get(0).getImportantLevel());
        }
        else{
            todoEntity.setImportantLevel(todoRequestModel.getImportantLevel());
        }
        if (todoRequestModel.getCreatedDate().equals("")){
            todoEntity.setCreatedDate(todos.get(0).getCreatedDate());
        }
        else{
            todoEntity.setCreatedDate(todoRequestModel.getCreatedDate());
        }
        if (todoRequestModel.getExpectFinishDate().equals("")){
            todoEntity.setCreatedDate(todos.get(0).getExpectFinishDate());
        }
        else{
            todoEntity.setExpectFinishDate(todoRequestModel.getExpectFinishDate());
        }
        todoEntity.setFinnished(false);
        todoEntity.setUserId(todoRequestModel.getUserId());
        todoEntity.setTaskId(todoRequestModel.getTaskId());
        todoEntity.setUpdatedDate(LocalDate.now());
        todoRepository.delete(todos.get(0));
        TodoEntity storeEntity = todoRepository.save(todoEntity);
        TodoDto returnDto = new TodoDto();
        BeanUtils.copyProperties(storeEntity,returnDto);
        return returnDto;
    }


    //TODO bu methodu değiştir bence
    @Override
    public TodoDto finishTodo(TodoTaskIdRequestModel todoTaskIdRequestModel) {
        LocalDate localDate = LocalDate.now();
        int x =  todoRepository.update(todoTaskIdRequestModel.getTaskId(),localDate);
        System.out.println("x: "+ x);
        TodoEntity entity =new TodoEntity();
        TodoDto todoDto = new TodoDto();
        BeanUtils.copyProperties(entity,todoDto);
        return todoDto;
    }

    @Override
    public void deleteSpecificTodo(TodoTaskIdRequestModel todoTaskIdRequestModel) {
        List<TodoEntity> todoEntities = todoRepository.findByTaskId(todoTaskIdRequestModel.getTaskId());
        todoRepository.deleteById(todoEntities.get(0).getId());
    }

    @Override
    public void deleteAll() {
        todoRepository.deleteAll();
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
        TodoEntity entity =  todoRepository.findByUserId(userId);
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

    private String stringRandom(int lenght){
        String s = generateNumberUtil.generateUserId(lenght);
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

    @Override
    public List<TodoEntity> showTodoList() {
        return (List<TodoEntity>) todoRepository.findAll();
    }

    @Override
    public List<TodoEntity> showSpecifTodoAndSubTask(String taskId) {
        return this.showTodoList();
    }
}
