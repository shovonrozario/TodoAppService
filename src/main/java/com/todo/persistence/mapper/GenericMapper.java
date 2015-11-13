package com.todo.persistence.mapper;

import java.util.List;

public interface GenericMapper<TDomain> {
	
	public List<TDomain> getAll();

	public <TPrimaryKey> TDomain get(TPrimaryKey primaryKey);

	public Integer insert(TDomain domainObject);

	public Integer update(TDomain domainObject);

	public <TPrimaryKey> Integer delete(TPrimaryKey primaryKey);
}