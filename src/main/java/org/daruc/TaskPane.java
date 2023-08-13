package org.daruc;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.List;

public class TaskPane extends ListView<Task> implements SelectedYearChangedListener, SelectedMonthChangedListener,
        SelectedDaysChangedListener {

    private final TaskRepository taskRepository;

    private List<Integer> allSelectedDays;
    private Month selectedMonth;
    private int selectedYear;
    public TaskPane(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        var now = LocalDate.now();
        selectedMonth = now.getMonth();
        selectedYear = now.getYear();
        allSelectedDays = List.of();
    }

    @Override
    public void onSelectedYearChanged(int year) {
        selectedYear = year;
        updateTaskList();
    }

    @Override
    public void onSelectedMonthChanged(Month month) {
        selectedMonth = month;
        updateTaskList();
    }

    @Override
    public void onSelectedDaysChanged(List<Integer> allSelectedDays) {
        this.allSelectedDays = allSelectedDays;
        updateTaskList();
    }

    private void updateTaskList() {
        List<Task> taskList = allSelectedDays.stream()
                .map(this::toCompleteDate)
                .map(taskRepository::getTaskForDate)
                .flatMap(Collection::stream)
                .toList();
        setTaskList(taskList);
    }

    private LocalDate toCompleteDate(Integer day) {
        return LocalDate.of(selectedYear, selectedMonth, day);
    }

    private void setTaskList(List<Task> taskList) {
        setItems(FXCollections.observableList(taskList));
    }
}
