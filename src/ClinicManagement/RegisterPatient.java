package ClinicManagement;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class RegisterPatient
{
    public Parent getView(MainAppClinic app)
    {
        //patient banner
        Text sceneTitle = new Text("PATIENT REGISTRATION");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        HBox banner = new HBox();
        banner.setAlignment(Pos.CENTER);
        banner.setPadding(new Insets(30)); //size of the green banner
        banner.getChildren().add(sceneTitle);
        banner.setStyle("-fx-background-color: #ADD378;");

        //grid banner layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //patient id
        Label ptlabel = new Label("Patient ID");
        ptlabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptlabel, 0, 0);
        TextField ptid = new TextField();
        grid.add(ptid,0,1);

        //patient name
        Label ptname = new Label("Patient Name");
        ptname.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptname, 0, 2);
        TextField patientName = new TextField();
        grid.add(patientName,0,3);

        //phone number
        Label number = new Label("Phone Number");
        number.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(number, 0, 4);
        TextField phoneNumber = new TextField();
        grid.add(phoneNumber,0,5);

        //gender
        Label gender = new Label("Gender");
        gender.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(gender, 0, 6);

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female");
        genderBox.setPrefWidth(100);
        grid.add(genderBox, 0, 7);

        //age
        Label age = new Label("Age");
        age.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(age, 0, 8);
        TextField ages = new TextField();
        grid.add(ages,0,9);

        //register and cancel button
        Button register = new Button("Register");
        grid.add(register,5, 10);
        Button cancel = new Button("Cancel");
        grid.add(cancel,4, 10);

        final Text
        actiontarget = new Text();

        grid.add(actiontarget, 5, 11);

        //value box which shows all patient information
        Label view = new Label("Patient Information");
        view.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(view, 0, 12);

        TextArea area = new TextArea();
        area.setPrefHeight(100);
        area.setPrefWidth(300);
        grid.add(area, 0, 13, 2, 1); //i2 height i3 width

        //once register is clicked, values will show
        register.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                //patient is registered
                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Patient is Registered");

                //read patient id
                String id = ptid.getText();

                //read patient name
                String name = patientName.getText();

                //read patients phone number
                String number = phoneNumber.getText();

                //read gender
                String patientGender = genderBox.getValue();

                //read age
                String patientAge = ages.getText();

                String msg = "Patient ID :  " + id + "\n"
                        +"Patient Name : " + name + "\n"
                        +"Phone Number : " + number + "\n"
                        +"Gender : " + patientGender + "\n"
                        +"Age : " + patientAge;

                area.setText(msg);

                String fileLine = id + "\n" + name + "\n" + number + "\n" + patientGender + "\n" + patientAge + "\n";

                // to text file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("patients.txt", true)))
                {
                    writer.write(fileLine);
                    writer.newLine();
                }
                catch (IOException e)
                {
                    System.err.println("Error writing to file: " + e.getMessage());
                }
            }
        });

        //registration is cancel
        cancel.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                actiontarget.setFill(Color.DARKRED);
                actiontarget.setText("Registration is Cancelled");
            }
        });

        //scene
        VBox layout = new VBox();
        layout.getChildren().addAll(banner, grid);

        //back
        Button backBtn = new Button("Back to Main Page");
        backBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                app.setScene(app.getDashboard());
            }
        });
        layout.getChildren().add(backBtn);

        // connect register w main
        VBox connectView = new VBox(20);
        connectView.setPadding(new Insets(20));
        connectView.getChildren().addAll(layout, panel);

        return connectView;

    }

}


