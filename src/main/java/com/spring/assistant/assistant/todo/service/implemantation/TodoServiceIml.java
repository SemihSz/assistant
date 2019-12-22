package com.spring.assistant.assistant.todo.service.implemantation;


import com.spring.assistant.assistant.executable.interfaces.service.GetUserIdService;
import com.spring.assistant.assistant.general.GenerateService;
import com.spring.assistant.assistant.mailservice.model.MailInfoModel;
import com.spring.assistant.assistant.mailservice.service.AutomaticMailServiceImpl;
import com.spring.assistant.assistant.mailservice.service.MailService;
import com.spring.assistant.assistant.todo.entity.DeleteAllTodoEntity;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.model.request.TodoRequestModel;
import com.spring.assistant.assistant.todo.model.request.TodoSortRequestModel;
import com.spring.assistant.assistant.todo.model.request.TodoTaskIdRequestModel;
import com.spring.assistant.assistant.todo.repository.DeleteAllTodoRepository;
import com.spring.assistant.assistant.todo.repository.TodoRepository;
import com.spring.assistant.assistant.todo.service.TodoService;
import com.spring.assistant.assistant.todo.shared.TodoDto;
import com.spring.assistant.assistant.todo.shared.enums.EmailType;
import com.spring.assistant.assistant.todo.shared.enums.ImportantType;
import com.spring.assistant.assistant.todo.shared.enums.PageType;
import com.spring.assistant.assistant.todo.shared.enums.PrefixType;
import com.spring.assistant.assistant.todo.shared.utils.GenerateNumberUtil;
import com.spring.assistant.assistant.usercontroller.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @Autowired
    private AutomaticMailServiceImpl automaticEmailService;

    @Autowired
    private GenerateService generateService;

    @Autowired
    private GetUserIdService getUserIdService;

    //TODO mail yap 05.11.19
    @Override
    public TodoDto createNewTodo(TodoDto todo) {
        TodoEntity entityTodoEntity = new TodoEntity();
        TodoDto returnValue = new TodoDto();
        String userId = null;
        BeanUtils.copyProperties(todo, entityTodoEntity);
        if (controlTheTitleOfTodo(todo.getTitle())) {
            if (sizeOfTitle(todo.getTitle())) {
                if (descLenght(todo.getDescription())) {
                    if (!todo.getUserId().isEmpty() && todo.getUserId().equals("")) {
                        userId = todo.getUserId();
                    } else {
                        userId = getUserIdService.getUserId();
                    }

                    String taskId = generateService.generateRandomTaskId();

                    if (todoRepository.findByTaskId(taskId) == null) {
                        String newTaskId = generateService.generateRandomTaskId();
                        if (!newTaskId.equals(taskId)) {
                            entityTodoEntity.setTaskId(newTaskId);
                        }

                    } else {
                        entityTodoEntity.setTaskId(taskId);
                    }

                    entityTodoEntity.setUserId(userId);
                    entityTodoEntity.setCreatedDate(todo.getCreatedDate());
                    entityTodoEntity.setUpdatedDate(todo.getCreatedDate());
                    entityTodoEntity.setExpectFinishDate(todo.getExpectFinishDate());
                    entityTodoEntity.setFinnished(false);
                    entityTodoEntity.setEmail(EmailType.NEW.getEmailType());
                    TodoEntity storeTodos = todoRepository.save(entityTodoEntity);
                    BeanUtils.copyProperties(storeTodos, returnValue);

                } else {
                    showRunTimeMessag("Your description is so short");
                }
            }
        } else {

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
            if (todoRequestModel.getDescription().isEmpty()) {
                todoEntity.setDescription(todos.get(0).getDescription());
            } else {
                todoEntity.setDescription(todoRequestModel.getDescription());
            }
            if (todoRequestModel.getCategory().isEmpty()) {
                todoEntity.setCategory(todos.get(0).getCategory());
            } else {
                todoEntity.setCategory(todoRequestModel.getCategory());
            }
            todoEntity.setImportantLevel(todos.get(0).getImportantLevel());
            if (todoRequestModel.getCreatedDate().equals(null)) {
                todoEntity.setCreatedDate(todos.get(0).getCreatedDate());
            } else {
                todoEntity.setCreatedDate(todoRequestModel.getCreatedDate());
            }
            if (todoRequestModel.getExpectFinishDate().equals(null)) {
                todoEntity.setCreatedDate(todos.get(0).getExpectFinishDate());
            } else {
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


    @Override
    public TodoDto finishTodo(TodoTaskIdRequestModel todoTaskIdRequestModel) {
        LocalDate localDate = LocalDate.now();
        TodoEntity currentEntity = null;
        List<TodoEntity> todoEntity = todoRepository.findByTaskId(todoTaskIdRequestModel.getTaskId());
        if (todoEntity.size() > 1) {
            log.error("Two different task id cannot be same");
            throw new RuntimeException("Two different task id cannot be same");
        } else {
            currentEntity = todoEntity.get(0);
        }

        try {
            MailInfoModel mailInfoModel = MailInfoModel.builder()
                    .body1(currentEntity.getTaskId())
                    .body2(currentEntity.getDescription())
                    .body3(currentEntity.getCategory())
                    .body4(" Task bitmiştir. Tebrikler!")
                    .subject(currentEntity.getTitle() + " " + currentEntity.getTaskId())
                    .to(showEmailAddress()).build();

            mailService.sendStandartMail(mailInfoModel);
            int x = todoRepository.update(todoTaskIdRequestModel.getTaskId(), localDate,
                    EmailType.SEND.getEmailType());
            logger.info("!!!Email Sending Now!!!");
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
        //Todo kontrol in progress ise bunu o listeden kaldır
        TodoEntity entity =new TodoEntity();
        TodoDto todoDto = new TodoDto();
        BeanUtils.copyProperties(entity,todoDto);
        return todoDto;
    }

    @Override
    public void automaticEmailService() {

        String userId = getUserIdService.getUserId();
        List<TodoEntity> todoEntity = todoRepository.finds(userId);
        for (TodoEntity currentTodo : todoEntity) {
            final LocalDate expectedDate = currentTodo.getExpectFinishDate();
            if (currentTodo.getEmail().equals(EmailType.NEW.emailType)
                    && currentTodo.getImportantLevel().equals(ImportantType.FIVE.importantLevelNum)) {
                MailInfoModel mailInfoModel = MailInfoModel.builder()
                        .body1(currentTodo.getTaskId())
                        .body2(currentTodo.getDescription())
                        .body3(currentTodo.getCategory())
                        .body4(" Task bitmiştir. Tebrikler!")
                        .subject(currentTodo.getTitle() + " " + currentTodo.getTaskId())
                        .to(showEmailAddress()).build();
                try {
                    automaticEmailService.getAllAutomatic(mailInfoModel, expectedDate);
                    currentTodo.setEmail(EmailType.SEND.getEmailType());
                    todoRepository.save(currentTodo);
                } catch (MessagingException e) {
                    log.error(e.getMessage());

                }

            } else {
                currentTodo.setEmail(EmailType.NOTSEND.getEmailType());
                throw new RuntimeException("You sent this task b before");
            }
        }

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
                .todoPrefix(PrefixType.TODO.toString())
                .build();
        logger.info("!!!Save the deleted item into All Todo Entity!!!");
        deleteAllTodoRepository.save(allTodoEntity);
        logger.info("!!!Delete specific todo!!!");
        todoRepository.deleteById(newTodoEntity.getId());
    }


    @Override
    public List<TodoEntity> getlAllTodosWithPagination(TodoSortRequestModel todoSortRequestModel) {
        return getSortOperation(todoSortRequestModel);
    }

    private List<TodoEntity> getSortOperation(TodoSortRequestModel todoSortRequestModel) {
        logger.info("Sorting operations is starting");
        String username = userService.giveUserAuthenticationInformation();
        String userId = userService.findByEmail(username).getUserId();
        String sortBy = null;
        if (todoSortRequestModel.getId() != null) {
            sortBy = todoSortRequestModel.getId();
        }
        if (todoSortRequestModel.getImportantLevel() != null) {
            sortBy = todoSortRequestModel.getImportantLevel();
        }
        if (todoSortRequestModel.getCreatedDate() != null) {
            sortBy = todoSortRequestModel.getCreatedDate();
        }
        if (todoSortRequestModel.getCategory() != null) {
            sortBy = todoSortRequestModel.getCategory();
        }
        org.springframework.data.domain.Pageable pageable = PageRequest.of(PageType.PAGENO.getPageType(),
                PageType.PAGESIZE.getPageType(),
                Sort.by(sortBy).ascending());
        Page<TodoEntity> pageResult = todoRepository.pages(userId, pageable);
        logger.info("Sorting operations is finished");
        if (pageResult.hasContent()) {
            return pageResult.getContent();

        } else
            return new ArrayList<TodoEntity>();

    }

    public Page<TodoEntity> getWorkLogs(Pageable pageable) {
        final Pageable pageableWithDefaultSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSortOr(Sort.by(
                Sort.Direction.DESC, "id")));
        return todoRepository.findAll(pageableWithDefaultSort);
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

    private String showEmailAddress() {
        return userService.giveUserAuthenticationInformation();
    }

    @Override
    public List<TodoEntity> showSpecifTodoAndSubTask(String taskId) {
        logger.info("!!!Show Specif Todo Sub Task!!!");
        return this.showTodoList();
    }
}
