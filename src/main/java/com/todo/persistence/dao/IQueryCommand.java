package com.todo.persistence.dao;

import com.todo.persistence.mapper.GenericMapper;

public interface IQueryCommand<TDomain, Mapper extends GenericMapper<TDomain>, TPrimaryKey>{
	public Object execute(Mapper mapper);
}
