package projectTodo.backend.controller;

import projectTodo.backend.Todo;
import projectTodo.backend.payload.TodoRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface Controller {
    List<Todo> myTodos = new ArrayList<>();
    TodoRecord add(Todo todo);
    boolean delete(UUID id);
    boolean update(UUID id, TodoRecord todo);
    TodoRecord get(UUID id);
    List<TodoRecord> getAll();
    TodoRecord todoConvertTodoRecord(Todo todo);

    boolean complied(TodoRecord todoRecord);
}
