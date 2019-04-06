package com.bp.task.services;

import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bp.task.BPEventStream;

@Component
public class EventProcessingService {
	final static Logger log = LoggerFactory.getLogger(EventProcessingService.class);

	@Value("${generator_path}")
	private String path;
	
	@Autowired
	public EventCounterService eventCounterService;
	
	@PostConstruct
	public void init() {
		try {
			log.info("Reading data from generator, path = {}", path);
			Process process = Runtime.getRuntime().exec(path);

			BPEventStream eventStream = 
					new BPEventStream(process.getInputStream(), event-> {
						if(event != null) {
							log.info("counting types of {}", event.getEvent_type());
							eventCounterService.countTypes(event.getEvent_type());
							eventCounterService.countWords(event.getData());
						} else {
							log.debug("found an empty event");
						}
					});
			Executors.newSingleThreadExecutor().submit(eventStream);
		} catch(Exception e) {
			log.error("Error in init {}", e)
		}
	}
}
