package ClinicManagement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.io.FileWriter;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import java.io.BufferedWriter;
import java.io.IOException;

public class PatientRegistration
{
    public Parent getView(MainAppClinic app)
    {
        //Clinic banner
        // Load an image
        Image image = new Image("file:/Users/aisyahhafizar/Downloads/3.png");
        ImageView bannerPatient = new ImageView(image);
        bannerPatient.setFitHeight(200);
        bannerPatient.setFitWidth(1400);

        //add banner
        HBox banner = new HBox();
        banner.setAlignment(Pos.CENTER);
        banner.setPadding(new Insets(30));
        banner.setMaxWidth(Double.MAX_VALUE); // Let it expand fully

        banner.getChildren().add(bannerPatient);

        //grid banner layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setPrefWidth(650);

        //patient id
        Label ptlabel = new Label("Patient ID");
        ptlabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptlabel, 0, 0);
        TextField ptid = new TextField();
        ptid.setPromptText("Enter Patient ID");
        ptid.setPrefWidth(100);
        grid.add(ptid,0,1);

        //patient name
        Label ptname = new Label("Patient Name");
        ptname.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptname, 0, 2);
        TextField patientName = new TextField();
        patientName.setPromptText("Enter Patient Name");
        patientName.setPrefWidth(500);
        grid.add(patientName,0,3);

        //phone number
        Label number = new Label("Phone Number");
        number.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(number, 0, 4);
        TextField phoneNumber = new TextField();
        phoneNumber.setPromptText("Enter Phone Number");
        phoneNumber.setPrefWidth(400);
        grid.add(phoneNumber,0,5);

        //gender
        Label gender = new Label("Gender");
        gender.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(gender, 0, 6);

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.setPromptText("Select Gender");
        genderBox.getItems().addAll("Male", "Female");
        genderBox.setPrefWidth(300);
        grid.add(genderBox, 0, 7);

        //age
        Label age = new Label("Age");
        age.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(age, 0, 8);

        ComboBox<Integer> ageBox = new ComboBox<>();
        for (int i = 0; i <= 100; i++) // age range
        {
            ageBox.getItems().add(i);
        }
        ageBox.setPrefWidth(100);
        ageBox.setPromptText("Age");
        grid.add(ageBox, 0, 9);

        //value box which shows all patient information
        VBox infoBox = new VBox(10);
        Label view = new Label("Patient Information");
        view.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(view, 0, 12);

        //text area
        TextArea area = new TextArea();
        area.setPrefSize(500, 300);
        infoBox.getChildren().addAll(view,area);

        HBox buttonBox = new HBox(10); // spacing between buttons
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT); // align buttons to the left
        Button register = new Button("Register");
        Button cancel = new Button("Cancel");
        buttonBox.getChildren().addAll(cancel,register);
        grid.add(buttonBox, 0, 10, 2, 1); //i2=going right i3=going down

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 11, 2, 1);
        GridPane.setHalignment(actiontarget, HPos.RIGHT);
        grid.setPrefWidth(600);

        //once register is clicked, values will show
        register.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("Successfully Registered Patient Information!");

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
                String patientAge = age.getText();

                String msg = "Patient ID :  " + id + "\n"
                        +"Patient Name : " + name + "\n"
                        +"Phone Number : " + number + "\n"
                        +"Gender : " + patientGender + "\n"
                        +"Age : " + patientAge;

                area.setText(msg);

                String fileLine = id + "\n" + name + "\n" + number + "\n" + patientGender + "\n" + patientAge + "";

                // to text file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("patients.txt", true)))
                {
                    writer.write(fileLine);
                    writer.newLine();
                }
                catch (IOException e)
                {
                    System.out.println("Error writing to file: " + e.getMessage());
                }
            }
        });

        //registration is cancel
        cancel.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                actiontarget.setFill(Color.RED);
                ptid.setText(""); //text fields
                patientName.setText("");
                phoneNumber.setText("");
                actiontarget.setText("Registration is Cancelled");
            }
        });

        //side buttons
        //actions
        Button regDoctorBtn = new Button("Doctor Registration");
        Button createApptBtn = new Button("Appointment Manager");
        Button medHistoryBtn = new Button("Medical History Manager");
        Button genBillBtn = new Button("Bill Generator");
        Button mainPageBtn = new Button("Main Page");

        //regPatientBtn action
        regDoctorBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                DoctorRegistration regDoctor = new DoctorRegistration();
                app.setScene(regDoctor.getView(app));
            }
        });

        //createApptBtn action
        createApptBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                AppointmentManager createApp = new AppointmentManager();
                app.setScene(createApp.getView(app, new Admin()));
            }
        });

        //medHistoryBtn action
        medHistoryBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                MedicalHistoryManager addMedicHistory = new MedicalHistoryManager();
                app.setScene(addMedicHistory.getView(app, new Admin()));
            }
        });

        //genBillBtn action
        genBillBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                BillGenerator genBill = new BillGenerator();
                app.setScene(genBill.getView(app, new Admin()));
            }
        });

        //mainPageBtn action
        mainPageBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                app.setScene(app.getDashboard());
            }
        });

        HBox form = new HBox(30);
        form.getChildren().addAll(grid);
        form.setAlignment(Pos.TOP_CENTER);

        HBox info = new HBox(30);
        info.getChildren().add(infoBox);
        form.setAlignment(Pos.TOP_RIGHT);

        //buttons at the side
        VBox sideBtns = new VBox(10);
        sideBtns.setPrefWidth(180);
        sideBtns.setPadding(new Insets(10));
        sideBtns.setAlignment(Pos.TOP_LEFT);

        //size of letters, size of buttons
        regDoctorBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        createApptBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        medHistoryBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        genBillBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        mainPageBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");


        HBox contentLayout = new HBox(30);
        contentLayout.setPadding(new Insets(20));
        contentLayout.getChildren().addAll(sideBtns, grid,infoBox);
        contentLayout.setAlignment(Pos.CENTER_LEFT);

        sideBtns.getChildren().addAll(regDoctorBtn, createApptBtn, medHistoryBtn, genBillBtn, mainPageBtn);

        VBox fullLayout = new VBox(10);
        fullLayout.getChildren().addAll(bannerPatient,contentLayout);
        fullLayout.setPadding(new Insets(10));

        return fullLayout;
    }

}
