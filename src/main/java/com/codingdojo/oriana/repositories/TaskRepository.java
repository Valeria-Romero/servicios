package com.codingdojo.oriana.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.oriana.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{

	//Lista de Tareas ordenadas por prioridad descendente
	List<Task> findByOrderByPriorityDesc(); //SELECT priority FROM task WHEN 
	
	//Lista de Tareas ordenadas por prioridad ascendente  
	List<Task> findByOrderByPriorityAsc();

	//User findByUsersJoined(Long id);
}
