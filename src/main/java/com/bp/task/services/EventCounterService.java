package com.bp.task.services;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventCounterService {
	final static Logger log = LoggerFactory.getLogger(EventCounterService.class);
	
	private Map<String, Integer> words = new HashMap<String, Integer>();
	private Map<String, Integer> types = new HashMap<String, Integer>();
	
	public void countWords(String word) {
		Integer wordCount = words.get(word);
		if (wordCount == null) {
			words.put(word, 0);
		}
		else {
			words.put(word, wordCount + 1);
		}
		log.info("The word {} count = {}", word, wordCount);
	}
	
	public void countTypes(String type) {
		Integer count = types.get(type);
		if (count == null) {
			types.put(type, 0);
		}
		else {
			types.put(type, count + 1);
		}
		log.info("Type {} count = {}", type, count);
	}
	
	public String printTypes() {
		
		return printMap(types);
	}
	
	public String printWords() {
		return printMap(words);
	}
	
	private String printMap(Map<String, Integer > counters) {
		StringBuffer stringBuffer = new StringBuffer();
		for(Entry<String, Integer > entry: counters.entrySet()) {
			stringBuffer.append(entry.getKey() + " : " + entry.getValue() +", ");
		}
		return stringBuffer.toString();
	}

}
