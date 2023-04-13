package projectTodo.front;

import projectTodo.backend.Todo;
import projectTodo.backend.controller.Controller;
import projectTodo.backend.controller.ControllerImpl;
import projectTodo.backend.enumClasses.CategoryEnum;
import projectTodo.backend.enumClasses.ProiortyEnum;
import projectTodo.backend.payload.TodoRecord;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class FrontUI {
    static Scanner scanner = new Scanner(System.in);
    static Controller controller = new ControllerImpl();
    public static void main(String[] args) {
        while (true){
            mainMenu();
            String mainOption = getConsoleOption("Select => ");
            switch (mainOption) {
                case "1" -> addTodo();
                case "2" -> todoUpdate();
                case "3" -> getTodo();
                case "4" -> getAllTodo();
                case "5" -> complied();
                case "6" -> deleteTodo();
                case "7" -> deleteAllTodo();
                case "q" -> System.exit(0);
            }
        }
    }

    private static void complied() {
        List<TodoRecord> allTodo = controller.getAll();
        for (int i = 0; i < allTodo.size(); i++) {
            if (allTodo.get(i).completed()) {
                System.out.println((i + 1) + " ✅ " + allTodo.get(i));
                return;
            }
            System.out.println((i + 1) + " ⭕ " + allTodo.get(i));
        }
        String compliedOption= getConsoleOption("Select todo for complied  ");
        TodoRecord todoRecord = allTodo.get(Integer.parseInt(compliedOption) - 1);
        boolean complied = controller.complied(todoRecord);
    }

    private static void deleteAllTodo() {
        List<TodoRecord> allTodo = controller.getAll();
        if (!allTodo.isEmpty()) {
            String deleteAllOption = getConsoleOption("Are you sure you want to delete all todos? y/n");
            if (deleteAllOption.equals("y")) {
                for (TodoRecord todoRecord : allTodo) {
                    controller.delete(todoRecord.id());
                }
                System.out.println("Deleted all todo");
            } else {
                System.out.println("Ok not deleted all todo");
            }
        }
        System.out.println("Todo empty");
    }

    private static void deleteTodo() {
        List<TodoRecord> allTodo = controller.getAll();
        for (int i = 0; i < allTodo.size(); i++) {
            if (allTodo.get(i).completed()) {
                System.out.println((i + 1) + " ✅ " + allTodo.get(i));
                return;
            }
            System.out.println((i + 1) + " ⭕ " + allTodo.get(i));
        }
        String deleteIndex = getConsoleOption("Select todo for delete ");
        TodoRecord todoRecord = allTodo.get(Integer.parseInt(deleteIndex) - 1);
        boolean delete = controller.delete(todoRecord.id());
        if (delete) {
            System.out.println("Deleted successfully  ✅✅✅✅");
            return;
        }
        System.out.println("Deleted not successfully \uD83E\uDD26\u200D♂️");
    }

    private static void getAllTodo() {
        List<TodoRecord> allTodo = controller.getAll();
        for (int i = 0; i < allTodo.size(); i++) {
            if (allTodo.get(i).completed()) {
                System.out.println((i + 1) + " ✅ " + allTodo.get(i));
                return;
            }
            System.out.println((i + 1) + " ⭕ " + allTodo.get(i));
        }
    }

    private static void getTodo() {
        List<TodoRecord> allTodo = controller.getAll();
        for (int i = 0; i < allTodo.size(); i++) {
            if (allTodo.get(i).completed()) {
                System.out.println((i + 1) + " ✅ " + allTodo.get(i));
                return;
            }
            System.out.println((i + 1) + " ⭕ " + allTodo.get(i));
        }
        String index = getConsoleOption("Select todo for get ");
        if (allTodo.get(Integer.parseInt(index)-1).completed()) {
            System.out.println((Integer.parseInt(index) - 1 + 1) + " ✅ " + allTodo.get(Integer.parseInt(index) - 1));
            return;
        }
        System.out.println((Integer.parseInt(index)-1 + 1) + " ⭕ " + allTodo.get(Integer.parseInt(index)-1));
    }

    private static void todoUpdate() {
        List<TodoRecord> allTodo = controller.getAll();
        for (int i = 0; i < allTodo.size(); i++) {
            if (allTodo.get(i).completed()) {
                System.out.println((i + 1) + " ✅ " + allTodo.get(i));
                return;
            }
            System.out.println((i + 1) + " ⭕ " + allTodo.get(i));
        }
        String index = getConsoleOption("Select todo for get ");
        TodoRecord todoRecord = allTodo.get(Integer.parseInt(index) - 1);
        UUID id = todoRecord.id();
        Todo todo = enterTodo(id);
        TodoRecord todoRecord1 = controller.todoConvertTodoRecord(todo);
        boolean update = controller.update(id, todoRecord1);
        if (update) {
            System.out.println("Updated");
            return;
        }
        System.out.println("Error updating");
    }

    private static Todo enterTodo(UUID id) {
        Todo todo = new Todo.Builder().build();
        todo.setId(id);
        String title = getConsoleOption("Enter new todo title => ");
        todo.setTitle(title);
        System.out.println("Todo proiorty");
        System.out.println("1 => LOW");
        System.out.println("2 => MEDIUM");
        System.out.println("3 => HIGH");
        String proiortyOption = getConsoleOption("Select a new todo proiorty ");
        switch (proiortyOption) {
            case "2" -> todo.setProiorty(ProiortyEnum.MEDIUM);
            case "3" -> todo.setProiorty(ProiortyEnum.HIGH);
            default -> todo.setProiorty(ProiortyEnum.LOW);
        }
        System.out.println("Todo category");
        System.out.println("1 => WORK");
        System.out.println("2 => STUDY");
        System.out.println("3 => ENTERTAINMENT");
        System.out.println("4 => DEFAULT");
        String categoryOption = getConsoleOption("Select a new todo category ");
        switch (categoryOption){
            case "1" : todo.setCategory(CategoryEnum.WORK);
            case "2" : todo.setCategory(CategoryEnum.STUDY);
            case "3" : todo.setCategory(CategoryEnum.ENTERTAINMENT);
            default:todo.setCategory(CategoryEnum.DEFAULT);
        }
        todo.setCompleted(false);
        return todo;
    }

    private static void addTodo() {
        UUID uuid = UUID.randomUUID();
        Todo todo = enterTodo(uuid);
        TodoRecord todoRecord = controller.add(todo);
        if (Objects.isNull(todoRecord)) {
            System.out.println("You cannot enter more than one id or title!!!");
            return;
        }
        System.out.println("Added successfully");
    }

    private static String getConsoleOption(String str) {
        System.out.print(str);
        return scanner.nextLine();
    }

    private static void mainMenu() {
        System.out.println("1 => Add todo");
        System.out.println("2 => Update todo");
        System.out.println("3 => Get todo");
        System.out.println("4 => Get all todo");
        System.out.println("5 => Complied");
        System.out.println("6 => Delete todo");
        System.out.println("7 => Delete all todo");
        System.out.println("q => Exit");
    }
}



