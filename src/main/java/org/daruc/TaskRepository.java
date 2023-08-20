package org.daruc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskRepository {
    private static final String NEW_LINE_REGEX = "\r\n|\n";
    private static final String NEW_LINE = "\r\n";
    private static final String DATE_DESCRIPTION_SEPARATOR = "=";
    private final List<Task> taskList = new ArrayList<>();
    private final Storage storage;

    public TaskRepository(Storage storage) {
        this.storage = storage;
        readFromStorage();
    }

    private void readFromStorage() {
        String[] lines = storage.read().split(NEW_LINE_REGEX);
        for (String line : lines) {
            if (!line.isBlank()) {
                String[] parts = line.split(DATE_DESCRIPTION_SEPARATOR);
                LocalDate date = LocalDate.parse(parts[0], DateTimeFormatter.ISO_LOCAL_DATE);
                Task task = new Task(date, parts[1]);
                taskList.add(task);
            }
        }
    }

    public List<Task> getTaskForDate(LocalDate date) {
        return taskList.stream().filter(task -> task.date().equals(date)).toList();
    }

    public void addTask(Task task) {
        taskList.add(task);
        writeIntoStorage();
    }

    private void writeIntoStorage() {
        String allTasksAsString = taskList.stream()
                .map(task -> task.date() + DATE_DESCRIPTION_SEPARATOR + task.description())
                .collect(Collectors.joining(NEW_LINE, "", NEW_LINE));
        storage.write(allTasksAsString);
    }
}
