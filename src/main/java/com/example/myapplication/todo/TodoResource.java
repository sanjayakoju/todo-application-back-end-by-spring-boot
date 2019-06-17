package com.example.myapplication.todo;

import java.net.URI;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.myapplication.todo.Todo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoResource {
	
	@Autowired
	private TodoHardcodedService todoHardcodedService;
	
	@Autowired
	private TodoRepository todoRepository;
	
	
	@GetMapping("jpa/user/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username)
	{
		//return todoHardcodedService.findAll();
		return this.todoRepository.findAll();
	}

	@GetMapping("jpa/user/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable int id)
	{
//		return todoHardcodedService.fingById(id);
		return this.todoRepository.findById(id).get();
	}
	
	@DeleteMapping("jpa/user/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable int id)
	{
		//Todo todo=todoHardcodedService.deleteById(id);
		todoRepository.deleteById(id);
//		if(todo!=null)
//		{
			return ResponseEntity.noContent().build();
//		}
//		return ResponseEntity.notFound().build();
	}

	
	@PutMapping("jpa/user/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo)
	{
//		Todo updatedTodo=todoHardcodedService.update(todo);
		Todo updatedTodo=todoRepository.save(todo);
		return new ResponseEntity<Todo>(todo,HttpStatus.OK);
	}
	
	@PostMapping("jpa/user/{username}/todos")
	public ResponseEntity<Void> saveTodo(@PathVariable String username, @RequestBody Todo todo)
	{
		
//		Todo addTodo=todoHardcodedService.update(todo);
		Todo addTodo=todoRepository.save(todo);
		//Location
		//Get current resource url
		//{id}
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(addTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
