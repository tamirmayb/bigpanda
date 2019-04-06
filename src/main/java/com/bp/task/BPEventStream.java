package com.bp.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bp.task.models.BpEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BPEventStream implements Runnable {
	final static Logger log = LoggerFactory.getLogger(BPEventStream.class);
    final ObjectMapper jsonMapper = new ObjectMapper();
    
    private InputStream inputStream;
    private Consumer<BpEvent> consumer;
 
    public BPEventStream(InputStream inputStream, Consumer<BpEvent> consumer) {
        this.inputStream = inputStream;
        this.consumer = consumer;
    }
 
    @Override
    public void run() {
        new BufferedReader(new InputStreamReader(inputStream)).lines()
          .map(s->{
        	  BpEvent event = null;
        	  try {
        		  log.info("tring to read event, length = {}", s.length());
        		  event = jsonMapper.readValue(s, BpEvent.class);
        	  } catch(Exception e) {
        		  log.debug("Got bad json exception tring to continue...", e);
        	  }
        	  return event;
          }).forEach(consumer);
    }
}
