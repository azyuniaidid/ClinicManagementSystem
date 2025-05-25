package ClinicManagement;

import java.io.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author User
 */
public class GenerateBill {
    
    public Parent getView (MainAppClinic app){
        
        //Clinic banner
        Image image = new Image("file:///C:/Users/User/Pictures/BannerBill.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);
        imageView.setPreserveRatio(true);
        
        //grid banner layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        //sidebar menu 
        VBox sideBar = new VBox(10);
        sideBar.setPrefWidth(200);
        
        Button regPatientBtn = new Button("REGISTER PATIENT");
        Button regDocBtn = new Button("Register Doctor");
        Button createApptBtn = new Button("Create Appointment");
        Button medHistoryBtn = new Button("Medical History");
        Button mainPageBtn = new Button("Main Page");
        
        regPatientBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        regDocBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        createApptBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        medHistoryBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        mainPageBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        
        sideBar.getChildren().addAll(regPatientBtn, regDocBtn, createApptBtn, medHistoryBtn, mainPageBtn);

         VBox sideMenu = new VBox(15);
        sideMenu.setPadding(new Insets(20));
        sideMenu.setStyle("-fx-background-color: white;");
        sideMenu.setPrefWidth(180);

        Button btnRegisterPatient = new Button("REGISTER PATIENT");
        Button btnRegisterDoctor = new Button("REGISTER DOCTOR");
        Button btnMedicalHistory = new Button("MEDICAL HISTORY");
        Button btnGenerateBill = new Button("GENERATE BILL");
        Button btnHome = new Button("← HOME");


        
        
        
        //list of patients    
        Label ptList = new Label("List of Patients:");
        ptList.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptList, 0, 0);
        ComboBox<String> patient = new ComboBox<>();
        patient.setPromptText("Select");
        patient.setPrefWidth(600);
        grid.add(patient, 0, 1);
        for(Patient p : admin.getAllPatients()){
            patient.getItems().add(p.getName());
        }
        
        
        //patient index
        Label ptIndexLabel = new Label("Patient Index:");
        ptIndexLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptIndexLabel, 0, 2);
        TextField ptIndex = new TextField();
        ptIndex.setPromptText("Enter here");
        grid.add(ptIndex,0,3);
        
        //list of doctors
        Label docList = new Label("List of Doctors:");
        docList.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docList, 5, 0);
        ComboBox<String> doctor = new ComboBox<>();
        doctor.setPromptText("Select");
        doctor.setPrefWidth(600);
        grid.add(doctor,5, 1);
        for(Doctor d : admin.getAllDoctors()){
            doctor.getItems().add(d.getName());
        }
        
        
        //doctor index
        Label docIndexLabel = new Label("Doctor Index:");
        docIndexLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docIndexLabel, 5, 2);
        TextField docIndex = new TextField();
        docIndex.setPromptText("Enter here");
        grid.add(docIndex,5,3);
        
        //date
        Label dateLabel = new Label("Date:");
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(dateLabel, 5, 4);
        DatePicker date = new DatePicker();
        grid.add(date, 5, 5);
    
        //bill id
        Label billIdLabel = new Label("Bill ID:");
        billIdLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(billIdLabel, 0, 4);
        TextField billBox = new TextField();
        billBox.setPromptText("Enter Bill ID");
        grid.add(billBox,0,5);
        
        //amount
        Label amountLabel = new Label("Amount:");
        amountLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(amountLabel, 0, 6);
        TextField amountBox = new TextField();
        amountBox.setPromptText("RM");
        grid.add(amountBox,0,7);
        
         //button
        Button generateBtn = new Button("GENERATE");
        generateBtn.setAlignment(Pos.CENTER_LEFT);
        generateBtn.setPrefHeight(100);
        generateBtn.setPrefWidth(100);
        grid.add(generateBtn,0, 10);
        
        final Text
        actiontarget = new Text();

        grid.add(actiontarget, 0, 11);
        
        //actions
        
         
        //value box which shows bill information
        Label viewBill = new Label("Billing Information");
        viewBill.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(viewBill, 0, 12);
        
        TextArea billArea = new TextArea();
        billArea.setPrefHeight(500);
        billArea.setPrefWidth(500);
        grid.add(billArea, 0, 13, 2, 1); 

        //once generate is clicked, values will show
        generateBtn.setOnAction(new EventHandler<ActionEvent>()
{
            @Override
            public void handle(ActionEvent actionEvent)
            {
                // read bill id
                String billID = billBox.getText();

                // read amount
                double amount = 0.0;
                try {
                    amount = Double.parseDouble(amountBox.getText());
                } catch (NumberFormatException e) {
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText("Invalid amount entered.");
                    return;
                }
                
                String currentDate = (date.getValue() != null) ? date.getValue().toString() : "No date selected";
                

                // set success message
                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Bill generated successfully");

                // display in text area
                String viewBillingInfo = String.format(
                    "\n=== Billing Information ===\n" +
                    "Bill ID : %s\n" +
                    "Amount : RM%.2f\n" +
                    "Bill generated on : %s\n", 
                
                    billID, amount,  currentDate
                   
                );
                billArea.setText(viewBillingInfo);
                
                // write to file
                String fileLine = billID + "\n" + amount + "\n" + currentDate + "\n";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("bill.txt", true)))
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


        
        //regPatientBtn action
        regPatientBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RegisterPatient regPatient = new RegisterPatient();
                app.setScene(regPatient.getView(app));
            }
        });
        
        
         //regDocBtn action
        regDocBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RegisterDoctorPanel regDoctor = new RegisterDoctorPanel();
                app.setScene(regDoctor.getView(app));
            }
        });
        
        
        //createApptBtn action
        createApptBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreateAppointment createApp = new CreateAppointment();
                app.setScene(createApp.getView(app));
            }
        });
        
        //medHistoryBtn action
        genBillBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddMedicalHistory addMedicHistory = new AddMedicalHistory();
                app.setScene(addMedicHistory.getView(app));
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
        
        
        

        BorderPane layout = new BorderPane();
        layout.setTop(imageView);
        layout.setLeft(sideBar);
        layout.setCenter(grid);
        return layout;
        
    }
    
}
    



