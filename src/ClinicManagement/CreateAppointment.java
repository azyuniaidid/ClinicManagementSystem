package ClinicManagement;

import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreateAppointment {
    public Parent getView(MainAppClinic app, Admin admin){

        Text sceneTitle = new Text("CREATE APPOINTMENT");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        HBox banner = new HBox();
        banner.setAlignment(Pos.CENTER);
        banner.setPadding(new Insets(30)); // Size of banner
        banner.getChildren().add(sceneTitle);
        banner.setStyle("-fx-background-color: #ADD378;");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        Label appointmentID = new Label("Appointment ID : ");
        Label patientName = new Label("Patient Name : ");
        Label doctorName = new Label("Doctor Name : ");
        Label date = new Label("Date : ");

        grid.add(appointmentID, 0, 2);
        grid.add(patientName, 0, 5);
        grid.add(doctorName, 0, 8);
        grid.add(date, 0, 11);

        TextField IDTextField = new TextField();
        IDTextField.setPromptText("Enter Appointment ID");
        grid.add(IDTextField, 0, 3);

        ComboBox<String> patient = new ComboBox<>();
        patient.setPromptText("Select");
        for(Patient p : admin.getAllPatients()){
            patient.getItems().add(p.getName());
        }
        grid.add(patient, 0, 6);

        ComboBox<String> doctor = new ComboBox<>();
        doctor.setPromptText("Select");
        for(Doctor d : admin.getAllDoctors()){
            doctor.getItems().add(d.getName());
        }
        grid.add(doctor,0, 9);

        DatePicker dates = new DatePicker();
        grid.add(dates, 0, 12);

        Button btnCreate = new Button("Create");
        grid.add(btnCreate,5, 13);
        Button btnCancel = new Button("Cancel");
        grid.add(btnCancel,4, 13);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 5, 14);

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

                System.out.println("Appointment ID : " + id + " saved successfully.");

                IDTextField.clear();
                patient.setValue(null);
                doctor.setValue(null);
                dates.setValue(null);
            }
        });

        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                actiontarget.setFill(Color.DARKRED);
                actiontarget.setText("Registration is Cancelled");
            }
        });


        Button btnRegPatient = new Button("Register Patient");
        Button btnRegDoctor = new Button("Register Doctor");
        Button btnAddMed = new Button("Add Medical History");
        Button btnGenBill = new Button("Generate Bill");
        Button btnHome = new Button("Main Page");

        //regPatientBtn action
        btnRegPatient.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                RegisterPatient registerPatient = new RegisterPatient();
                app.setScene(registerPatient.getView(app));
            }
        });

        //createApptBtn action
        btnRegDoctor.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                RegisterDoctorPanel doctorPanel = new RegisterDoctorPanel();
                app.setScene(doctorPanel.getView(app));
            }
        });

        //medHistoryBtn action
        btnAddMed.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                AddMedicalHistory addMedicHistory = new AddMedicalHistory();
                app.setScene(addMedicHistory.getView(app, new Admin()));
            }
        });

        //genBillBtn action
        btnGenBill.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                GenerateBill genBill = new GenerateBill();
                app.setScene(genBill.getView(app, new Admin()));
            }
        });

        //mainPageBtn action
        btnHome.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                app.setScene(app.getDashboard());
            }
        });

        //buttons at the side
        VBox sideBtns = new VBox(10);
        sideBtns.setPrefWidth(10);
        sideBtns.setPadding(new Insets(10));
        sideBtns.setAlignment(Pos.TOP_LEFT);

        //size of letters, size of buttons
        btnRegPatient.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        btnRegDoctor.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        btnAddMed.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        btnGenBill.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        btnHome.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");

        HBox contentLayout = new HBox();
        contentLayout.setPadding(new Insets(20));
        contentLayout.getChildren().addAll(sideBtns, grid);
        //contentLayout.setAlignment(Pos.CENTER);

        sideBtns.getChildren().addAll(btnRegPatient, btnRegDoctor, btnAddMed, btnGenBill, btnHome);

        VBox fullLayout = new VBox();
        fullLayout.getChildren().addAll(banner, contentLayout);

        return fullLayout;
    }
}
