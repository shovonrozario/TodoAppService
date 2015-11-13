package com.todo.persistence.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.todo.persistence.config.MyBatisConfigurator;
import com.todo.persistence.mapper.GenericMapper;

public abstract class GenericDao<TDomain, Mapper extends GenericMapper<TDomain>, TPrimaryKey> {
	
	private static final Logger LOGGER = Logger.getLogger(GenericDao.class);
	
	protected SqlSessionFactory sqlSessionFactory = MyBatisConfigurator.getSqlSessionFactory();
		
	private Class<Mapper> mapperClass;
	
	public GenericDao(Class<Mapper> mapperClass) {
		this.mapperClass = mapperClass;
	}
	
	@SuppressWarnings("unchecked")
	public List<TDomain> getAll() {		
		return (List<TDomain>) executeQueryCommand(new IQueryCommand<TDomain, Mapper, TPrimaryKey>() {
			@Override
			public Object execute(Mapper mapper) {
				return mapper.getAll();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public TDomain get(final TPrimaryKey primaryKey) {	
		
		return (TDomain) executeQueryCommand(new IQueryCommand<TDomain, Mapper, TPrimaryKey>() {
			@Override
			public Object execute(Mapper mapper) {
				return mapper.get(primaryKey);
			}
			
		});
	}
	
	public Boolean insert(final TDomain domainObject) {
		
		Integer rowsAffected = (Integer) executeQueryCommand(new IQueryCommand<TDomain, Mapper, TPrimaryKey>() {
			@Override
			public Object execute(Mapper mapper) {
				return mapper.insert(domainObject);
			}
		}, true);
		
		return rowsAffected > 0;
	}
	
	public Boolean update(final TDomain domainObject) {
		
		Integer rowsAffected = (Integer) executeQueryCommand(new IQueryCommand<TDomain, Mapper, TPrimaryKey>() {
			@Override
			public Object execute(Mapper mapper) {
				return mapper.update(domainObject);
			}
		}, true);
		
		return rowsAffected > 0;
	}
	
	public Boolean delete(final TPrimaryKey primaryKey) {
		
		Integer rowsAffected = (Integer) executeQueryCommand(new IQueryCommand<TDomain, Mapper, TPrimaryKey>() {
			@Override
			public Object execute(Mapper mapper) {
				return mapper.delete(primaryKey);
			}
		}, true);
		
		return rowsAffected > 0;
	}
	
	private Object executeQueryCommand(IQueryCommand<TDomain, Mapper, TPrimaryKey> queryCommand) {
		return executeQueryCommand(queryCommand, false);
	}
	
	private Object executeQueryCommand(IQueryCommand<TDomain, Mapper, TPrimaryKey> queryCommand, boolean doCommit) {
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			Mapper mapper = sqlSession.getMapper(mapperClass);
			Object result = queryCommand.execute(mapper);
			
			// This code doesn't handle transaction, needs modification for adding transaction functionality
			if (doCommit) {
				sqlSession.commit();
			}
			
			return result;
		}
	}
	
	private static void consoleLog(String message) {
		LOGGER.debug("GenericDao: " + message);
	}
}