package org.daruc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TaskRepositoryTest {
    @Test
    void shouldAddAndGetTask() {
        // given
        Task task = new Task(LocalDate.of(2023,8,15), "description");
        TaskRepository taskRepository = new TaskRepository();

        // when
        taskRepository.addTask(task);
        List<Task> taskList = taskRepository.getTaskForDate(LocalDate.of(2023, 8, 15));
        Assertions.assertTrue(taskList.contains(task));
    }
}
