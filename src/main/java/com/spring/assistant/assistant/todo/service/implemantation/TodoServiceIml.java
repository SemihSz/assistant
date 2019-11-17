package com.spring.assistant.assistant.todo.service.implemantation;


import com.spring.assistant.assistant.mailservice.MailService;
import com.spring.assistant.assistant.todo.Prefix;
import com.spring.assistant.assistant.todo.entity.DeleteAllTodoEntity;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.model.request.TodoRequestModel;
import com.spring.assistant.assistant.todo.model.request.TodoTaskIdRequestModel;
import com.spring.assistant.assistant.todo.repository.DeleteAllTodoRepository;
import com.spring.assistant.assistant.todo.repository.TodoRepository;
import com.spring.assistant.assistant.todo.service.TodoService;
import com.spring.assistant.assistant.todo.shared.TodoDto;
import com.spring.assistant.assistant.todo.shared.utils.GenerateNumberUtil;
import com.spring.assistant.assistant.usercontroller.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Primary
@Service
@Qualifier("todo")
@Slf4j
public class TodoServiceIml implements TodoService, Serializable {

    private final int TASK_RANDOM_NUMBER = 15;
    private final int MAX = 100;
    private final int MIN =10;
    private static final Logger logger = LoggerFactory.getLogger(SubTodoServiceIml.class);

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    GenerateNumberUtil generateNumberUtil;

    @Autowired
    DeleteAllTodoRepository deleteAllTodoRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    //TODO mail yap 05.11.19
    @Override
    public TodoDto createNewTodo(TodoDto todo) {
        TodoEntity entityTodoEntity = new TodoEntity();
        TodoDto returnValue = new TodoDto();
        String userId = null;
        String username = userService.giveUserAuthenticationInformation();
        BeanUtils.copyProperties(todo, entityTodoEntity);
        if (controlTheTitleOfTodo(todo.getTitle())){
            if (sizeOfTitle(todo.getTitle())){
                if (descLenght(todo.getDescription())){
                    if (!todo.getUserId().isEmpty() && todo.getUserId().equals("")){
                        userId = todo.getUserId();
                    }
                    else {
                        userId = userService.findByEmail(username).getUserId();
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
                    entityTodoEntity.setExpectFinishDate(todo.getExpectFinishDate());
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
        logger.info("!!!Create New Todo!!!");
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
        try {


        if (todoRequestModel.getTitle().isEmpty()) {
            todoEntity.setTitle(todos.get(0).getTitle());
        } else {
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
        todoEntity.setImportantLevel(todos.get(0).getImportantLevel());
        if (todoRequestModel.getCreatedDate().equals(null)){
            todoEntity.setCreatedDate(todos.get(0).getCreatedDate());
        }
        else{
            todoEntity.setCreatedDate(todoRequestModel.getCreatedDate());
        }
        if (todoRequestModel.getExpectFinishDate().equals(null)){
            todoEntity.setCreatedDate(todos.get(0).getExpectFinishDate());
        }
        else{
            todoEntity.setExpectFinishDate(todoRequestModel.getExpectFinishDate());
        }
        }catch (NullPointerException e){
            logger.error(e.getMessage());
        }
        todoEntity.setFinnished(false);
        todoEntity.setUserId(todoRequestModel.getUserId());
        todoEntity.setTaskId(todoRequestModel.getTaskId());
        todoEntity.setUpdatedDate(LocalDate.now());
        todoRepository.delete(todos.get(0));
        TodoEntity storeEntity = todoRepository.save(todoEntity);
        TodoDto returnDto = new TodoDto();
        BeanUtils.copyProperties(storeEntity,returnDto);
        logger.info("!!!Update Specific Todo!!!");
        return returnDto;
    }


    //TODO bu methodu değiştir bence
    @Override
    public TodoDto finishTodo(TodoTaskIdRequestModel todoTaskIdRequestModel) {
        LocalDate localDate = LocalDate.now();
        int x =  todoRepository.update(todoTaskIdRequestModel.getTaskId(),localDate);
        List<TodoEntity> todoEntity = todoRepository.findByTaskId(todoTaskIdRequestModel.getTaskId());
        try {
            StringBuilder stringBuilderText = new StringBuilder();
            stringBuilderText
                    .append(todoEntity.get(0).getDescription())
                    .append(" Task id: ")
                    .append(todoEntity.get(0).getTaskId())
                    .append(" is finish ").append(todoEntity.get(0).getUpdatedDate())
                    .append(" Task bitmiştir. Tebrikler!");
            mailService.sendStandartMail("semirhayy@gmail.com",
                    todoEntity.get(0).getTaskId() + " " + todoEntity.get(0).getTitle(),"<h2>Finish Todo "+todoEntity.get(0).getTaskId() +" \" id of todo is finished at \"" +"</h2>"+
                    todoEntity.get(0).getUpdatedDate()+ " Detail: " + todoEntity.get(0).getDescription()
            );
            logger.info("!!!Email Sending Now!!!");

            //notificationService.sendEmail(stringBuilderText.toString(), todoEntity.get(0).getTitle());
        }catch (MailException e){
            e.printStackTrace();
            logger.error("Email sending error");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("Email sending error");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Email sending error");
        }
        System.out.println("x: "+ x);
        TodoEntity entity =new TodoEntity();
        TodoDto todoDto = new TodoDto();
        BeanUtils.copyProperties(entity,todoDto);
        return todoDto;
    }

    @Override
    public void deleteSpecificTodo(TodoTaskIdRequestModel todoTaskIdRequestModel) {
        List<TodoEntity> todoEntities = todoRepository.findByTaskId(todoTaskIdRequestModel.getTaskId());
        TodoEntity newTodoEntity = todoEntities.get(0);
        DeleteAllTodoEntity allTodoEntity = DeleteAllTodoEntity.builder()
                .id(newTodoEntity.getId())
                .title(newTodoEntity.getTitle())
                .description(newTodoEntity.getDescription())
                .category(newTodoEntity.getCategory())
                .userId(newTodoEntity.getUserId())
                .createdDate(newTodoEntity.getCreatedDate())
                .importantLevel(newTodoEntity.getImportantLevel())
                .expectFinishDate(newTodoEntity.getExpectFinishDate())
                .isFinished(newTodoEntity.isFinnished())
                .updatedDate(newTodoEntity.getUpdatedDate())
                .taskId(newTodoEntity.getTaskId())
                .todoPrefix(Prefix.TODO.toString())
                .build();
        logger.info("!!!Save the deleted item into All Todo Entity!!!");
        deleteAllTodoRepository.save(allTodoEntity);
        logger.info("!!!Delete specific todo!!!");
        todoRepository.deleteById(newTodoEntity.getId());
    }

    @Override
    public void deleteAll() {
        logger.info("!!!DELETE ALL!!!");
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
        logger.info("!!!Now Generate Random Task Id!!!");
        return (generateNumberUtil.generateUserId(TASK_RANDOM_NUMBER));

    }

    private void showRunTimeMessag(String message){
        throw new RuntimeException(message);
    }

    @Override
    public List<TodoEntity> showTodoList() {
        logger.info("!!!Showing The Todo's List!!!");
        return (List<TodoEntity>) todoRepository.findAll();
    }

    @Override
    public List<TodoEntity> showTodoListCurrentUser() {
        String userId = null;
        String username = userService.giveUserAuthenticationInformation();
        userId =  userService.findByEmail(username).getUserId();
        return (List<TodoEntity>) todoRepository.finds(userId);
    }

    @Override
    public List<TodoEntity> showSpecifTodoAndSubTask(String taskId) {
        logger.info("!!!Show Specif Todo Sub Task!!!");
        return this.showTodoList();
    }
}
