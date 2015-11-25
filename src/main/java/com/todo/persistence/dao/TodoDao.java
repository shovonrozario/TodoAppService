package com.todo.persistence.dao;


import com.todo.log.Logger;
import com.todo.log.LoggerFactory;
import com.todo.persistence.domain.Todo;
import com.todo.persistence.mapper.TodoMapper;


public class TodoDao extends GenericDao<Todo, TodoMapper, Integer>{
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoDao.class);
	
	public TodoDao() {
		super(TodoMapper.class);
	}
	
}
