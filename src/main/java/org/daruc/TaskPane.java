package org.daruc;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class TaskPane extends VBox {
    private final TaskListView taskListView;
    private final TaskRepository taskRepository;

    public TaskPane(TaskListView taskListView, TaskRepository taskRepository) {
        this.taskListView = taskListView;
        this.taskRepository = taskRepository;
        var addTaskButton = new Button("+");
        addTaskButton.setOnAction(actionEvent -> openAddNewTaskDialog());
        getChildren().add(taskListView);
        getChildren().add(addTaskButton);
    }

    private void openAddNewTaskDialog() {
        var addNewTaskDialog = new AddNewTaskDialog();
        Optional<Task> newTask = addNewTaskDialog.showAndWait();
        newTask.ifPresent(task -> {
            taskRepository.addTask(task);
            taskListView.updateTaskList();
        });
    }
}
