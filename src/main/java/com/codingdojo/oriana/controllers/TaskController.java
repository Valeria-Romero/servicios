package com.codingdojo.oriana.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.oriana.models.Task;
import com.codingdojo.oriana.models.User;
import com.codingdojo.oriana.services.AppService;

@Controller
public class TaskController {
	
	@Autowired
	private AppService service;
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session,
						   Model model) {
		
		/*====Revisa que mi usuario haya iniciado sesión====*/
		User userInMethod = (User)session.getAttribute("userInSession");
		
		if(userInMethod == null) {
			return "redirect:/";
		}
		/*====Revisa que mi usuario haya iniciado sesión====*/
		
		//Lista de Tareas ordenadas por prioridad
		model.addAttribute("taskPriority", service.highPriorityTasks());
		model.addAttribute("taskPriority", service.lowPriorityTasks());
		
		return "dashboard.jsp";
	}
	
	@GetMapping("/new")
	public String newTask(HttpSession session,	
							@ModelAttribute("task") Task task,
							Model model) {
		/*====Revisa que mi usuario haya iniciado sesión====*/
		User userInMethod = (User)session.getAttribute("userInSession");
		
		if(userInMethod == null) {
			return "redirect:/";
		}
		/*====Revisa que mi usuario haya iniciado sesión====*/
		
		model.addAttribute("assignees", service.findUsers());
		
		return "new.jsp";
	}
	
	@PostMapping("/create")
	public String createCourse(@Valid @ModelAttribute("task") Task task,
								BindingResult result,
								HttpSession session) {
		/*====Revisa que mi usuario haya iniciado sesión====*/
		User userInMethod = (User)session.getAttribute("userInSession");
		
		if(userInMethod == null) {
			return "redirect:/";
		}
		/*====Revisa que mi usuario haya iniciado sesión====*/
		
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			//Guardamos la tarea
			Task newTask = service.saveTask(task);
			
			return "redirect:/dashboard";
		}
		
	}
	
	//Método Get que conecta controlador-jsp para ver editar curso, y método Post para guardar
	
	@GetMapping("/edit/{taskId}")
	public String edit(@PathVariable("taskId") Long id,
					   @ModelAttribute("task") Task task,
					   HttpSession session,
					   Model model) {
		/*====Revisa que mi usuario haya iniciado sesión====*/
		User userInMethod = (User)session.getAttribute("userInSession");
		
		if(userInMethod == null) {
			return "redirect:/";
		}
		/*====Revisa que mi usuario haya iniciado sesión====*/
		
		Task taskEdit = service.findTask(id);
		
		//Revisamos que el creador coincida con el usuario en sesion
		if(userInMethod.getId() != taskEdit.getCreator().getId()) {
			return "redirect:/task";
		}
		
		model.addAttribute("task", taskEdit);
		return "edit.jsp";
		
	}
	
	@PutMapping("/update")
	public String update(@Valid @ModelAttribute("task") Task task,
						 BindingResult result,
						 HttpSession session) {
		/*====Revisa que mi usuario haya iniciado sesión====*/
		User userInMethod = (User)session.getAttribute("userInSession");
		
		if(userInMethod == null) {
			return "redirect:/";
		}
		/*====Revisa que mi usuario haya iniciado sesión====*/
		
		//Revisamos que el creador coincida con el usuario en sesion
	
		if(result.hasErrors()) {
			return "edit.jsp";
		} else {
			service.saveTask(task);
			return "redirect:/dashboard";
		}
	
	}
	
	//Método que conecte controlador-jsp para ver detalles del curso 
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") Long id,
			   			HttpSession session,
			   			Model model) {
		
		User userInMethod = (User)session.getAttribute("userInSession");
		
		model.addAttribute("task", service.findTask(id));
		if(userInMethod == null) {
			return "redirect:/";
		}
		
		return "details.jsp";
	}
	
	

}



