package ClinicManagement;

import java.io.*;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.time.LocalDate;
import java.util.Scanner;
import javafx.geometry.HPos;

public class MedicalHistoryManager
{
    public Parent getView (MainAppClinic app, Admin admin)
    {
        //Load an image (from URL or local resource)
        Image image = new Image("file:/Users/aisyahhafizar/Downloads/5.png");
        ImageView bannerMedicalHistory = new ImageView(image);
        bannerMedicalHistory.setFitHeight(200);
        bannerMedicalHistory.setFitWidth(1400);

        //add banner
        HBox banner = new HBox();
        banner.setAlignment(Pos.CENTER);
        banner.setPadding(new Insets(30));
        banner.setMaxWidth(Double.MAX_VALUE); // Let it expand fully

        banner.getChildren().add(bannerMedicalHistory);

        //grid banner layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setPrefWidth(650);


        //list of patients
        Label ptList = new Label("List of Patients:");
        ptList.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptList, 0, 0);
        ComboBox<String> patient = new ComboBox<>();
        patient.setPromptText("Select Patient");
        try (Scanner scanner = new Scanner(new File("patients.txt"))) {
            while (scanner.hasNextLine()) {
                String id = scanner.nextLine().trim();         // P001
                if (!scanner.hasNextLine()) break;
                String name = scanner.nextLine().trim();       // Ali Bin Abu
                if (!scanner.hasNextLine()) break;
                String phone = scanner.nextLine().trim();      // 0123456789
                if (!scanner.hasNextLine()) break;
                String gender = scanner.nextLine().trim();     // Male
                if (!scanner.hasNextLine()) break;
                String age = scanner.nextLine().trim();        // 25

                patient.getItems().add(id + " - " + name);
            }
        } catch (FileNotFoundException e) {
            System.out.println("patients.txt not found.");
        }
        grid.add(patient, 0, 1);

        //list of doctors
        Label docList = new Label("List of Doctors:");
        docList.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docList, 0, 2);
        ComboBox<String> doctor = new ComboBox<>();
        doctor.setPromptText("Select Doctor");
        try (Scanner scanner = new Scanner(new File("doctors.txt"))) {
            while (scanner.hasNextLine()) {
                String id = scanner.nextLine().trim();         // D001
                if (!scanner.hasNextLine()) break;
                String name = scanner.nextLine().trim();       // Dr. Salina
                if (!scanner.hasNextLine()) break;
                String phone = scanner.nextLine().trim();      // 0159678324
                if (!scanner.hasNextLine()) break;
                String specialization = scanner.nextLine().trim(); // General
                if (!scanner.hasNextLine()) break;
                String rate = scanner.nextLine().trim();       // 97.0

                doctor.getItems().add(id + " - " + name);
            }
        } catch (FileNotFoundException e) {
            System.out.println("doctors.txt not found.");
        }
        grid.add(doctor,0, 3);

        //diagnosis
        Label diagnosisLabel = new Label("Diagnosis:");
        diagnosisLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(diagnosisLabel, 0, 4);
        TextField diagnosis = new TextField();
        diagnosis.setPromptText("Enter Diagnosis");
        diagnosis.setPrefWidth(500);
        grid.add(diagnosis,0,5);

        //prescription
        Label prescriptionLabel = new Label("Prescription:");
        prescriptionLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(prescriptionLabel, 0, 6);
        TextField prescription = new TextField();
        prescription.setPromptText("Enter Prescription");
        prescription.setPrefWidth(500);
        grid.add(prescription,0,7);

        //date
        Label dateLabel = new Label("Date:");
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(dateLabel, 0, 8);
        DatePicker dates = new DatePicker();
        dates.setPromptText("Choose Date");
        dates.setPrefWidth(500);
        grid.add(dates, 0, 9);

        //display medical history information
        VBox infoBox = new VBox(10);
        Label view = new Label("Medical History Information");
        view.setFont(Font.font("Arial", FontWeight.BOLD,15));

        //text area
        TextArea area = new TextArea();
        area.setPrefSize(500, 300);
        infoBox.getChildren().addAll(view,area);

        HBox buttonBox = new HBox(10); // spacing between buttons
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT); // align buttons to the left
        Button btnAdd = new Button("Add");
        Button btnCancel = new Button("Cancel");
        buttonBox.getChildren().addAll(btnCancel,btnAdd);
        grid.add(buttonBox, 0, 14, 2, 1); //i2=going right i3=going down

        //action for add and cancel button
        final Text actionTarget = new Text();
        grid.add(actionTarget, 0, 15, 2, 1);
        GridPane.setHalignment(actionTarget, HPos.RIGHT);
        grid.setPrefWidth(600);

        //registration is cancel
        btnCancel.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                actionTarget.setFill(Color.DARKRED);
                patient.getSelectionModel().clearSelection();
                doctor.getSelectionModel().clearSelection();
                diagnosis.clear();
                prescription.clear();
                dates.setValue(null);
                area.clear();
                actionTarget.setText("Registration is Cancelled");
            }
        });

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String selectedPatientName = patient.getValue();
                String selectedDoctorName = doctor.getValue();
                String diagnosisText = diagnosis.getText();
                String prescriptionText = prescription.getText();
                LocalDate selectedDate = dates.getValue();

                if (selectedPatientName == null || selectedDoctorName == null ||
                        diagnosisText.isEmpty() || prescriptionText.isEmpty() || selectedDate == null) {
                    actionTarget.setFill(Color.RED);
                    actionTarget.setText("Please fill in all fields.");
                    return;
                }
/*
                // Find the matching Patient and Doctor
                Patient selectedPatient = null;
                for (Patient p : admin.getAllPatients()) {
                    if (p.getName().equals(selectedPatientName)) {
                        selectedPatient = p;
                        break;
                    }
                }

                Doctor selectedDoctor = null;
                for (Doctor d : admin.getAllDoctors()) {
                    if (d.getName().equals(selectedDoctorName)) {
                        selectedDoctor = d;
                        break;
                    }
                }

                if (selectedPatient == null || selectedDoctor == null) {
                    actionTarget.setFill(Color.RED);
                    actionTarget.setText("Selected patient or doctor not found.");
                    return;
                }
*/
                String formattedDate = selectedDate.toString();
/*
                // Create MedicalHistory object
                MedicalHistory medicalHistory = new MedicalHistory(selectedPatientName, selectedDoctorName, diagnosisText, prescriptionText, formattedDate
                );
*/
                // Display values in TextArea
                String msg = "Patient: " + selectedPatientName + "\n"
                        + "Doctor: " + selectedDoctorName + "\n"
                        + "Diagnosis: " + diagnosisText + "\n"
                        + "Prescription: " + prescriptionText + "\n"
                        + "Date: " + formattedDate + "\n";
                area.setText(msg);

                // Show confirmation message
                actionTarget.setFill(Color.GREEN);
                actionTarget.setText("Medical History Added Successfully");

                // Save to file
                String fileLine = selectedPatientName + "\n"
                        + selectedDoctorName + "\n"
                        + diagnosisText + "\n"
                        + prescriptionText + "\n"
                        + formattedDate + "\n";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("medical_history.txt", true))) {
                    writer.write(fileLine);
                    writer.newLine();
                }

                catch (IOException ex) {
                    System.err.println("Error writing to file: " + ex.getMessage());
                }
            }
        });

        Button btnRegPatient = new Button("Patient Registration");
        Button btnRegDoctor = new Button("Doctor Registration");
        Button btnCreateAppt = new Button("Appointment Manager");
        Button btnGenBill = new Button("Bill Generator");
        Button btnHome = new Button("Main Page");


        //regPatientBtn action
        btnRegPatient.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                PatientRegistration registerPatient = new PatientRegistration();
                app.setScene(registerPatient.getView(app));
            }
        });


        //RegDoctorBtn action
        btnRegDoctor.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                DoctorRegistration doctorPanel = new DoctorRegistration();
                app.setScene(doctorPanel.getView(app));
            }
        });


        //btnCreateAppt action
        btnCreateAppt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                AppointmentManager createAppointment = new AppointmentManager();
                app.setScene(createAppointment.getView(app, new Admin()));
            }
        });


        //genBillBtn action
        btnGenBill.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                BillGenerator genBill = new BillGenerator();
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
        btnRegPatient.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        btnRegDoctor.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        btnCreateAppt.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        btnGenBill.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        btnHome.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");

        HBox contentLayout = new HBox(30);
        contentLayout.setPadding(new Insets(20));
        contentLayout.getChildren().addAll(sideBtns, grid,infoBox);
        contentLayout.setAlignment(Pos.CENTER_LEFT);

        sideBtns.getChildren().addAll(btnRegPatient, btnRegDoctor, btnCreateAppt, btnGenBill, btnHome);

        VBox fullLayout = new VBox(10);
        fullLayout.getChildren().addAll(bannerMedicalHistory, contentLayout);
        fullLayout.setPadding(new Insets(10));

        return fullLayout;
    }
}
