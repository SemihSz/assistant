package com.spring.assistant.assistant.pagination;

import com.spring.assistant.assistant.interfaces.service.GetUserIdService;
import com.spring.assistant.assistant.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author semih on Aralık, 2019, 15.12.2019, 22:41:29
 */
@Controller
public class CustomerController {


	private TodoRepository todoRepository;

	private CustomerRepo customerRepository;

	@Autowired
	private GetUserIdService getUserIdService;

	public CustomerController(CustomerRepo customerRepository, TodoRepository todoRepository) {
		this.customerRepository = customerRepository;
		this.todoRepository = todoRepository;
	}

	@GetMapping("/customers")
	public String customersPage(HttpServletRequest request, Model model) {
		//Todo user size ve sort type al.
		int page = 0; //default page number is 0 (yes it is weird)
		int size = 5; //default page size is 10

		if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
			page = Integer.parseInt(request.getParameter("page")) - 1;
		}

		if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
			size = Integer.parseInt(request.getParameter("size"));
		}

		model.addAttribute("customers", todoRepository.pages(getUserIdService.getUserId(), PageRequest.of(page, size, Sort.by("category"))));
		return "pagi";
	}
}