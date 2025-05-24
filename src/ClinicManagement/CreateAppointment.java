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

import javax.print.Doc;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreateAppointment {
    public Parent getView(MainAppClinic app, Admin admin){
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label scenetitle = new Label("CREATE APPOINTMENT");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        scenetitle.setTextFill(Color.WHITE);

        scenetitle.setBackground(new Background(new BackgroundFill(Color.LIGHTSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        scenetitle.setPadding(new Insets(10));

        layout.getChildren().add(scenetitle);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
/*
        VBox sideMenu = new VBox(15);
        sideMenu.setPadding(new Insets(20));
        sideMenu.setStyle("-fx-background-color: white;");
        sideMenu.setPrefWidth(180);

        Button btnRegisterPatient = new Button("REGISTER PATIENT");
        Button btnRegisterDoctor = new Button("REGISTER DOCTOR");
        Button btnMedicalHistory = new Button("MEDICAL HISTORY");
        Button btnGenerateBill = new Button("GENERATE BILL");
        Button btnHome = new Button("← HOME");

        btnRegisterPatient.setMaxWidth(Double.MAX_VALUE);
        btnRegisterDoctor.setMaxWidth(Double.MAX_VALUE);
        btnMedicalHistory.setMaxWidth(Double.MAX_VALUE);
        btnGenerateBill.setMaxWidth(Double.MAX_VALUE);
        btnHome.setMaxWidth(Double.MAX_VALUE);

        sideMenu.getChildren().addAll(btnRegisterPatient, btnRegisterDoctor, btnMedicalHistory, btnGenerateBill, btnHome);

 */
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

        Button btnCreate = new Button("Save");
        Button btnHome = new Button("Home");

        HBox hbtn = new HBox(20);
        hbtn.setAlignment((Pos.CENTER));
        hbtn.getChildren().addAll(btnCreate, btnHome);
        grid.add(hbtn, 0, 14, 4, 1);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 15);

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

        btnHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               app.setScene(app.getDashboard());
            }
        });

        layout.getChildren().add(grid);
        return layout;
    }
}
