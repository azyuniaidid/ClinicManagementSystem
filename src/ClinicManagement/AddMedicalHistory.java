
package ClinicManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
import javafx.geometry.HPos;

/**
 *
 * @author Surface
 */
public class AddMedicalHistory
{
    public Parent getView (MainAppClinic app, Admin admin)
    {
        //Load an image (from URL or local resource)
        Image image = new Image("file:/Users/Surface/Downloads/5.png");
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
        for(Patient p : admin.getAllPatients()){
            patient.getItems().add(p.getName());
        }
        grid.add(patient, 0, 1);

        //patient index
        Label ptIndexLabel = new Label("Patient Index:");
        ptIndexLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptIndexLabel, 0, 2);
        TextField ptIndex = new TextField();
        ptIndex.setPromptText("Enter Patient Index");
        ptIndex.setPrefWidth(500);
        grid.add(ptIndex,0,3);

        //list of doctors
        Label docList = new Label("List of Doctors:");
        docList.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docList, 0, 4);
        ComboBox<String> doctor = new ComboBox<>();
        doctor.setPromptText("Select Doctor");
        for(Doctor d : admin.getAllDoctors()){
            doctor.getItems().add(d.getName());
        }
        grid.add(doctor,0, 5);

        //doctor index
        Label docIndexLabel = new Label("Doctor Index:");
        docIndexLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docIndexLabel, 0, 6);
        TextField docIndex = new TextField();
        docIndex.setPromptText("Enter Doctor Index");
        docIndex.setPrefWidth(500);
        grid.add(docIndex,0,7);

        //diagnosis
        Label diagnosisLabel = new Label("Diagnosis:");
        diagnosisLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(diagnosisLabel, 0, 8);
        TextField diagnosis = new TextField();
        diagnosis.setPromptText("Enter Diagnosis");
        diagnosis.setPrefWidth(500);
        grid.add(diagnosis,0,9);

        //prescription
        Label prescriptionLabel = new Label("Prescription:");
        prescriptionLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(prescriptionLabel, 0, 10);
        TextField prescription = new TextField();
        prescription.setPromptText("Enter Prescription");
        prescription.setPrefWidth(500);
        grid.add(prescription,0,11);

        //date
        Label dateLabel = new Label("Date:");
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(dateLabel, 0, 12);
        DatePicker dates = new DatePicker();
        dates.setPromptText("Choose Date");
        dates.setPrefWidth(500);
        grid.add(dates, 0, 13);
        
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
                ptIndex.clear();
                doctor.getSelectionModel().clearSelection();
                docIndex.clear();
                diagnosis.clear();
                prescription.clear();
                dates.setValue(null);
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

                String formattedDate = selectedDate.toString();

                // Create MedicalHistory object
                MedicalHistory medicalHistory = new MedicalHistory(selectedPatient, selectedDoctor, diagnosisText, prescriptionText, formattedDate
                );

                // Display values in TextArea
                String msg = "Patient: " + selectedPatient.getName() + "\n"
                           + "Doctor: " + selectedDoctor.getName() + "\n"
                           + "Diagnosis: " + diagnosisText + "\n"
                           + "Prescription: " + prescriptionText + "\n"
                           + "Date: " + formattedDate + "\n";
                area.setText(msg);

                // Show confirmation message
                actionTarget.setFill(Color.GREEN);
                actionTarget.setText("Medical History Added Successfully");

                // Save to file
                String fileLine = selectedPatient.getName() + "\n"
                                + selectedDoctor.getName() + "\n"
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

        Button btnRegPatient = new Button("Register Patient");
        Button btnRegDoctor = new Button("Register Doctor");
        Button btnCreateAppt = new Button("Create Appointment");
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


        //RegDoctorBtn action
        btnRegDoctor.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                RegisterDoctorPanel doctorPanel = new RegisterDoctorPanel();
                app.setScene(doctorPanel.getView(app));
            }
        });


        //btnCreateAppt action
        btnCreateAppt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                CreateAppointment createAppointment = new CreateAppointment();
                app.setScene(createAppointment.getView(app, new Admin()));
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
        fullLayout.getChildren().addAll(banner, contentLayout);
        fullLayout.setPadding(new Insets(10));
        
        return fullLayout;
    }
}
