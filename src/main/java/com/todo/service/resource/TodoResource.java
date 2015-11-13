package com.todo.service.resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.eclipse.persistence.oxm.JSONWithPadding;

import com.todo.persistence.dao.TodoDao;
import com.todo.service.pojo.Todo;

/**
 * Root resource (exposed at "todos" path)
 */
@Singleton
@Path("todo")
public class TodoResource {
	private static final Logger LOGGER = Logger.getLogger(TodoResource.class);
	
	private TodoDao todoDao = new TodoDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JSONWithPadding<List<Todo>> getTodos(@QueryParam("callback") String callback) {
		
		List<com.todo.persistence.domain.Todo> domainTodos = todoDao.getAll();
		List<Todo> pojoTodos = getPojoTodos(domainTodos);
		
		return new JSONWithPadding<List<Todo>>(pojoTodos, callback);
	}
	
	private List<Todo> getPojoTodos(List<com.todo.persistence.domain.Todo> domainTodos) {
		
		List<Todo> pojoTodos = new ArrayList<Todo>();

		for (com.todo.persistence.domain.Todo todo : domainTodos) {
			Todo tmpTodo = new Todo();
			tmpTodo.setId(todo.getId());
			tmpTodo.setTitle(todo.getTitle());
			tmpTodo.setDone(todo.isDone());
			pojoTodos.add(tmpTodo);
		}
		
		return pojoTodos;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONWithPadding<Todo> getTodo(@PathParam("id") Integer id, @QueryParam("callback") String callback) {
		
		com.todo.persistence.domain.Todo domainTodo = todoDao.get(id);
		Todo pojoTodo = getPojoTodo(domainTodo);
		
		return new JSONWithPadding<Todo>(pojoTodo);
	}	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertTodo(Todo pojoTodo) {
		
		com.todo.persistence.domain.Todo domainTodo = getDomainTodo(pojoTodo);
		Boolean result = todoDao.insert(domainTodo);
		
		return result.toString();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}")
	public String updateTodo(@PathParam("id") Integer id, Todo pojoTodo) {
		
		com.todo.persistence.domain.Todo domainTodo = getDomainTodo(pojoTodo);
		Boolean result = todoDao.update(domainTodo);
		
		return result.toString();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTodo(@PathParam("id") Integer id) {
		
		Boolean result = todoDao.delete(id);
		
		return result.toString();
	}
	
	private Todo getPojoTodo(com.todo.persistence.domain.Todo domainTodo) {
		
		Todo pojoTodo = new Todo();
		pojoTodo.setId(domainTodo.getId());
		pojoTodo.setTitle(domainTodo.getTitle());
		pojoTodo.setDone(domainTodo.isDone());
		
		return pojoTodo;
	}
	
	private com.todo.persistence.domain.Todo getDomainTodo(Todo pojoTodo) {
		
		com.todo.persistence.domain.Todo domainTodo = new com.todo.persistence.domain.Todo();
		domainTodo.setId(pojoTodo.getId());
		domainTodo.setTitle(pojoTodo.getTitle());
		domainTodo.setDone(pojoTodo.isDone());
		
		return domainTodo;
	}
	
	private static void consoleLog(String message) {
		LOGGER.debug("TodoResource: " + message);
	}
	
}
