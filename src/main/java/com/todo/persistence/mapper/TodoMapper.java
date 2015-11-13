package com.todo.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.todo.persistence.domain.Todo;

public interface TodoMapper extends GenericMapper<Todo> {
	@Select("SELECT id as id, title as title, is_done as done FROM todos")
	public List<Todo> getAll();
	
	@Select("SELECT id as id, title as title, is_done as done FROM todos WHERE id=#{id}")
	public Todo get(Integer id);
	
	@Insert("INSERT INTO todos (title, is_done) VALUES(#{title}, #{done})")
	public Integer insert(Todo todo);

	@Update("UPDATE todos SET title=#{title}, is_done=#{done} WHERE id=#{id}")
	public Integer update(Todo todo);

	@Delete("DELETE FROM todos WHERE id=#{id}")
	public Integer delete(Integer id);
}