package ClinicManagement;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreateAppointment {
    public Parent getView(MainAppClinic app, Admin admin){

        // Clinic banner
        Image image = new Image("file:C:/Users/User/Downloads/CreateApptBanner.png");
        ImageView banner = new ImageView(image);
        banner.setFitHeight(200);
        banner.setFitWidth(1400);

        // Main form grid
        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(30));

        // Labels
        Label appointmentID = new Label("Appointment ID : ");
        Label patientName = new Label("Patient Name : ");
        Label doctorName = new Label("Doctor Name : ");
        Label date = new Label("Date : ");

        appointmentID.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        patientName.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        doctorName.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        date.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Fields
        TextField IDTextField = new TextField();
        IDTextField.setPromptText("Enter Appointment ID");
        IDTextField.setPrefWidth(250);

        ComboBox<String> patient = new ComboBox<>();
        patient.setPromptText("Select");
        for(Patient p : admin.getAllPatients()){
            patient.getItems().add(p.getName());
        }

        ComboBox<String> doctor = new ComboBox<>();
        doctor.setPromptText("Select");
        for(Doctor d : admin.getAllDoctors()){
            doctor.getItems().add(d.getName());
        }

        DatePicker dates = new DatePicker();

        // Add to grid
        grid.add(appointmentID, 0, 0);
        grid.add(IDTextField, 1, 0);

        grid.add(patientName, 0, 1);
        grid.add(patient, 1, 1);

        grid.add(doctorName, 0, 2);
        grid.add(doctor, 1, 2);

        grid.add(date, 0, 3);
        grid.add(dates, 1, 3);

        // Buttons
        Button btnCreate = new Button("Create");
        Button btnCancel = new Button("Cancel");
        btnCreate.setStyle("-fx-font-size: 16px;");
        btnCancel.setStyle("-fx-font-size: 16px;");

        HBox btnBox = new HBox(15);
        btnBox.setAlignment(Pos.CENTER_RIGHT);
        btnBox.setPadding(new Insets(10, 0, 0, 0));
        btnBox.getChildren().addAll(btnCancel, btnCreate);
        grid.add(btnBox, 1, 4);

        // Action text
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 5);
        GridPane.setMargin(actiontarget, new Insets(5, 0, 0, 0));

        // Right-side information box
        VBox infoBox = new VBox(10);
        infoBox.setAlignment(Pos.TOP_LEFT);
        Label view = new Label("Appointment Information");
        view.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        TextArea outputArea = new TextArea();
        outputArea.setPrefSize(400, 400);
        outputArea.setEditable(false);

        infoBox.getChildren().addAll(view, outputArea);

        // Button actions
        btnCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String id = IDTextField.getText().trim();
                String patientName = patient.getValue();
                String doctorName = doctor.getValue();
                LocalDate localDate = dates.getValue();

                if (id.isEmpty() || patientName == null || doctorName == null || localDate == null) {
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText("Please fill all fields");
                    return;
                }

                Date appointmentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                Patient selectedPatient = null;
                for (Patient p : admin.getAllPatients()) {
                    if (p.getName().equals(patientName)) {
                        selectedPatient = p;
                        break;
                    }
                }

                Doctor selectedDoctor = null;
                for (Doctor d : admin.getAllDoctors()) {
                    if (d.getName().equals(doctorName)) {
                        selectedDoctor = d;
                        break;
                    }
                }

                if (selectedDoctor == null || selectedPatient == null) {
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText("Selected patient or doctor not found");
                    return;
                }

                Appointment appointment = new Appointment(id, appointmentDate, "Scheduled", selectedPatient, selectedDoctor);
                admin.addAppointment(appointment);
                admin.dataToTextFiles();

                actiontarget.setFill(Color.LIGHTGREEN);
                actiontarget.setText("Appointment saved successfully!");

                String details = "📅 Appointment Details\n"
                        + "------------------------\n"
                        + "Appointment ID : " + id + "\n"
                        + "Patient Name   : " + selectedPatient.getName() + "\n"
                        + "Doctor Name    : " + selectedDoctor.getName() + "\n"
                        + "Date           : " + localDate + "\n"
                        + "Status         : Scheduled";

                outputArea.setText(details);

                IDTextField.clear();
                patient.setValue(null);
                doctor.setValue(null);
                dates.setValue(null);
            }
        });

        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                IDTextField.clear();
                patient.setValue(null);
                doctor.setValue(null);
                dates.setValue(null);
                outputArea.clear();
                actiontarget.setFill(Color.DARKRED);
                actiontarget.setText("Appointment creation cancelled.");
            }
        });

        // Side navigation buttons
        VBox sideBtns = new VBox(15);
        sideBtns.setPadding(new Insets(20));
        sideBtns.setAlignment(Pos.TOP_CENTER);

        String btnStyle = "-fx-font-size: 16px; -fx-min-width: 200px;";
        Button btnRegPatient = new Button("Register Patient");
        Button btnRegDoctor = new Button("Register Doctor");
        Button btnAddMed = new Button("Add Medical History");
        Button btnGenBill = new Button("Generate Bill");
        Button btnHome = new Button("Main Page");

        btnRegPatient.setStyle(btnStyle);
        btnRegDoctor.setStyle(btnStyle);
        btnAddMed.setStyle(btnStyle);
        btnGenBill.setStyle(btnStyle);
        btnHome.setStyle(btnStyle);

        sideBtns.getChildren().addAll(btnRegPatient, btnRegDoctor, btnAddMed, btnGenBill, btnHome);

        // Button navigation logic
        btnRegPatient.setOnAction(e -> {
            RegisterPatient registerPatient = new RegisterPatient();
            app.setScene(registerPatient.getView(app));
        });

        btnRegDoctor.setOnAction(e -> {
            RegisterDoctorPanel doctorPanel = new RegisterDoctorPanel();
            app.setScene(doctorPanel.getView(app));
        });

        btnAddMed.setOnAction(e -> {
            AddMedicalHistory addMedicHistory = new AddMedicalHistory();
            app.setScene(addMedicHistory.getView(app, admin));
        });

        btnGenBill.setOnAction(e -> {
            GenerateBill genBill = new GenerateBill();
            app.setScene(genBill.getView(app, admin));
        });

        btnHome.setOnAction(e -> app.setScene(app.getDashboard()));

        HBox contentLayout = new HBox(30);
        contentLayout.setPadding(new Insets(30));
        contentLayout.getChildren().addAll(sideBtns, grid, infoBox);

        VBox fullLayout = new VBox(20);
        fullLayout.getChildren().addAll(banner, contentLayout);
        fullLayout.setPadding(new Insets(10));

        return fullLayout;
    }
}