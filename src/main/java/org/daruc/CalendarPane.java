package org.daruc;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalendarPane extends GridPane {

    private final Set<Integer> selectedDays = new HashSet<>();
    private final TaskRepository taskRepository;
    private final TaskPane taskPane;

    public CalendarPane(TaskRepository taskRepository, TaskPane taskPane) {
        this.taskRepository = taskRepository;
        this.taskPane = taskPane;
        for (int i = 1; i <= 31; ++i) {
            var button = new ToggleButton(String.valueOf(i));
            button.setOnAction(actionEvent -> {
                if (button.isSelected()) {
                    selectedDays.add(Integer.valueOf(button.getText()));
                } else {
                    selectedDays.remove(Integer.valueOf(button.getText()));
                }
                showTaskListForSelectedDays();
            });
            add(button, i % 7, i/ 7);
        }
    }

    private void showTaskListForSelectedDays() {
        List<Task> taskList = selectedDays.stream()
                .map(day -> taskRepository.getTaskForDate(day))
                .flatMap(Collection::stream)
                .toList();

        taskPane.setTaskList(taskList);
    }
}
