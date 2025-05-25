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
        grid.add(patient, 0, 1);
        
        //patient index
        Label ptIndexLabel = new Label("Patient Index:");
        ptIndexLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptIndexLabel, 0, 2);
        TextField ptIndex = new TextField();
        grid.add(ptIndex,0,3);
        
        //list of doctors
        Label docList = new Label("List of Doctors:");
        docList.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docList, 0, 4);
        ComboBox<String> doctor = new ComboBox<>();
        doctor.setPromptText("Select");
        for(Doctor d : admin.getAllDoctors()){
            doctor.getItems().add(d.getName());
        }
        grid.add(doctor,0, 5);
        
        //doctor index
        Label docIndexLabel = new Label("Doctor Index:");
        docIndexLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docIndexLabel, 0, 6);
        TextField docIndex = new TextField();
        grid.add(docIndex,0,7);
        
        //diagnosis
        Label diagnosisLabel = new Label("Diagnosis:");
        diagnosisLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(diagnosisLabel, 0, 8);
        TextField diagnosis = new TextField();
        grid.add(diagnosis,0,9);
        
        //prescription
        Label prescriptionLabel = new Label("Prescription:");
        prescriptionLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(prescriptionLabel, 0, 10);
        TextField prescription = new TextField();
        grid.add(prescription,0,11);
        
        //date
        Label dateLabel = new Label("Date:");
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(dateLabel, 0, 12);
        DatePicker dates = new DatePicker();
        grid.add(dates, 0, 13);
        
        //button
        Button btnAdd = new Button("Add");
        grid.add(btnAdd,5, 10);
        Button btnCancel = new Button("Cancel");
        grid.add(btnCancel,4, 10);

        final Text
        actiontarget = new Text();

        grid.add(actiontarget, 5, 11);

        //registration is cancel
       btnCancel.setOnAction(new EventHandler<ActionEvent>()
       {
           @Override
           public void handle(ActionEvent e)
           {
               actiontarget.setFill(Color.DARKRED);
               actiontarget.setText("Registration is Cancelled");
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
    
    
           sideBtns.getChildren().addAll(btnRegPatient, btnRegDoctor, btnCreateAppt, btnGenBill, btnHome);
    
    
           VBox fullLayout = new VBox();
           fullLayout.getChildren().addAll(banner, contentLayout);
    
    
           return fullLayout;
    }
}
