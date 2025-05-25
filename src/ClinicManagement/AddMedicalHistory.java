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

/**
 *
 * @author Surface
 */
public class AddMedicalHistory 
{   
    public Parent getView (MainAppClinic app, Admin admin)
    {
        //patient banner
        Text sceneTitle = new Text("ADD MEDICAL HISTORY");
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
/*
        VBox sideMenu = new VBox(15);
        sideMenu.setPadding(new Insets(20));
        sideMenu.setStyle("-fx-background-color: white;");
        sideMenu.setPrefWidth(180);

        Button btnRegisterPatient = new Button("REGISTER PATIENT");
        Button btnRegisterDoctor = new Button("REGISTER DOCTOR");
        Button btnMedicalHistory = new Button("CREATE APPOINTMENT");
        Button btnGenerateBill = new Button("GENERATE BILL");
        Button btnHome = new Button("← HOME");

        btnRegisterPatient.setMaxWidth(Double.MAX_VALUE);
        btnRegisterDoctor.setMaxWidth(Double.MAX_VALUE);
        btnMedicalHistory.setMaxWidth(Double.MAX_VALUE);
        btnGenerateBill.setMaxWidth(Double.MAX_VALUE);
        btnHome.setMaxWidth(Double.MAX_VALUE);

        sideMenu.getChildren().addAll(btnRegisterPatient, btnRegisterDoctor, btnCreateAppointment, btnGenerateBill, btnHome);
 */     
        //list of patients    
        Label ptList = new Label("List of Patients:");
        ptList.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptList, 0, 0);
        ComboBox<String> patient = new ComboBox<>();
        patient.setPromptText("Select");
        for(Patient p : admin.getAllPatients()){
            patient.getItems().add(p.getName());
        }
        grid.add(patient, 0, 6);
        
        //patient index
        Label ptIndexLabel = new Label("Patient Index:");
        ptIndexLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptIndexLabel, 0, 0);
        TextField ptIndex = new TextField();
        grid.add(ptIndex,0,1);
        
        //list of doctors
        Label docList = new Label("List of Doctors:");
        docList.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docList, 0, 0);
        ComboBox<String> doctor = new ComboBox<>();
        doctor.setPromptText("Select");
        for(Doctor d : admin.getAllDoctors()){
            doctor.getItems().add(d.getName());
        }
        grid.add(doctor,0, 9);
        
        //doctor index
        Label docIndexLabel = new Label("Doctor Index:");
        docIndexLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docIndexLabel, 0, 0);
        TextField docIndex = new TextField();
        grid.add(docIndex,0,1);
        
        //diagnosis
        Label diagnosisLabel = new Label("Diagnosis:");
        diagnosisLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(diagnosisLabel, 0, 0);
        TextField diagnosis = new TextField();
        grid.add(diagnosis,0,1);
        
        //prescription
        Label prescriptionLabel = new Label("Prescription:");
        prescriptionLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(prescriptionLabel, 0, 0);
        TextField prescription = new TextField();
        grid.add(prescription,0,1);
        
        //date
        Label dateLabel = new Label("Prescription:");
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(dateLabel, 0, 0);
        DatePicker dates = new DatePicker();
        grid.add(dates, 0, 12);
        
        //button
        Button btnAdd = new Button("Add");
        grid.add(btnAdd,5, 10);
        Button btnCancel = new Button("Cancel");
        grid.add(btnCancel,4, 10);

        final Text
        actiontarget = new Text();

        grid.add(actiontarget, 5, 11);
    }
}
