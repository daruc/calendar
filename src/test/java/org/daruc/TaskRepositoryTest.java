package org.daruc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TaskRepositoryTest {
    @Test
    void shouldAddAndGetTask() {
        // given
        var storage = new InMemoryStorage();
        storage.write("2023-08-15=task1_description\r\n2023-08-15=task2_description\r\n");
        TaskRepository taskRepository = new TaskRepository(storage);
        Task task3 = new Task(LocalDate.of(2023,8,15), "task3_description");
        Task task4 = new Task(LocalDate.of(2023, 8, 16), "task4_description");

        // when
        taskRepository.addTask(task3);
        taskRepository.addTask(task4);
        List<Task> taskList = taskRepository.getTaskForDate(LocalDate.of(2023, 8, 15));

        // then
        var task1FromStorage = new Task(LocalDate.of(2023, 8, 15), "task1_description");
        Assertions.assertTrue(taskList.contains(task1FromStorage));
        var task2FromStorage = new Task(LocalDate.of(2023, 8, 15), "task2_description");
        Assertions.assertTrue(taskList.contains(task2FromStorage));
        Assertions.assertTrue(taskList.contains(task3));
        Assertions.assertFalse(taskList.contains(task4));
        String expectedOutputStorageStr = """
                2023-08-15=task1_description
                2023-08-15=task2_description
                2023-08-15=task3_description
                2023-08-16=task4_description
                """;
        String outputStorageStr = storage.read();
        Assertions.assertEquals(
                expectedOutputStorageStr.replaceAll("\r\n", "\n"),
                outputStorageStr.replaceAll("\r\n", "\n")
        );
    }
}
