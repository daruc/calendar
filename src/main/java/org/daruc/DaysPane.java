package org.daruc;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class DaysPane extends GridPane implements SelectedYearChangedListener, SelectedMonthChangedListener {
    private final List<ToggleButton> buttonList = new ArrayList<>();
    private final List<Integer> selectedDayList = new ArrayList<>();
    private SelectedDaysChangedListener selectedDaysChangedListener;
    private int selectedYear;
    private Month selectedMonth;

    public DaysPane() {
        var now = LocalDate.now();
        selectedYear = now.getYear();
        selectedMonth = now.getMonth();
        for (int i = 1; i <= 31; ++i) {
            var button = new ToggleButton(String.valueOf(i));
            buttonList.add(button);
            if (i == now.getDayOfMonth()) {
                button.setSelected(true);
                selectedDayList.add(i);
            }
            button.setOnAction(actionEvent -> {
                if (button.isSelected()) {
                    selectedDayList.add(getDay(button));
                } else {
                    selectedDayList.removeIf(selectedDay -> selectedDay == getDay(button));
                }
                if (selectedDaysChangedListener != null) {
                    selectedDaysChangedListener.onSelectedDaysChanged(selectedDayList);
                }
            });
            add(button, i % 7, i/ 7);
        }
    }

    private int getDay(ToggleButton button) {
        return Integer.parseInt(button.getText());
    }

    public void setSelectedDaysChangedListener(SelectedDaysChangedListener selectedDaysChangedListener) {
        this.selectedDaysChangedListener = selectedDaysChangedListener;
        selectedDaysChangedListener.onSelectedDaysChanged(selectedDayList);
    }

    @Override
    public void onSelectedYearChanged(int year) {
        if (year == selectedYear) {
            return;
        }
        selectedYear = year;
        updateNumberOfDays();
    }

    private void updateNumberOfDays() {
        int days = LocalDate.of(selectedYear, selectedMonth, 1).lengthOfMonth();
        for (int i = 1; i <= 31; ++i) {
            buttonList.get(i - 1).setSelected(false);
        }
        for (int i = 1; i <= days; ++i) {
            buttonList.get(i - 1).setVisible(true);
        }
        for (int i = days + 1; i <= 31; ++i) {
            buttonList.get(i - 1).setVisible(false);
        }
        selectedDayList.removeIf(selectedDay -> selectedDay <= days);
    }

    @Override
    public void onSelectedMonthChanged(Month month) {
        if (selectedMonth == month) {
            return;
        }
        selectedMonth = month;
        updateNumberOfDays();
    }
}
