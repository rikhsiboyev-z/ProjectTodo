package projectTodo.backend;

import projectTodo.backend.enumClasses.CategoryEnum;
import projectTodo.backend.enumClasses.ProiortyEnum;

import java.util.UUID;

public class Todo {
    private UUID id;
    private String title;
    private ProiortyEnum proiorty = ProiortyEnum.LOW;
    private CategoryEnum category = CategoryEnum.DEFAULT;
    private boolean completed;

    private Todo(UUID id, String title, ProiortyEnum proiorty, CategoryEnum category, boolean completed) {
        this.id = id;
        this.title = title;
        this.proiorty = proiorty;
        this.category = category;
        this.completed = completed;
    }
    public Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private UUID id;
        private String title;
        private ProiortyEnum proiorty = ProiortyEnum.LOW;
        private CategoryEnum category = CategoryEnum.DEFAULT;
        private boolean completed;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder proiorty(ProiortyEnum proiorty) {
            this.proiorty = proiorty;
            return this;
        }

        public Builder category(CategoryEnum category) {
            this.category = category;
            return this;
        }

        public Builder completed(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Todo build(){
            return new Todo(id,title, proiorty, category, completed);
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProiortyEnum getProiorty() {
        return proiorty;
    }

    public void setProiorty(ProiortyEnum proiorty) {
        this.proiorty = proiorty;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", proiorty=" + proiorty +
                ", category=" + category +
                ", completed=" + completed +
                '}';
    }
}
