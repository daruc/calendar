package org.daruc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        var calendarPane = new CalendarPane();
        var storage = new FileStorage("storage.txt");
        var taskRepository = new TaskRepository(storage);
        var taskListView = new TaskListView(taskRepository);
        calendarPane.setSelectedDaysChangedListener(taskListView);
        calendarPane.addSelectedMothChangedListener(taskListView);
        calendarPane.setSelectedYearChangedListener(taskListView);
        var taskPane = new TaskPane(taskListView, taskRepository);
        var scene = new Scene(new VBox(calendarPane, taskPane), 320, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}