package com.spring.assistant.assistant.todo.service.implemantation;

import com.spring.assistant.assistant.todo.entity.SubTodoEntity;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.model.request.SubTaskIdRequestModel;
import com.spring.assistant.assistant.todo.model.request.SubTodoRequestModel;
import com.spring.assistant.assistant.todo.model.request.TodoRequestModel;
import com.spring.assistant.assistant.todo.repository.SubTodoRepository;
import com.spring.assistant.assistant.todo.repository.TodoRepository;
import com.spring.assistant.assistant.todo.service.SubTodoService;
import com.spring.assistant.assistant.todo.shared.SubTodoDto;
import com.spring.assistant.assistant.todo.shared.TodoDto;

import com.spring.assistant.assistant.todo.shared.utils.GenerateNumberUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;


//@Component("sub")

public class SubTodoServiceIml extends TodoServiceIml implements SubTodoService {

    private static final Logger logger = LoggerFactory.getLogger(SubTodoServiceIml.class);

    private final int SUB_TASK_ID_LENGHT = 12;


    @Autowired
    SubTodoRepository subTodoRepository;

    private LocalDate localDate = LocalDate.now();

    @Autowired
    private GenerateNumberUtil generateNumberUtil;

    @Override
    public SubTodoDto createNewSubTodo(SubTodoRequestModel subTodoRequestModel) {
        boolean isFinnished = false;
        String subTaskId = null;
        SubTodoEntity subTodoEntity = new SubTodoEntity();
        SubTodoDto returnValue = new SubTodoDto();

        if (controlTitleDescription(subTodoRequestModel.getSubTodoTitle(), subTodoRequestModel.getSubTodoDescription())) {
            subTodoEntity.setSubTodoTitle(subTodoRequestModel.getSubTodoTitle());
            subTodoEntity.setSubTodoDescription(subTodoRequestModel.getSubTodoDescription());
            logger.info("Title and Description is ok!");
        } else {
            logger.error("Your title and desc. are empty", new RuntimeException("Your title and desc. are empty"));

        }
        if (!subTodoRequestModel.getTaskId().isEmpty() && !subTodoRequestModel.getUserId().isEmpty()) {
            subTodoEntity.setUserId(subTodoRequestModel.getUserId());
            subTodoEntity.setTaskId(subTodoRequestModel.getTaskId());
            if (subTodoRequestModel.getUserId().equals(subTodoRequestModel.getTaskId())) {
                logger.error("User id and task id cannot be same", new RuntimeException("Use id and task id are equal"));
            }
            subTaskId = generateNumberUtil.generateSubTaskId(SUB_TASK_ID_LENGHT);
            subTodoEntity.setSubTaskId(subTaskId);
            logger.info("Your Sub task id created", subTaskId);
        }
        subTodoEntity.setFinished(isFinnished);
        subTodoEntity.setSubTodoCategory(subTodoRequestModel.getSubTodoCategory());

        if (!controlTheStartDateAndFinsih(subTodoRequestModel.getSubTodoCreatedDate(), subTodoRequestModel.getSubTodoFinishDate())) {
            subTodoEntity.setSubTodoCreatedDate(subTodoRequestModel.getSubTodoCreatedDate());
            subTodoEntity.setSubTodoFinishDate(subTodoRequestModel.getSubTodoFinishDate());

            subTodoEntity.setSubTodoUpdateDate(localDate);
            logger.info("Creating date and finish date are correctly to setting.");
        } else {
            logger.error("Dates are wrongs", new DateTimeException("Local dates are not correctly"));
        }

        SubTodoEntity storeEntity = subTodoRepository.save(subTodoEntity);
        BeanUtils.copyProperties(storeEntity, returnValue);

        return returnValue;

    }

    @Override
    public SubTodoDto updateTheSubTask(SubTodoRequestModel subTodoRequestModel) {

        if (subTodoRequestModel.getSubTaskId().isEmpty()){
            logger.error("There is no sub task id", new Exception());
        }

        SubTodoEntity subTodoEntity = subTodoRepository.findBySubTaskId(subTodoRequestModel.getSubTaskId());
        SubTodoEntity newSubTodoEntity1 = new SubTodoEntity();
        if (subTodoEntity == null){
            logger.error("Errorrrr" , new RuntimeException());
        }
        if (subTodoRequestModel.getSubTodoTitle().isEmpty()){
            logger.info("Now Control the request of title is empty");
            newSubTodoEntity1.setSubTodoTitle(subTodoEntity.getSubTodoTitle());
        }
        if (subTodoRequestModel.getSubTodoDescription().isEmpty()){
            logger.info("Now Control the request of description is empty");
            newSubTodoEntity1.setSubTodoDescription(subTodoEntity.getSubTodoDescription());
        }

        if (controlTitleDescription(subTodoRequestModel.getSubTodoTitle(),subTodoRequestModel.getSubTodoDescription())){
            if (!subTodoRequestModel.getSubTodoTitle().isEmpty()){
                newSubTodoEntity1.setSubTodoTitle(subTodoRequestModel.getSubTodoTitle());
            }

            if (!subTodoRequestModel.getSubTodoDescription().isEmpty()){
                newSubTodoEntity1.setSubTodoDescription(subTodoRequestModel.getSubTodoDescription());
            }

            logger.info("Update the tittle and Description");

        }else
            logger.error("title and desc", new RuntimeException());


        if (subTodoRequestModel.getSubTodoCategory().isEmpty()) {
            newSubTodoEntity1.setSubTodoCategory(subTodoEntity.getSubTodoCategory());
        }
        else {
            newSubTodoEntity1.setSubTodoCategory(subTodoRequestModel.getSubTodoCategory());
        }

        if (subTodoRequestModel.getSubTodoCreatedDate()==null){
            newSubTodoEntity1.setSubTodoCreatedDate(subTodoEntity.getSubTodoCreatedDate());
        }
        if (subTodoRequestModel.getSubTodoFinishDate()==null){
            newSubTodoEntity1.setSubTodoFinishDate(subTodoEntity.getSubTodoFinishDate());

        }
        else {
            if (controlTheStartDateAndFinsih(subTodoRequestModel.getSubTodoCreatedDate(),subTodoRequestModel.getSubTodoFinishDate())){
                newSubTodoEntity1.setSubTodoCreatedDate(subTodoRequestModel.getSubTodoCreatedDate());
                newSubTodoEntity1.setSubTodoFinishDate(subTodoRequestModel.getSubTodoFinishDate());
            }
            else
                logger.error("Time problem", new DateTimeException("Local Date was wrong"));
        }


        newSubTodoEntity1.setSubTodoUpdateDate(localDate);
        newSubTodoEntity1.setUserId(subTodoEntity.getUserId());
        newSubTodoEntity1.setTaskId(subTodoEntity.getTaskId());
        newSubTodoEntity1.setSubTaskId(subTodoRequestModel.getSubTaskId());

        newSubTodoEntity1.setFinished(false);
        subTodoRepository.delete(subTodoEntity);
        subTodoRepository.save(newSubTodoEntity1);
        SubTodoDto returnSubTodo = new SubTodoDto();
        BeanUtils.copyProperties(newSubTodoEntity1, returnSubTodo);
        return returnSubTodo;
    }

    @Override
    public void deleteSpecificTodo(SubTaskIdRequestModel subTaskIdRequestModel) {
        SubTodoEntity subTodoEntity = subTodoRepository.findBySubTaskId(subTaskIdRequestModel.getSubTaskId());
        subTodoRepository.delete(subTodoEntity);
    }


    @Override
    public List<SubTodoEntity> showAllSubTaskForCurrentTaskId(String taskId) {

        return (List<SubTodoEntity>) subTodoRepository.findByTaskId(taskId);
    }

    @Override
    public void finishSpecificTask(SubTaskIdRequestModel subTaskIdRequestModel) {
        subTodoRepository.finishSubTask(subTaskIdRequestModel.getSubTaskId());
    }

    @Override
    public List<SubTodoEntity> showAllsSubTask() {
        return (List<SubTodoEntity>) subTodoRepository.findAll();
    }


    @Override
    public Boolean controlTitleDescription(String title, String desc) {
        return !title.isEmpty() || !desc.isEmpty();
    }

    @Override
    public Boolean controlTitleDescriptionLenght(String title, String desc) {

        return null;
    }

    @Override
    public Boolean controlTheStartDateAndFinsih(LocalDate start, LocalDate end) {
        if (start.compareTo(end) > 0) {
            return true;
        } else if (start.compareTo(end) == 0) {
            return true;
        }
        return false;
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
    public void deleteAll() {
        subTodoRepository.deleteAll();
    }

    @Override
    public List<TodoEntity> showTodoList() {
        return super.showTodoList();
    }


}
