package com.spring.assistant.assistant.todo.controller;



import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.model.request.CategoryRequestModel;
import com.spring.assistant.assistant.todo.model.request.TodoRequestModel;
import com.spring.assistant.assistant.todo.model.request.TodoTaskIdRequestModel;
import com.spring.assistant.assistant.todo.model.response.TodoResponseAll;
import com.spring.assistant.assistant.todo.model.response.TodoResponseModel;
import com.spring.assistant.assistant.todo.service.CategoryService;
import com.spring.assistant.assistant.todo.service.TodoService;
import com.spring.assistant.assistant.todo.shared.TodoDto;
import com.spring.assistant.assistant.usercontroller.User;
import com.spring.assistant.assistant.usercontroller.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

// https://github.com/khandelwal-arpit/springboot-starterkit/tree/master/src/main/java/com/starterkit/springboot/brs/service
@Controller
//@RestController //Postman kullanırsan sadece Rest fakat index ise controller gerekiyor
@RequestMapping("/home")
public class TodoPageController {

    @Qualifier("TodoServiceIml")
    @Autowired
    private TodoService todoService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String homePageView(Model model){
        TodoRequestModel todoRequestModel = new TodoRequestModel();

        model.addAttribute("todoRequestModel", todoRequestModel);

        return "index";
    }
    //TODO Requst yolla servislere
    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public String showListOfTodo(Model model){
        String username = userService.giveUserAuthenticationInformation();
        User userId = userService.findByEmail(username);
        String id = userId.getUserId();
        CategoryRequestModel categoryRequestModel = new CategoryRequestModel();
        TodoRequestModel todoRequestModel = new TodoRequestModel();
        model.addAttribute("todos", todoService.showTodoListCurrentUser());
        model.addAttribute("todoRequestModel", todoRequestModel);
        model.addAttribute("categories", categoryService.showCategories());
        model.addAttribute("categoryRequestModel", categoryRequestModel);
        return "about";
    }

    @RequestMapping(path = "/create",method = RequestMethod.GET)
    public String todoPageView(Model model){

        TodoRequestModel todoRequestModel = new TodoRequestModel();

        model.addAttribute("todoRequestModel", todoRequestModel);
        return "create-todo";
    }
    @PostMapping(path = "/category")
    public String createCategory(Model model, CategoryRequestModel categoryRequestModel, BindingResult result){
        model.addAttribute("categoryRequestModel", categoryRequestModel);



        categoryService.createNewCategory(categoryRequestModel);

        return "redirect:/home/list";
    }

    @RequestMapping(path = "/about",method = RequestMethod.GET)
    public String homePageLinkAboutPage(Model model){
        TodoRequestModel todoRequestModel = new TodoRequestModel();
        CategoryRequestModel categoryRequestModel = new CategoryRequestModel();
        model.addAttribute("todoRequestModel", todoRequestModel);
        model.addAttribute("categoryRequestModel", categoryRequestModel);
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
    public String createTodoController(@RequestBody @ModelAttribute("todoRequestModel") TodoRequestModel todoRequestModel,
                                       HttpSession httpSession){
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
    public String updateSpecificTodo(@PathVariable("taskId") String taskId, @ModelAttribute("updateTodoRequestModel") TodoRequestModel todoRequestModel){
        todoRequestModel.setTaskId(taskId);
        TodoDto updateDto = todoService.updateSpecifTask(todoRequestModel);
        return "redirect:/home/list";

    }

    @GetMapping(value = "/finish/{taskId}")
    public String getFinishSpecificTodo(@PathVariable("taskId") String taskId, @ModelAttribute("finishTodoRequestModel") TodoTaskIdRequestModel todoTaskIdRequestModel) throws IOException, MessagingException {
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

    //TODO SHOW THE CURRENT USER TASK
    @GetMapping(value = "/demex")
    public ResponseEntity<List<TodoEntity>> ss(){
        return ResponseEntity.ok().body(todoService.showTodoListCurrentUser());
    }


}
