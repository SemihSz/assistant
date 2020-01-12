package com.spring.assistant.assistant.todo.controller;

import com.spring.assistant.assistant.todo.entity.SubTodoEntity;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.model.request.SubTaskIdRequestModel;
import com.spring.assistant.assistant.todo.model.request.SubTodoRequestModel;
import com.spring.assistant.assistant.todo.model.response.SubTodoResponse;
import com.spring.assistant.assistant.todo.service.implemantation.SubTodoServiceIml;
import com.spring.assistant.assistant.todo.shared.SubTodoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/subtask")
public class SubTodoController {

    private static final Object RuntimeException = "error user id and task id are empty";
    private final static String SHOW_THE_CREATE_SUB_TASK_PAGE_GET = "/detail/{userId}/{taskId}";
    private final static String SHOW_LIST_SUB_TODOS = "/list";
    private final static String CREATE_SUB_TASK = "/create/{userId}/{taskId}";
    private final static String GET_SUB_TASK_LIST = "/list/{userId}/{taskId}";
    private final static String GET_SUB_TASK_LIST_ALL = "/list-all";
    private final static String POST_UPDATE_SUB_TASK= "/update/{subTaskId}";
    private final static String GET_UPDATE_SUB_TASK= "/update/{subTaskId}";
    private final static String GET_FINISH_SUB_TODO= "/finish/{subTaskId}";
    private final static String GET_SPECIFIC_DELETE_SUB_TODO = "/delete/{subTaskId}";
    private final static String GET_DELETE_ALL = "/delete-all";
    private final static String GET_SPECIFIC_TASK_ID_SUB_TODOS_LIST = "/show-specific-task-id-sub-todo-list/{taskId}";
    private final static String GET_SPECIF_TASK_ID_DELETE_SUB_TODO_FROM_SUB_TODO_ID = "/show-specific-task-id-sub-todo-list/delete/{subTaskId}/{taskId}";
    private final static String GET_SPECIF_TASK_ID_FINISH_SUB_TODO_FROM_SUB_TODO_ID = "/show-specific-task-id-sub-todo-list/finish/{subTaskId}/{taskId}";

    //TODO todo yapılan sadece belli user tasklarını göster.

    @Autowired
    private SubTodoServiceIml service; // private yap kesinlikle

    /**
     *
     * Get  Kısmı yapıldı
     *
     * PostMapping kısmın yapıldı +
     * */


    @GetMapping(value = SHOW_THE_CREATE_SUB_TASK_PAGE_GET)
    public String showTheCreateSubTaskPage(@PathVariable("taskId") String taskId,
                                           @PathVariable("userId")String userId, Model model) throws Throwable {


        SubTodoRequestModel subTodoRequestModel = new SubTodoRequestModel();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("USER_ID", userId);
        httpHeaders.add("TASK_ID", taskId);
        ResponseEntity.ok().headers(httpHeaders).body("Adding user id and task id");
        subTodoRequestModel.setUserId(userId);
        subTodoRequestModel.setTaskId(taskId);
        model.addAttribute("subTodoRequestModel", subTodoRequestModel);
        return "sub-task-view";
    }

    @PostMapping(value = CREATE_SUB_TASK)
    public String createSubTasks(@PathVariable("taskId") String taskId,
                                 @PathVariable("userId")String userId,
                                 @RequestBody @ModelAttribute("subTodoRequestModel") SubTodoRequestModel subTodoRequestModel,
                                 @ModelAttribute("message") String message,
                                 Model model,
                                 BindingResult bindingResult,
                                 HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("Userid", userId);
        httpServletResponse.setHeader("Taskid", taskId);
        SubTodoResponse subTodoResponse = new SubTodoResponse();
        subTodoRequestModel.setUserId(userId);
        subTodoRequestModel.setTaskId(taskId);
        if (bindingResult.hasErrors()){
            message = "some errors happens";
            model.addAttribute("error",message);
        }
        SubTodoDto subTodoDto = service.createNewSubTodo(subTodoRequestModel);
        BeanUtils.copyProperties(subTodoDto, subTodoResponse);
        message = "You Created Sub task!";
        model.addAttribute("message", message);
        return "sub-task-view";
    }

    @RequestMapping(path = SHOW_LIST_SUB_TODOS ,method = RequestMethod.GET)
    public String showListOfTodo(Model model){

        System.out.println(service.showTodoList());
        model.addAttribute("todos", service.showTodoList());
        return "about";
    }

    @GetMapping(value = GET_SUB_TASK_LIST)
    public ResponseEntity<List<TodoEntity>> showSubList(@PathVariable("taskId") String taskId,
                                                        @PathVariable("userId")String userId,
                                                        @RequestBody @ModelAttribute("subTodoRequestModel") SubTodoRequestModel subTodoRequestModel,
                                                        Model model,
                                                        HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("Userid", userId);
        httpServletResponse.setHeader("Taskid", taskId);
        return ResponseEntity.ok().body(service.showTodoList());
    }

    @GetMapping(value = GET_SUB_TASK_LIST_ALL)
    public String showTheSubTodoList(Model model){
        List <SubTodoEntity> subTodoEntity = service.showAllsSubTask();
        model.addAttribute("subtodo", subTodoEntity);
        model.addAttribute("status",false);
        return "show-sub-tasks";
    }

    @PostMapping(value = POST_UPDATE_SUB_TASK)
    public String postUpdateSubTodoList(@PathVariable("subTaskId") String  subTaskId, SubTodoRequestModel subTodoRequestModel){
        subTodoRequestModel.setSubTaskId(subTaskId);
        SubTodoDto subTodoDto = service.updateTheSubTask(subTodoRequestModel);
        return "redirect:/subtask/list-all";
    }

    @GetMapping(value = GET_UPDATE_SUB_TASK)
    public String  getUpdateSubTodoList(@PathVariable("subTaskId") String subTaskId, Model model){
        SubTodoRequestModel subTodoRequestModel = new SubTodoRequestModel();
        subTodoRequestModel.setSubTaskId(subTaskId);
        model.addAttribute("updateSubTodoRequestModel", subTodoRequestModel);
        return "update-sub-todo";
    }

    @GetMapping(value = GET_FINISH_SUB_TODO)
    public String getFinishSubTodoList(@PathVariable("subTaskId") String subTaskId, @ModelAttribute("finishSubTodoRequestModel") SubTaskIdRequestModel taskIdRequestModel){
        taskIdRequestModel.setSubTaskId(subTaskId);
        service.finishSpecificTask(taskIdRequestModel);
        return "redirect:/subtask/list-all";
    }

    @GetMapping(value = GET_SPECIFIC_DELETE_SUB_TODO)
    public String getDeleteSubTodoList(@PathVariable("subTaskId") String subTaskId,SubTaskIdRequestModel taskIdRequestModel){
        taskIdRequestModel.setSubTaskId(subTaskId);
        service.deleteSpecificTodo(taskIdRequestModel);
        return "redirect:/subtask/list-all";
    }

    @GetMapping(value = GET_DELETE_ALL)
    public String getDeleteAll(){
        service.deleteAll();
        return "redirect:/home";
    }

    @GetMapping(value = GET_SPECIFIC_TASK_ID_SUB_TODOS_LIST)
    public String showSpecificTaskSubTodoList(@PathVariable("taskId") String taskId, Model model){
        List<SubTodoEntity> subTodoEntities = service.showAllSubTaskForCurrentTaskId(taskId);
        model.addAttribute("subtodo", subTodoEntities);
        return "task-show-all-subtodos";
    }

    @GetMapping(value = GET_SPECIF_TASK_ID_DELETE_SUB_TODO_FROM_SUB_TODO_ID)
    public String getSpecificTaskSubTodoDelete(@PathVariable("subTaskId") String subTaskId, @PathVariable("taskId") String taskId, SubTaskIdRequestModel taskIdRequestModel){
        taskIdRequestModel.setSubTaskId(subTaskId);
        service.deleteSpecificTodo(taskIdRequestModel);
        return "redirect:/subtask/show-specific-task-id-sub-todo-list/"+taskId;
    }

    @GetMapping(value = GET_SPECIF_TASK_ID_FINISH_SUB_TODO_FROM_SUB_TODO_ID)
    public String getSpecificTaskSubTodoFinish(@PathVariable("subTaskId") String subTaskId, @PathVariable("taskId") String taskId, SubTaskIdRequestModel taskIdRequestModel){
        taskIdRequestModel.setSubTaskId(subTaskId);
        service.finishSpecificTask(taskIdRequestModel);
        return "redirect:/subtask/show-specific-task-id-sub-todo-list/"+taskId;
    }



}
