package com.example.myapplication.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HelloWorldController {

	@GetMapping(path = "/hello-world")
	public String helloWorld()
	{
		return "hello world";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public  HelloWorldBean helloWorldBean()
	{
		//throw new RuntimeException("Some error happen");
		return new HelloWorldBean("hello world changed");
	}
	
	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public  HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("hello world, %s", name));
	}


}
