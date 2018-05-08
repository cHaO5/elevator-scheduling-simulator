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

import static java.lang.Thread.sleep;
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
    private JFXButton up3;
    @FXML
    private JFXButton up4;
    @FXML
    private JFXButton up5;
    @FXML
    private JFXButton down1;
    @FXML
    private JFXButton down2;
    @FXML
    private JFXButton down3;
    @FXML
    private JFXButton down4;
    @FXML
    private JFXButton down5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> {
            reflashButton();
        }).start();
    }

    private void reflashButton() {
        while (true) {
            System.out.println("reflash");
            if (getUpButton(0) == 1) {
                up1.setStyle("-fx-background-color: #00796B");
            } else {
                up1.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getUpButton(1) == 1) {
                up2.setStyle("-fx-background-color: #00796B");
            } else {
                up2.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getUpButton(2) == 1) {
                up3.setStyle("-fx-background-color: #00796B");
            } else {
                up3.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getUpButton(3) == 1) {
                up4.setStyle("-fx-background-color: #00796B");
            } else {
                up4.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getUpButton(4) == 1) {
                up5.setStyle("-fx-background-color: #00796B");
            } else {
                up5.setStyle("-fx-background-color: #BDBDBD");
            }

            if (getDownButton(0) == 1) {
                down1.setStyle("-fx-background-color: #00796B");
            } else {
                down1.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getDownButton(1) == 1) {
                down2.setStyle("-fx-background-color: #00796B");
            } else {
                down2.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getDownButton(2) == 1) {
                down3.setStyle("-fx-background-color: #00796B");
            } else {
                down3.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getDownButton(3) == 1) {
                down4.setStyle("-fx-background-color: #00796B");
            } else {
                down4.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getDownButton(4) == 1) {
                down5.setStyle("-fx-background-color: #00796B");
            } else {
                down5.setStyle("-fx-background-color: #BDBDBD");
            }

            if (getElevator(0) == 1) {
                floor01.setStyle("-fx-background-color: #00796B");
            } else {
                floor01.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getElevator(1) == 1) {
                floor02.setStyle("-fx-background-color: #00796B");
            } else {
                floor02.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getElevator(2) == 1) {
                floor03.setStyle("-fx-background-color: #00796B");
            } else {
                floor03.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getElevator(3) == 1) {
                floor04.setStyle("-fx-background-color: #00796B");
            } else {
                floor04.setStyle("-fx-background-color: #BDBDBD");
            }
            if (getElevator(4) == 1) {
                floor05.setStyle("-fx-background-color: #00796B");
            } else {
                floor05.setStyle("-fx-background-color: #BDBDBD");
            }
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    //floorNO.TextProperty.bind()

}
