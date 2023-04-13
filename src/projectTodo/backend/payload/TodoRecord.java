package projectTodo.backend.payload;

import projectTodo.backend.enumClasses.CategoryEnum;
import projectTodo.backend.enumClasses.ProiortyEnum;

import java.util.Objects;
import java.util.UUID;

public record TodoRecord(UUID id, String title, ProiortyEnum proiorty, CategoryEnum category, boolean completed) {

    public TodoRecord {
        if (Objects.isNull(id) || Objects.isNull(title)){
            System.err.println("Error");
        }
    }
    @Override
    public String toString() {
        return "Todo => " + title  +
                ", proiorty=" + proiorty +
                ", category=" + category +
                ", completed=" + completed;
    }

}
