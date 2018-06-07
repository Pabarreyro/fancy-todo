package models;

import java.util.Objects;

public class Category {
   private String name;
   private int taskCount;
   private int id;

    public Category(String name) { this.name = name; this.taskCount = 0; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public int getTaskCount() { return taskCount; }

    public void setTaskCount(int taskCount) { this.taskCount = taskCount; }
}
