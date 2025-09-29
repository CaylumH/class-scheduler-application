package Client_22356363_22344977;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class Client_22356363_22344977 extends Application {
    static InetAddress host;
    static Socket link = null;
    static PrintWriter out;
    static BufferedReader in;

    static {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {

            System.out.println("Host ID not found!");
            System.exit(1);
        }
    }

    static final int PORT = 1234;
    Label label = new Label("Response From Server Will Display Here");
    String[] calendar = {"Mon", "Tues", "Wed", "Thur", "Fri"};
    String[] timetable = {"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};

    ChoiceBox calendarBox = new ChoiceBox(FXCollections.observableArrayList(calendar));
    RadioButton addButton = new RadioButton("ADD CLASS");
    RadioButton removeButton = new RadioButton("REMOVE CLASS");
    RadioButton displayButton = new RadioButton("DISPLAY SCHEDULE");
    RadioButton earlyLectureButton = new RadioButton("EARLY LECTURE");
    ToggleGroup radioGroup = new ToggleGroup();
    Label scheduleLabel = new Label();

    @Override
    public void init() {
        try {
            link = new Socket(host, PORT);
            in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            out = new PrintWriter(link.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Can NOT connect to server");
            System.exit(1);
        }
    }

    public String getRadioButtonOption() {
        if (radioGroup.getSelectedToggle() == addButton) {
            return "ADD";
        } else if (radioGroup.getSelectedToggle() == removeButton) {
            return "REMOVE";
        } else if (radioGroup.getSelectedToggle() == displayButton) {
            return "DISPLAY";
        } else if (radioGroup.getSelectedToggle() == earlyLectureButton) {
            return "EARLY";
        }
        return "EMPTY";
    }


    void displaySchedulePage(String response) {
        StringBuilder timetableDisplay = new StringBuilder();
        Label title = new Label("Bookings:");
        title.setStyle("-fx-font-weight: bold");
        String[] timetable = response.split("/");

        Stage scheduleStage = new Stage();
        GridPane schedulePage = new GridPane();
        schedulePage.setGridLinesVisible(true);
        schedulePage.setPadding(new Insets(10));

        Label mondayLabel = new Label("MONDAY");
        Label tuesdayLabel = new Label("TUESDAY");
        Label wednesdayLabel = new Label("WEDNESDAY");
        Label thursdayLabel = new Label("THURSDAY");
        Label fridayLabel = new Label("FRIDAY");

        mondayLabel.setStyle("-fx-font-weight: bold");
        tuesdayLabel.setStyle("-fx-font-weight: bold");
        wednesdayLabel.setStyle("-fx-font-weight: bold");
        thursdayLabel.setStyle("-fx-font-weight: bold");
        fridayLabel.setStyle("-fx-font-weight: bold");

        mondayLabel.setMinSize(80, 30);
        tuesdayLabel.setMinSize(80, 30);
        wednesdayLabel.setMinSize(80, 30);
        thursdayLabel.setMinSize(80, 30);
        fridayLabel.setMinSize(80, 30);

        mondayLabel.setAlignment(Pos.CENTER);
        tuesdayLabel.setAlignment(Pos.CENTER);
        wednesdayLabel.setAlignment(Pos.CENTER);
        thursdayLabel.setAlignment(Pos.CENTER);
        fridayLabel.setAlignment(Pos.CENTER);

        Label nineLabel = new Label("9:00");
        Label tenLabel = new Label("10:00");
        Label elevenLabel = new Label("11:00");
        Label twelveLabel = new Label("12:00");
        Label oneLabel = new Label("1:00");
        Label twoLabel = new Label("2:00");
        Label threeLabel = new Label("3:00");
        Label fourLabel = new Label("4:00");
        Label fiveLabel = new Label("5:00");
        Label sixLabel = new Label("6:00");

        nineLabel.setStyle("-fx-font-weight: bold");
        tenLabel.setStyle("-fx-font-weight: bold");
        elevenLabel.setStyle("-fx-font-weight: bold");
        twelveLabel.setStyle("-fx-font-weight: bold");
        oneLabel.setStyle("-fx-font-weight: bold");
        twoLabel.setStyle("-fx-font-weight: bold");
        threeLabel.setStyle("-fx-font-weight: bold");
        fourLabel.setStyle("-fx-font-weight: bold");
        fiveLabel.setStyle("-fx-font-weight: bold");
        sixLabel.setStyle("-fx-font-weight: bold");

        nineLabel.setMinSize(50, 40);
        tenLabel.setMinSize(50, 40);
        elevenLabel.setMinSize(50, 40);
        twelveLabel.setMinSize(50, 40);
        oneLabel.setMinSize(50, 40);
        twoLabel.setMinSize(50, 40);
        threeLabel.setMinSize(50, 40);
        fourLabel.setMinSize(50, 40);
        fiveLabel.setMinSize(50, 40);
        sixLabel.setMinSize(50, 40);

        nineLabel.setAlignment(Pos.CENTER);
        tenLabel.setAlignment(Pos.CENTER);
        elevenLabel.setAlignment(Pos.CENTER);
        twelveLabel.setAlignment(Pos.CENTER);
        oneLabel.setAlignment(Pos.CENTER);
        twoLabel.setAlignment(Pos.CENTER);
        threeLabel.setAlignment(Pos.CENTER);
        fourLabel.setAlignment(Pos.CENTER);
        fiveLabel.setAlignment(Pos.CENTER);
        sixLabel.setAlignment(Pos.CENTER);

        schedulePage.add(mondayLabel, 1, 0);
        schedulePage.add(tuesdayLabel, 2, 0);
        schedulePage.add(wednesdayLabel, 3, 0);
        schedulePage.add(thursdayLabel, 4, 0);
        schedulePage.add(fridayLabel, 5, 0);

        schedulePage.add(nineLabel, 0, 1);
        schedulePage.add(tenLabel, 0, 2);
        schedulePage.add(elevenLabel, 0, 3);
        schedulePage.add(twelveLabel, 0, 4);
        schedulePage.add(oneLabel, 0, 5);
        schedulePage.add(twoLabel, 0, 6);
        schedulePage.add(threeLabel, 0, 7);
        schedulePage.add(fourLabel, 0, 8);
        schedulePage.add(fiveLabel, 0, 9);
        schedulePage.add(sixLabel, 0, 10);

        for (int i = 0; i < timetable.length; i++) {
            String booking = timetable[i];
            String[] bookingSpec = booking.split(",");
            //System.out.println(Arrays.toString(bookingSpec));
            String code = bookingSpec[0].replace("Success : Schedule Displayed : ", "");
            code = code.replace("Booking : ", "");
            code = code.replace("=", ": ");
            code = code.replace("code", "Code");
            String day = bookingSpec[1];
            String time = bookingSpec[2];
            String room = bookingSpec[3];
            room = room.replace("=", ": ");
            room = room.replace(" room", "Room");

            Label bookingLabel = new Label(code + "\n" + room);
            bookingLabel.setAlignment(Pos.CENTER);
            schedulePage.add(bookingLabel, TimeDayFormat.getColumn(day), TimeDayFormat.getRow(time));

        }

        schedulePage.setPadding(new Insets(10));
        GridPane.setMargin(scheduleLabel, new Insets(10, 0, 0, 0));

        Scene scene = new Scene(schedulePage, 500, 480); //minwidth,minheight
        scheduleStage.setScene(scene);
        scheduleStage.show();
    }


    @Override
    public void start(Stage stage) {
        Label enterModuleCode = new Label("Enter Module Code");
        Label selectDay = new Label("Select Day");
        Label selectTime = new Label("Select Time");
        Label roomNumberLabel = new Label("Enter room number");

        TextField moduleCode = new TextField();
        TextField roomNumber = new TextField();
        moduleCode.setMinSize(200, 20);
        moduleCode.setMaxSize(200, 20);
        roomNumber.setMinSize(200, 20);
        roomNumber.setMaxSize(200, 20);

        Button sendButton = new Button("Send");
        Button timeSlotsButton = new Button("Choose Time Slots");

        ChoiceBox timetableBox = new ChoiceBox<>(FXCollections.observableArrayList(timetable));

        sendButton.setStyle("-fx-background-color: green;-fx-text-fill:white");
        Button stopButton = new Button("Stop");
        stopButton.setStyle("-fx-background-color: red;-fx-text-fill:white");

        stopButton.setOnAction(event -> {
            out.println("STOP");
            System.exit(1);
        });

        addButton.setToggleGroup(radioGroup);
        removeButton.setToggleGroup(radioGroup);
        displayButton.setToggleGroup(radioGroup);
        earlyLectureButton.setToggleGroup(radioGroup);

        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Send Clicked");
                String code = moduleCode.getText() != null ? moduleCode.getText() : "EMPTY";
                String option = getRadioButtonOption();
                String day = calendarBox.getValue() != null ? calendarBox.getValue().toString() : "EMPTY";
                String time = timetableBox.getValue() != null ? timetableBox.getValue().toString() : "EMPTY";
                String room = roomNumber.getText();

                String message = code + "," + option + "," + day + "," + time + "," + room;

                ProcessRequest request = new ProcessRequest(message, in, out);
                new Thread(request).start(); // Network / I/O operations are taken into separate thread.

                request.setOnSucceeded(e -> {
                    String response = request.getValue();

                    System.out.println("RESPONSE : " + response);
                    if (response == null) {
                        return;
                    }

                    label.setText(response);
                    if (option.equals("DISPLAY") && response.startsWith("Success :")) {
                        displaySchedulePage(response);
                    }

                    String[] responseAlert = response.split(":");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(responseAlert[0]);
                    alert.setHeaderText("Alert");
                    alert.setContentText(responseAlert[1]);
                    alert.showAndWait();

                });
            }
        });


        VBox page = new VBox(addButton, removeButton, displayButton, earlyLectureButton, enterModuleCode, moduleCode, roomNumberLabel, roomNumber, selectDay, calendarBox, selectTime, timetableBox, sendButton, stopButton, scheduleLabel);
        page.setPadding(new Insets(10));
        VBox.setMargin(sendButton, new Insets(10, 0, 10, 0));
        //Top,Right,Bottom,Left
        VBox.setMargin(earlyLectureButton, new Insets(0, 0, 10, 0));
        VBox.setMargin(scheduleLabel, new Insets(10, 0, 0, 0));

        Scene scene = new Scene(page, 550, 350);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
