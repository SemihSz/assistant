package com.spring.assistant.assistant.todo.controller;


import com.spring.assistant.assistant.todo.model.request.TodoRequestModel;
import com.spring.assistant.assistant.todo.model.request.TodoTaskIdRequestModel;
import com.spring.assistant.assistant.todo.model.response.TodoResponseAll;
import com.spring.assistant.assistant.todo.model.response.TodoResponseModel;
import com.spring.assistant.assistant.todo.service.TodoService;
import com.spring.assistant.assistant.todo.shared.TodoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
// https://github.com/khandelwal-arpit/springboot-starterkit/tree/master/src/main/java/com/starterkit/springboot/brs/service
@Controller
//@RestController //Postman kullanırsan sadece Rest fakat index ise controller gerekiyor
@RequestMapping("/home")
public class TodoPageController {

    @Qualifier("TodoServiceIml")
    @Autowired
    private TodoService todoService;

    @RequestMapping(method = RequestMethod.GET)
    public String homePageView(Model model){
        TodoRequestModel todoRequestModel = new TodoRequestModel();
        model.addAttribute("todoRequestModel", todoRequestModel);
        return "index";
    }

    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public String showListOfTodo(Model model){

        System.out.println(todoService.showTodoList());
        model.addAttribute("todos", todoService.showTodoList());
        return "about";
    }

    @RequestMapping(path = "/create",method = RequestMethod.GET)
    public String todoPageView(Model model){

        TodoRequestModel todoRequestModel = new TodoRequestModel();

        model.addAttribute("todoRequestModel", todoRequestModel);

        return "create-todo";
    }

    @RequestMapping(path = "/about",method = RequestMethod.GET)
    public String homePageLinkAboutPage(){
        return "about";
    }

    @RequestMapping(value = "/home-post", method = RequestMethod.POST) //Bu Html alınan post verileri backend iletiliyor
    public ResponseEntity denemes(@RequestParam("ss") String name){
        System.out.println(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(path = "/create")
    public String creatNewTodosPage(){
        return "create-todo";
    }

    @PostMapping(path = "/create-todo")
    public String createTodoController(@Valid @RequestBody @ModelAttribute("todoRequestModel") TodoRequestModel todoRequestModel, HttpSession httpSession){
        TodoResponseModel todoResponseModel = new TodoResponseModel();
        TodoDto todoDto = new TodoDto();
        BeanUtils.copyProperties(todoRequestModel, todoDto);
        TodoDto createDto =  todoService.createNewTodo(todoDto);
        BeanUtils.copyProperties(createDto,todoResponseModel);
        httpSession.setAttribute("id", todoResponseModel.getId());
        return  "index";//ResponseEntity.accepted().body(todoResponseModel); // Response bu şekilde de yapılabilir ResponseEntity.status().body içeriği böyle olabilir:
    }

    @GetMapping(path = "/show-todo-task")
    public TodoResponseAll showTodoTaskNumber(@RequestBody TodoTaskIdRequestModel todoTaskIdRequestModel){
        TodoResponseAll todoResponseAll = new TodoResponseAll();
        TodoDto todoDto = new TodoDto();
        BeanUtils.copyProperties(todoTaskIdRequestModel, todoDto);
        TodoDto createDtro = todoService.getUser(todoTaskIdRequestModel.getTaskId());
        BeanUtils.copyProperties(createDtro,todoResponseAll);
        return todoResponseAll;

    }
    @GetMapping(value = "/update/{taskId}")
    public String getCreatTaskId(@PathVariable("taskId") String taskId, Model model){
        TodoRequestModel updateTodoRequestModel =new TodoRequestModel();
        updateTodoRequestModel.setTaskId(taskId);
        model.addAttribute("updateTodoRequestModel", updateTodoRequestModel);
        return "update-todo";
    }
    /**
     * Putmaping olamıyor çünkü thymleaf ve html form yapılarında put yok!!!!!!
     * */
    @PostMapping(value = "/update/{taskId}")
    public ResponseEntity<TodoDto> updateSpecificTodo(@PathVariable("taskId") String taskId, @ModelAttribute("updateTodoRequestModel") TodoRequestModel todoRequestModel){
        todoRequestModel.setTaskId(taskId);
        TodoDto updateDto = todoService.updateSpecifTask(todoRequestModel);
        return ResponseEntity.ok().body(updateDto);

    }

    @GetMapping(value = "/finish/{taskId}")
    public String getFinishSpecificTodo(@PathVariable("taskId") String taskId, @ModelAttribute("finishTodoRequestModel") TodoTaskIdRequestModel todoTaskIdRequestModel){
        todoTaskIdRequestModel.setTaskId(taskId);
        TodoDto todoDto = todoService.finishTodo(todoTaskIdRequestModel);
        return "redirect:/home/list";
    }
    //TODO DELETE Sonlarda Test et
    @GetMapping(value = "delete-all")
    public String deleteAllTodos(){
        todoService.deleteAll();
        return "redirect:/home";
    }

    @GetMapping(value = "/delete/{taskId}")
    public String deleteSpecificTodo(@PathVariable("taskId") String taskId, @ModelAttribute("finishTodoRequestModel") TodoTaskIdRequestModel todoTaskIdRequestModel){
        todoTaskIdRequestModel.setTaskId(taskId);
        todoService.deleteSpecificTodo(todoTaskIdRequestModel);
        return "redirect:/home/list";
    }


}
