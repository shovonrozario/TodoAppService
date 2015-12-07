package com.todo.service.resource.jsonp;

import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.core.Response;

import org.eclipse.persistence.oxm.JSONWithPadding;

import com.todo.log.Logger;
import com.todo.log.LoggerFactory;
import com.todo.persistence.dao.TodoDao;
import com.todo.service.pojo.Todo;

/**
 * Root resource (exposed at "todos" path)
 */
@Path("jsonp/todo")
public class TodoResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoResource.class);
	
	private TodoDao todoDao = new TodoDao();
	
	@GET
	@Produces({ "application/x-javascript"})
	public JSONWithPadding<List<Todo>> getTodos(@QueryParam("callback") String callback) {
		List<com.todo.persistence.domain.Todo> domainTodos = todoDao.getAll();
		List<Todo> pojoTodos = getPojoTodos(domainTodos);
		
		return new JSONWithPadding<List<Todo>>(pojoTodos, callback);
	}
	
	private List<Todo> getPojoTodos(List<com.todo.persistence.domain.Todo> domainTodos) {
		
		List<Todo> pojoTodos = new ArrayList<Todo>();

		for (com.todo.persistence.domain.Todo domainTodo : domainTodos) {
			Todo pojoTodo = getPojoTodo(domainTodo);
			pojoTodos.add(pojoTodo);
		}
		
		return pojoTodos;
	}
	
	@GET
	@Produces({ "application/x-javascript"})
	@Path("/{id}")
	public JSONWithPadding<Todo> getTodo(@PathParam("id") Integer id, @QueryParam("callback") String callback) {
		
		com.todo.persistence.domain.Todo domainTodo = todoDao.get(id);
		Todo pojoTodo = getPojoTodo(domainTodo);
		
		return new JSONWithPadding<Todo>(pojoTodo, callback);
	}	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertTodo(Todo pojoTodo) {
		
		com.todo.persistence.domain.Todo domainTodo = getDomainTodo(pojoTodo);
		Boolean executionSuccesful = todoDao.insert(domainTodo);
		
		if(executionSuccesful)
			return Response.noContent().build();
		
		return Response.status(404).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response updateTodo(@PathParam("id") Integer id, Todo pojoTodo) {
		
		com.todo.persistence.domain.Todo domainTodo = getDomainTodo(pojoTodo);
		Boolean executionSuccesful = todoDao.update(domainTodo);		
		
		if(executionSuccesful)
			return Response.noContent().build();
		
		return Response.status(404).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteTodo(@PathParam("id") Integer id) {
		
		Boolean executionSuccesful = todoDao.delete(id);
		
		if(executionSuccesful)
			return Response.noContent().build();
		
		return Response.status(404).build();
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
	
}
