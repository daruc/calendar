package org.daruc;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class TaskPane extends VBox {
    public void setTaskList(List<Task> taskList) {
        List<Label> labelList = taskList.stream().map(task -> new Label(task.description())).toList();
        getChildren().clear();
        getChildren().addAll(labelList);
    }
}
