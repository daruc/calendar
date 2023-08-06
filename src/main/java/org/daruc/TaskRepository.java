package org.daruc;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final List<Task> taskList = new ArrayList<>();

    public TaskRepository() {
        addTask(new Task(1, "task1description"));
        addTask(new Task(2, "task2descriptionA"));
        addTask(new Task(2, "task2descriptionB"));
    }

    public List<Task> getTaskForDate(int date) {
        return taskList.stream().filter(task -> task.date() == date).toList();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}
