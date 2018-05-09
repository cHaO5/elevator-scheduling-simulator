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
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;
import static util.Env.ELEVATOR_NUM;
import static util.Env.FLOOR_NUM;
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
    private JFXButton floor06;
    @FXML
    private JFXButton floor07;
    @FXML
    private JFXButton floor08;
    @FXML
    private JFXButton floor09;
    @FXML
    private JFXButton floor010;
    @FXML
    private JFXButton floor011;
    @FXML
    private JFXButton floor012;
    @FXML
    private JFXButton floor013;
    @FXML
    private JFXButton floor014;
    @FXML
    private JFXButton floor015;
    @FXML
    private JFXButton floor016;
    @FXML
    private JFXButton floor017;
    @FXML
    private JFXButton floor018;
    @FXML
    private JFXButton floor019;
    @FXML
    private JFXButton floor020;

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
    private JFXButton up6;
    @FXML
    private JFXButton up7;
    @FXML
    private JFXButton up8;
    @FXML
    private JFXButton up9;
    @FXML
    private JFXButton up10;
    @FXML
    private JFXButton up11;
    @FXML
    private JFXButton up12;
    @FXML
    private JFXButton up13;
    @FXML
    private JFXButton up14;
    @FXML
    private JFXButton up15;
    @FXML
    private JFXButton up16;
    @FXML
    private JFXButton up17;
    @FXML
    private JFXButton up18;
    @FXML
    private JFXButton up19;
    @FXML
    private JFXButton up20;

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
    @FXML
    private JFXButton down6;
    @FXML
    private JFXButton down7;
    @FXML
    private JFXButton down8;
    @FXML
    private JFXButton down9;
    @FXML
    private JFXButton down10;
    @FXML
    private JFXButton down11;
    @FXML
    private JFXButton down12;
    @FXML
    private JFXButton down13;
    @FXML
    private JFXButton down14;
    @FXML
    private JFXButton down15;
    @FXML
    private JFXButton down16;
    @FXML
    private JFXButton down17;
    @FXML
    private JFXButton down18;
    @FXML
    private JFXButton down19;
    @FXML
    private JFXButton down20;

    private Map<Integer, JFXButton> floorMap = new HashMap<>();
    private Map<Integer, JFXButton> upButtonMap = new HashMap<>();
    private Map<Integer, JFXButton> downButtonMap = new HashMap<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        floorMap.put(1, floor01);
        floorMap.put(2, floor02);
        floorMap.put(3, floor03);
        floorMap.put(4, floor04);
        floorMap.put(5, floor05);
        floorMap.put(6, floor06);
        floorMap.put(7, floor07);
        floorMap.put(8, floor08);
        floorMap.put(9, floor09);
        floorMap.put(10, floor010);
        floorMap.put(11, floor011);
        floorMap.put(12, floor012);
        floorMap.put(13, floor013);
        floorMap.put(14, floor014);
        floorMap.put(15, floor015);
        floorMap.put(16, floor016);
        floorMap.put(17, floor017);
        floorMap.put(18, floor018);
        floorMap.put(19, floor019);
        floorMap.put(20, floor020);

        upButtonMap.put(1, up1);
        upButtonMap.put(2, up2);
        upButtonMap.put(3, up3);
        upButtonMap.put(4, up4);
        upButtonMap.put(5, up5);
        upButtonMap.put(6, up6);
        upButtonMap.put(7, up7);
        upButtonMap.put(8, up8);
        upButtonMap.put(9, up9);
        upButtonMap.put(10, up10);
        upButtonMap.put(11, up11);
        upButtonMap.put(12, up12);
        upButtonMap.put(13, up13);
        upButtonMap.put(14, up14);
        upButtonMap.put(15, up15);
        upButtonMap.put(16, up16);
        upButtonMap.put(17, up17);
        upButtonMap.put(18, up18);
        upButtonMap.put(19, up19);
        upButtonMap.put(20, up20);

        downButtonMap.put(1, down1);
        downButtonMap.put(2, down2);
        downButtonMap.put(3, down3);
        downButtonMap.put(4, down4);
        downButtonMap.put(5, down5);
        downButtonMap.put(6, down6);
        downButtonMap.put(7, down7);
        downButtonMap.put(8, down8);
        downButtonMap.put(9, down9);
        downButtonMap.put(10, down10);
        downButtonMap.put(11, down11);
        downButtonMap.put(12, down12);
        downButtonMap.put(13, down13);
        downButtonMap.put(14, down14);
        downButtonMap.put(15, down15);
        downButtonMap.put(16, down16);
        downButtonMap.put(17, down17);
        downButtonMap.put(18, down18);
        downButtonMap.put(19, down19);
        downButtonMap.put(20, down20);

        new Thread(() -> {
            reflashButton();
        }).start();
    }

    private void reflashButton() {
//        up1.backgroundProperty().bind(getUpButton(1));
        while (true) {
            //System.out.println("reflash");
            for (int i = 1; i <= FLOOR_NUM; i++) {
                if (getUpButton(i) == 1) {
                    upButtonMap.get(i).setStyle("-fx-background-color: #00796B");
                } else {
                    upButtonMap.get(i).setStyle("-fx-background-color: #BDBDBD");
                }
            }

            for (int i = 1; i <= FLOOR_NUM; i++) {
                if (getDownButton(i) == 1) {
                    downButtonMap.get(i).setStyle("-fx-background-color: #00796B");
                } else {
                    downButtonMap.get(i).setStyle("-fx-background-color: #BDBDBD");
                }
            }

            for (int i = 1; i <= FLOOR_NUM; i++) {
                if (i == getCurrElevator()) {
                    floorMap.get(i).setStyle("-fx-background-color: #00796B");
                } else {
                    floorMap.get(i).setStyle("-fx-background-color: #BDBDBD");
                }
            }


        }


    }
    //floorNO.TextProperty.bind()

}
