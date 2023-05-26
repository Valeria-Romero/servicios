package com.codingdojo.oriana.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.oriana.models.Task;
import com.codingdojo.oriana.models.User;
import com.codingdojo.oriana.repositories.TaskRepository;
import com.codingdojo.oriana.repositories.UserRepository;

@Service
public class AppService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TaskRepository taskRepo;
	
	public User register(User newUser, BindingResult result) {
		
		//Revisamos que el correo que recibimos no exista en nuestra tabla de usuarios
		String email = newUser.getEmail();
		User isUser = userRepo.findByEmail(email); //NULL o Objeto Usuario
		if(isUser != null) {
			//El correo ya está registrado
			//result.rejectValue("email", "Unique", "El correo ingresado ya está en uso");
			result.rejectValue("email", "Unique", "The email is already in use");
		}
		
		//Comparamos contraseñas 
		String password = newUser.getPassword();
		String confirm = newUser.getConfirm();
		if(!password.equals(confirm)) { //! -> Lo contrario
			//result.rejectValue("confirmacion", "Matches", "Las contraseñas no coinciden");
			result.rejectValue("confirmacion", "Matches", "The passwords don´t match");
		}
		
		//Si NO existe error, guardamos!
		if(result.hasErrors()) {
			return null;
		} else {
			//Encriptamos contraseña
			//String contra_encriptada = BCrypt.hashpw(contrasena, BCrypt.gensalt());
			String pass_encript = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(pass_encript);
			return userRepo.save(newUser);
		}
		
		
	}
	
	public User login(String email, String password) {
		
		//Buscar que el correo recibido esté en BD
		User userExists = userRepo.findByEmail(email); //NULL o Objeto de Usuario
		if(userExists == null) {
			return null;
		}
		
		//Comparamos contraseñas
		//BCrypt.checkpw(Contraseña NO encriptada, Contraseña encriptada) -> True o False
		if(BCrypt.checkpw(password, userExists.getPassword())) {
			return userExists;
		} else {
			return null;
		}
	}
	
	/*Guardamos tarea*/
	public Task saveTask(Task task) {
		return taskRepo.save(task);
	}
	
	/*Encontrar un usuario en base a su id*/
	public User findUser(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	
	/*Guarda cambios en usuario*/
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	/*Regresa lista de Tareas ordenadas por prioridad descendente*/
	public List<Task> highPriorityTasks() {
		return taskRepo.findByOrderByPriorityDesc();
	}
	
	/*Regresa lista de Tareas ordenadas por prioridad ascendente*/
	public List<Task> lowPriorityTasks() {
		return taskRepo.findByOrderByPriorityAsc();
	}
	
	/*Regresa un objeto de tarea en base a su id/ REVISAR SI ES NECESARIO*/
	public Task findTask(Long id) {
		return taskRepo.findById(id).orElse(null);
	}
	
	/*Encontrar usuarios*/
	public List<User> findUsers() {
		return userRepo.findAll();
	}
	
}






