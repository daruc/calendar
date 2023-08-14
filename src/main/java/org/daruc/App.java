package org.daruc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        CalendarPane calendarPane = new CalendarPane();
        TaskRepository taskRepository = new TaskRepository();
        TaskListView taskListView = new TaskListView(taskRepository);
        calendarPane.setSelectedDaysChangedListener(taskListView);
        calendarPane.addSelectedMothChangedListener(taskListView);
        calendarPane.setSelectedYearChangedListener(taskListView);
        TaskPane taskPane = new TaskPane(taskListView, taskRepository);
        var scene = new Scene(new VBox(calendarPane, taskPane), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}