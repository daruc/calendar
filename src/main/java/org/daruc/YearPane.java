package org.daruc;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class YearPane extends HBox {
    private final SimpleIntegerProperty selectedYear;
    private final Label yearLabel;
    private List<SelectedYearChangedListener> selectedYearChangedListenerList;

    public YearPane() {
        selectedYear = new SimpleIntegerProperty();
        var decrementYear = new Button("-");
        decrementYear.setOnAction(actionEvent -> setYear(selectedYear.get() - 1));
        yearLabel = new Label();
        var incrementYear = new Button("+");
        incrementYear.setOnAction(actionEvent -> setYear(selectedYear.get() + 1));
        selectedYear.addListener((observableValue, oldValue, newValue) -> yearLabel.setText(newValue.toString()));
        ObservableList<Node> children = getChildren();
        children.add(decrementYear);
        children.add(yearLabel);
        children.add(incrementYear);
    }

    public void setYear(int year) {
        selectedYear.set(year);
        notifySelectedYearChangedListeners();
    }

    private void notifySelectedYearChangedListeners() {
        if (selectedYearChangedListenerList != null) {
            selectedYearChangedListenerList.forEach(listener -> listener.onSelectedYearChanged(selectedYear.get()));
        }
    }

    public void setSelectedYearChangedListener(SelectedYearChangedListener selectedYearChangedListener) {
        if (selectedYearChangedListenerList == null) {
            selectedYearChangedListenerList = new ArrayList<>();
        }
        selectedYearChangedListenerList.add(selectedYearChangedListener);
    }
}
