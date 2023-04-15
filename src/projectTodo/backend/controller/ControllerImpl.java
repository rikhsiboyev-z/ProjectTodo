package projectTodo.backend.controller;

import projectTodo.backend.Todo;
import projectTodo.backend.payload.TodoRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ControllerImpl implements Controller{
    private static final Controller controller = new ControllerImpl();
    @Override
    public TodoRecord add(Todo todo) {
       if (!checkingTodo(todo)){
           System.out.println("Error");
           return null;
       }
        myTodos.add(todo);
        return todoConvertTodoRecord(todo);
    }


    @Override
    public boolean delete(UUID id) {
        Todo todoById = findTodoById(id);
        if (Objects.isNull(todoById))
            return false;
        myTodos.remove(todoById);
        return true;
    }

    @Override
    public boolean update(UUID id, TodoRecord todo) {
        Todo todoById = findTodoById(id);
        if (Objects.isNull(todoById)){
            return false;
        }
        todoById.setId(todo.id());
        todoById.setCategory(todo.category());
        todoById.setCompleted(todo.completed());
        todoById.setProiorty(todo.proiorty());
        todoById.setTitle(todo.title());
        return true;
    }

    @Override
    public TodoRecord get(UUID id) {
        Todo todoById = findTodoById(id);
        if (Objects.isNull(todoById))
            return null;
        return todoConvertTodoRecord(todoById);
    }

    @Override
    public List<TodoRecord> getAll() {
        List<TodoRecord> todolist = new ArrayList<>();
        for (Todo myTodo : myTodos) {
                TodoRecord todoRecord = todoConvertTodoRecord(myTodo);
                todolist.add(todoRecord);
        }
        return todolist;
    }

    public static Controller getInstance() {
        return controller;
    }
    public TodoRecord todoConvertTodoRecord(Todo todo) {
        return new TodoRecord(todo.getId(), todo.getTitle(), todo.getProiorty(), todo.getCategory(),todo.isCompleted());
    }

    @Override
    public boolean complied(TodoRecord todoRecord) {
        for (Todo myTodo : myTodos) {
            if (Objects.equals(myTodo.getId(),todoRecord.id())) {
                myTodo.setCompleted(true);
                return true;
            }
        }
        return false;
    }

    private boolean checkingTodo (Todo todo){
        Todo todoById = findTodoById(todo.getId());
        Todo todoByTitle = findTodoByTitle(todo.getTitle());
        return Objects.isNull(todoById) || Objects.isNull(todoByTitle);
    }

    private Todo findTodoByTitle(String title) {
        for (Todo myTodo : myTodos) {
            if (Objects.equals(myTodo.getTitle(), title)){
                return myTodo;
            }
        }
        return null;
    }

    private Todo findTodoById(UUID id) {
        for (Todo myTodo : myTodos) {
            if (Objects.equals(myTodo.getId(), id)){
                return myTodo;
            }
        }
        return null;
    }

}
