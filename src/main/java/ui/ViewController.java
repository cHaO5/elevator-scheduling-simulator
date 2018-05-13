package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import domain.Elevator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static domain.enumeration.Direction.DOWN;
import static domain.enumeration.Direction.UP;
import static java.lang.Thread.sleep;
import static util.Env.ELEVATOR_NUM;
import static util.Env.FLOOR_NUM;
import static util.Resource.*;

public class ViewController implements Initializable {
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

    @FXML
    private JFXButton elevator0;
    @FXML
    private JFXButton elevator1;
    @FXML
    private JFXButton elevator2;
    @FXML
    private JFXButton elevator3;
    @FXML
    private JFXButton elevator4;

    @FXML
    private JFXButton userUpButton;
    @FXML
    private JFXButton userDownButton;
    @FXML
    private Label userFloorDisplay;
    @FXML
    private Label elevatorDisplay;
    @FXML
    private javafx.scene.image.ImageView elevatorUp;
    @FXML
    private javafx.scene.image.ImageView elevatorDown;
    @FXML
    private JFXDialog finishDialog;
    @FXML
    private JFXButton bt1;
    @FXML
    private JFXButton bt2;
    @FXML
    private JFXButton bt3;
    @FXML
    private JFXButton bt4;
    @FXML
    private JFXButton bt5;
    @FXML
    private JFXButton bt6;
    @FXML
    private JFXButton bt7;
    @FXML
    private JFXButton bt8;
    @FXML
    private JFXButton bt9;
    @FXML
    private JFXButton bt10;
    @FXML
    private JFXButton bt11;
    @FXML
    private JFXButton bt12;
    @FXML
    private JFXButton bt13;
    @FXML
    private JFXButton bt14;
    @FXML
    private JFXButton bt15;
    @FXML
    private JFXButton bt16;
    @FXML
    private JFXButton bt17;
    @FXML
    private JFXButton bt18;
    @FXML
    private JFXButton bt19;
    @FXML
    private JFXButton bt20;

    @FXML
    private JFXButton display;
    @FXML
    private JFXTextField userInputFloor;
    @FXML
    private javafx.scene.image.ImageView finish;


//    private Map<Integer, JFXButton> floorMap = new HashMap<>();
    private Map<Integer, JFXButton> upButtonMap = new HashMap<>();
    private Map<Integer, JFXButton> downButtonMap = new HashMap<>();
    private Map<Integer, JFXButton> elevatorButtonMap = new HashMap<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        finish.setVisible(false);
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

        elevatorButtonMap.put(1, bt1);
        elevatorButtonMap.put(2, bt2);
        elevatorButtonMap.put(3, bt3);
        elevatorButtonMap.put(4, bt4);
        elevatorButtonMap.put(5, bt5);
        elevatorButtonMap.put(6, bt6);
        elevatorButtonMap.put(7, bt7);
        elevatorButtonMap.put(8, bt8);
        elevatorButtonMap.put(9, bt9);
        elevatorButtonMap.put(10, bt10);
        elevatorButtonMap.put(11, bt11);
        elevatorButtonMap.put(12, bt12);
        elevatorButtonMap.put(13, bt13);
        elevatorButtonMap.put(14, bt14);
        elevatorButtonMap.put(15, bt15);
        elevatorButtonMap.put(16, bt16);
        elevatorButtonMap.put(17, bt17);
        elevatorButtonMap.put(18, bt18);
        elevatorButtonMap.put(19, bt19);
        elevatorButtonMap.put(20, bt20);

        userInputFloor.focusedProperty().addListener((o, oldVal, newVal) -> {
            userFloorNo = Integer.parseInt(userInputFloor.getText());
            System.out.println("vip input the floor " + userFloorNo + "");
            finish.setVisible(false);
            //floorPressed = true;
            //input = true;
        });

        //userFloorDisplay.textProperty().bind(userFloorNo);
        new Thread(() -> {
            refreshButton();
        }).start();
    }

    private void refreshButton() {

//        up1.backgroundProperty().bind(getUpButton(1));
        while (true) {
            //System.out.println("refresh");
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
            elevator0.setLayoutY(540 - (getElevator(0) - 1) * 28);
            elevator1.setLayoutY(540 - (getElevator(1) - 1) * 28);
            elevator2.setLayoutY(540 - (getElevator(2) - 1) * 28);
            elevator3.setLayoutY(540 - (getElevator(3) - 1) * 28);
            elevator4.setLayoutY(540 - (getElevator(4) - 1) * 28);

            if (recover) {
                userUpButton.setStyle("-fx-background-color: #4DB6AC");
                userDownButton.setStyle("-fx-background-color: #4DB6AC");

                for (int i = 1; i <= FLOOR_NUM; i++) {
                    elevatorButtonMap.get(i).setStyle("-fx-background-color: #009688");
                }
                recover = false;
                if (!errorInput) finish.setVisible(true);
                System.out.println("finish is set visible");
            }
//            elevatorUp.setVisible(elevatorDirection == UP);
//            elevatorDown.setVisible(elevatorDirection == DOWN);
        }



    }


//    @FXML
//    void button1() { userTarget.add(1); bt1.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button2() { userTarget.add(2); bt2.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button3() { userTarget.add(3); bt3.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button4() { userTarget.add(4); bt4.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button5() { userTarget.add(5); bt5.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button6() { userTarget.add(6); bt6.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button7() { userTarget.add(7); bt7.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button8() { userTarget.add(8); bt8.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button9() { userTarget.add(9); bt9.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button10() { userTarget.add(10); bt10.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button11() { userTarget.add(11); bt11.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button12() { userTarget.add(12); bt12.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button13() { userTarget.add(13); bt13.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button14() { userTarget.add(14); bt14.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button15() { userTarget.add(15); bt15.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button16() { userTarget.add(16); bt16.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button17() { userTarget.add(17); bt17.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button18() { userTarget.add(18); bt18.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button19() { userTarget.add(19); bt19.setStyle("-fx-background-color: #BDBDBD"); }
//    @FXML
//    void button20() { userTarget.add(20); bt20.setStyle("-fx-background-color: #BDBDBD"); }

    @FXML
    private void button1() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 1;
            bt1.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }

    @FXML
    private void button2() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 2;
            bt2.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button3() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 3;
            bt3.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button4() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 4;
            bt4.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button5() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 5;
            bt5.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button6() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 6;
            bt6.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button7() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 7;
            bt7.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button8() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 8;
            bt8.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button9() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 9;
            bt9.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button10() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 10;
            bt10.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;


            //elevatorDisplay.setText(10 + "");
        }
    }
    @FXML
    private void button11() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 11;
            bt11.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button12() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 12;
            bt12.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button13() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 13;
            bt13.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button14() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 14;
            bt14.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button15() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 15;
            bt15.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button16() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 16;
            bt16.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button17() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 17;
            bt17.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button18() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 18;
            bt18.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button19() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 19;
            bt19.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void button20() {
        if (floorPressed && !elevatorPressed) {
            userTarget = 20;
            bt20.setStyle("-fx-background-color: #B2DFDB");
            elevatorPressed = true;
        }
    }
    @FXML
    private void userUp() {
        if (!input && !floorPressed) {
            userDirection = UP;
            userUpButton.setStyle("-fx-background-color: #B2DFDB");
            floorPressed = true;
            input = true;
        }
    }
    @FXML
    private void userDown() {
        if (!input && !floorPressed) {
            userDirection = DOWN;
            userDownButton.setStyle("-fx-background-color: #B2DFDB");
            floorPressed = true;
            input = true;
        }
    }

}
