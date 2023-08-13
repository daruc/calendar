package org.daruc;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class CalendarPane extends VBox {

    private final YearPane yearPane;
    private final DaysPane daysPane;
    private final ComboBox<Month> monthComboBox;
    private List<SelectedMonthChangedListener> selectedMonthChangedListenerList;

    public CalendarPane() {
        daysPane = new DaysPane();
        addSelectedMothChangedListener(daysPane);
        var now = LocalDate.now();
        yearPane = new YearPane();
        yearPane.setSelectedYearChangedListener(daysPane);
        yearPane.setYear(now.getYear());

        getChildren().add(yearPane);
        monthComboBox = new ComboBox<>();
        monthComboBox.setValue(now.getMonth());
        monthComboBox.setItems(FXCollections.observableList(Arrays.stream(Month.values()).toList()));
        monthComboBox.setOnAction(actionEvent -> {
            if (selectedMonthChangedListenerList != null) {
                selectedMonthChangedListenerList.forEach(
                        listener -> listener.onSelectedMonthChanged(monthComboBox.getValue()
                        ));
            }
        });
        getChildren().add(monthComboBox);

        getChildren().add(daysPane);
    }

    public void setSelectedYearChangedListener(SelectedYearChangedListener selectedYearChangedListener) {
        yearPane.setSelectedYearChangedListener(selectedYearChangedListener);
    }

    public void addSelectedMothChangedListener(SelectedMonthChangedListener selectedMothChangedListener) {
        if (selectedMonthChangedListenerList == null) {
            selectedMonthChangedListenerList = new ArrayList<>();
        }
        selectedMonthChangedListenerList.add(selectedMothChangedListener);
    }

    public void setSelectedDaysChangedListener(SelectedDaysChangedListener selectedDaysChangedListener) {
        daysPane.setSelectedDaysChangedListener(selectedDaysChangedListener);
    }
}
