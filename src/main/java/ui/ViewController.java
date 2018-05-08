package ui;

import com.jfoenix.controls.JFXButton;
import com.sun.javaws.util.JfxHelper;
import domain.Elevator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import static util.Resource.*;

public class ViewController implements Initializable {
    @FXML
    private JFXButton floor01;
    @FXML
    private JFXButton floor02;
    @FXML
    private JFXButton floor03;
    @FXML
    private JFXButton floor04;
    @FXML
    private JFXButton floor05;
    @FXML
    private JFXButton up1;
    @FXML
    private JFXButton up2;
    @FXML
    private JFXButton down1;
    @FXML
    private JFXButton down2;
    @FXML
    private Label floorNo;

    Elevator elevator;

    private static int preFloor;

//    public static void reflashElevator(int currFloor) {
//        takeBack();
//    }

//    public void changeElevator(int preFloor, int currFloor) {
//        if (preFloor == 1 && currFloor == 2) {
//            floor01.setStyle("-fx-background-color: #BDBDBD");
//            floor02.setStyle("-fx-background-color:  #4DB6AC");
//        }
//
//    }

    public void takeBack() {};
    public static int getPreFloor() {
        return preFloor;
    }

    public void setPreFloor(int preFloor) {
        this.preFloor = preFloor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    //floorNO.TextProperty.bind()
}
