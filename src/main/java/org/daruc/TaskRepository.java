package org.daruc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final List<Task> taskList = new ArrayList<>();

    public TaskRepository() {
        addTask(new Task(LocalDate.of(2023,8,13), "task1description"));
        addTask(new Task(LocalDate.of(2023,8,14), "task2descriptionA"));
        addTask(new Task(LocalDate.of(2023,8,15), "task2descriptionB"));
        addTask(new Task(LocalDate.of(2023,9,16), "task3description"));
        addTask(new Task(LocalDate.of(2024,9,17), "task3description"));
    }

    public List<Task> getTaskForDate(LocalDate date) {
        return taskList.stream().filter(task -> task.date().equals(date)).toList();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}
