package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
public class app {
	@GetMapping("/equal")
	public String equal(@RequestParam(value = "mystring", defaultValue = "0") String name) {
		ExpressionEvaluator test = new ExpressionEvaluator();
		name=name.replace(" ", "+");
		name=name.replace("x", "*");
		String expression = test.infixToPostfix(name);
		
		float value = test.evaluate(expression);
		
		return String.format((value)+ "");
   }
	
	@GetMapping("/percent")
	public String percent(@RequestParam(value = "mystring", defaultValue = "0") String name) {
		
		
		double value = ((double)(Integer.parseInt(name))) / 100 ;
		
		return String.format((value)+ "");
   }
	


	@GetMapping("/frac")
	public String frac(@RequestParam(value = "mystring", defaultValue = "0") String name) {
        double value = (double)(1 /((double)(Integer.parseInt(name)))) ;
		
		return String.format((value)+ "");
   }
	
	@GetMapping("/square")
	public String square(@RequestParam(value = "mystring", defaultValue = "0") String name) {
		
		double value = (double)((Integer.parseInt(name)))*((Integer.parseInt(name))) ;
		return String.format((value)+ "");
   }
	
	@GetMapping("/root")
	public String root(@RequestParam(value = "mystring", defaultValue = "0") String name) {
		
		double value =(double)Math.sqrt((Integer.parseInt(name)));
		return String.format((value)+ "");
   }
}