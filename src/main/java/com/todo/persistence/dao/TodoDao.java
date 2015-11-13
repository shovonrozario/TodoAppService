package com.todo.persistence.dao;

import org.apache.log4j.Logger;

import com.todo.persistence.domain.Todo;
import com.todo.persistence.mapper.TodoMapper;


public class TodoDao extends GenericDao<Todo, TodoMapper, Integer>{
	private static final Logger LOGGER = Logger.getLogger(TodoDao.class);
	
	public TodoDao() {
		super(TodoMapper.class);
	}
	
	private static void consoleLog(String message) {
		LOGGER.debug("TodoDao: " + message);
	}
}
