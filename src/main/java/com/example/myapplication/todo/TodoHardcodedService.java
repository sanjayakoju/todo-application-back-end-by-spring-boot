package com.example.myapplication.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {

	private static List<Todo> todos=new ArrayList();
	private static int idCounter=0;
	
	static
	{
		todos.add(new Todo(++idCounter,"admin","learn to dance",new Date(),false));
		todos.add(new Todo(++idCounter,"admin","learn to write",new Date(),false));
		todos.add(new Todo(++idCounter,"admin","learn to code",new Date(),true));
		todos.add(new Todo(++idCounter,"s","visiting soon",new Date(),false));
		todos.add(new Todo(++idCounter,"deependra","hardcoded",new Date(),true));
	}
	
	public List<Todo> findAll()
	{
		return todos;
	}
	
	public Todo deleteById(int id)
	{
		Todo todo=fingById(id);
		
		if(todo==null) return null;
		if(todos.remove(todo))
		{
			return todo;
		}
		return null;
	}

	public Todo fingById(int id) {
		for(Todo todo:todos)
		{
			if(todo.getId()==id)
			{
				return todo;
			}
		}
		return null;
	}
	
	public Todo update(Todo todo)
	{
		if(todo.getId()==-1 || todo.getId()==0)
		{
			todo.setId(++idCounter);
			todos.add(todo);
		}
		else
		{
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
}
