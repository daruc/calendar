package org.daruc;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class AddNewTaskDialog extends Dialog<Task> {
    public AddNewTaskDialog() {
        var dialogContent = new VBox();
        var taskDate = new DatePicker(LocalDate.now());
        var taskDescription = new TextField();
        Platform.runLater(taskDescription::requestFocus);
        dialogContent.getChildren().add(taskDate);
        dialogContent.getChildren().add(taskDescription);
        getDialogPane().setContent(dialogContent);
        setResultConverter(buttonType -> {
            if (ButtonType.APPLY.equals(buttonType)) {
                return new Task(taskDate.getValue(), taskDescription.getText());
            }
            return null;
        });
        getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE, ButtonType.APPLY);
    }
}
