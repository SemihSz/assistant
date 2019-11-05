package com.spring.assistant.assistant.todo.controller;

import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.model.response.SubTodoResponse;
import com.spring.assistant.assistant.todo.service.TodoService;
import com.spring.assistant.assistant.todo.service.implemantation.SubTodoServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/subtask")
public class SubTodoController {
    @Autowired
    private SubTodoServiceIml service; // private yap kesinlikle

    //TODO Html subtask tablosunu yap 05/11/2019
    //TODO Subtask request ve response yapıları yap 05/11/2019

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
     * TODO PostMapping kısmın yap
     * */
    @GetMapping("/detail/{userId}/{taskId}")
    public String showTheCreateSubTaskPage(@PathVariable("taskId") String taskId,
                                           @PathVariable("userId")String userId, Model model){
        SubTodoResponse subTodoResponse = new SubTodoResponse();
        subTodoResponse.setUserId(userId);
        subTodoResponse.setTaskId(taskId);
        model.addAttribute("getr", subTodoResponse);
        System.out.println(taskId);
        return "sub-task-view";
    }
    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public String showListOfTodo(Model model){

        System.out.println(service.showTodoList());
        model.addAttribute("todos", service.showTodoList());
        return "about";
    }

}
