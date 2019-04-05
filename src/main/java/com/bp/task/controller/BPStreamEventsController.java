package com.bp.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.task.services.EventCounterService;

@RestController("BPController")
public class BPStreamEventsController {
	@Autowired
	EventCounterService countService;
	
	@GetMapping("/hello")
	public String hello() {
		return "<body><h2>Hello, BP controller is ok...</h2></body>";
	}
	
	@GetMapping("/types")
    public String countTypes(){
        return countService.printTypes();
    }

    @GetMapping("/words")
    public String countWords(){
        return countService.printWords();
    }
}
