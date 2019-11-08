package com.spring.assistant.assistant.todo.controller;

import com.spring.assistant.assistant.todo.entity.TodoEntity;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/subtask")
public class SubTodoController {

    private static final Object RuntimeException = "error user id and task id are empty";
    private final String SHOW_THE_CREATE_SUB_TASK_PAGE_GET = "/detail/{userId}/{taskId}";
    private final String SHOW_LIST_SUB_TODOS = "/list";
    private final String CREATE_SUB_TASK = "/create/{userId}/{taskId}";
    private final String GET_SUB_TASK_LIST = "/list/{userId}/{taskId}";
    @Autowired
    private SubTodoServiceIml service; // private yap kesinlikle

    /*@GetMapping("/detail/{taskId}")
    public ResponseEntity<String> showTheSingleSubTask(@PathVariable("taskId") String taskId){
        List<TodoEntity> todoEntity = service.showSpecifTodoAndSubTask(taskId);
        System.out.println(todoEntity);
        return ResponseEntity.ok().body(todoEntity.get(0).toString());
    }*/
    /**
     *
     * Get  Kısmı yapıldı
     *
     * PostMapping kısmın yapıldı +
     * */


    @GetMapping(value = "/detail/{userId}/{taskId}")
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
                                 Model model,
                                 HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("Userid", userId);
        httpServletResponse.setHeader("Taskid", taskId);
        SubTodoResponse subTodoResponse = new SubTodoResponse();
        subTodoRequestModel.setUserId(userId);
        subTodoRequestModel.setTaskId(taskId);
        SubTodoDto subTodoDto = service.createNewSubTodo(subTodoRequestModel);
        BeanUtils.copyProperties(subTodoDto, subTodoResponse);
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
        //TODO Tabloda gösterme işlemleri
        return ResponseEntity.ok().body(service.showTodoList());
    }

}
