package ClinicManagement;

import java.io.*;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
        Image image = new Image("file:C:/Users/User/Pictures/BannerBill.jpg");
        ImageView bannerBill = new ImageView(image);
        bannerBill.setFitHeight(200);
        bannerBill.setFitWidth(1400);

        HBox banner = new HBox();
        banner.setAlignment(Pos.CENTER);
        banner.setPadding(new Insets(30));
        banner.setMaxWidth(Double.MAX_VALUE); // Let it expand fully
        
        banner.getChildren().add(bannerBill);

        //grid banner layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setPrefWidth(650);
        
        //sidebar menu 
         VBox sideBar = new VBox(10);
        sideBar.setPrefWidth(180);
        sideBar.setPadding(new Insets(10));
        sideBar.setAlignment(Pos.TOP_LEFT);
        
        Button regPatientBtn = new Button("Register Patient");
        Button regDocBtn = new Button("Register Doctor");
        Button createApptBtn = new Button("Create Appointment");
        Button medHistoryBtn = new Button("Medical History");
        Button mainPageBtn = new Button("Main Page");
        
        regPatientBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        regDocBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        createApptBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        medHistoryBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        mainPageBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        
        sideBar.getChildren().addAll(regPatientBtn, regDocBtn, createApptBtn, medHistoryBtn, mainPageBtn);
        
        
        //list of patients    
        Label ptList = new Label("List of Patients:");
        ptList.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(ptList, 0, 0);
        ComboBox<String> patient = new ComboBox<>();
        patient.getItems().addAll("1-Aisyah binti Ali","Option-2","Option-3");
        patient.setPromptText("Select Patient");
        
        
        for(Patient p : admin.getAllPatients()){
            patient.getItems().add(p.getName());
        }
        grid.add(patient, 0, 1);
        
        
        //list of doctors
        Label docList = new Label("List of Doctors:");
        docList.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docList, 0, 2);
        ComboBox<String> doctor = new ComboBox<>();
        doctor.getItems().addAll("1-Aminah binti Abu","Option-2","Option-3");
        doctor.setPromptText("Select Doctor");
        
        for(Doctor d : admin.getAllDoctors()){
            doctor.getItems().add(d.getName());
        }
        grid.add(doctor,0, 3);
        
        
        //date
        Label dateLabel = new Label("Date:");
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(dateLabel, 0, 4);
        DatePicker date = new DatePicker();
        date.setPromptText("Choose Date");
        date.setPrefWidth(500);
        grid.add(date, 0, 5);
    
        //bill id
        Label billIdLabel = new Label("Bill ID:");
        billIdLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(billIdLabel, 0, 6);
        TextField billBox = new TextField();
        billBox.setPromptText("Enter Bill ID");
        billBox.setPrefWidth(500);
        grid.add(billBox,0,7);
        
        //amount
        Label amountLabel = new Label("Amount:");
        amountLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(amountLabel, 0, 8);
        TextField amountBox = new TextField();
        amountBox.setPromptText("RM");
        amountBox.setPrefWidth(500);
        grid.add(amountBox,0,9);
        
        //payment method
        Label methodLabel = new Label("Payment Method:");
        methodLabel.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(methodLabel, 0, 10);
        ComboBox<String> payMethod = new ComboBox<>();
        payMethod.getItems().addAll("Cash","Credit Card", "Debit Card");
        payMethod.setPromptText("Select Payment Method");
        payMethod.setPrefWidth(500);
        grid.add(payMethod,0,11);
        
         //button
        HBox buttonBox = new HBox(10); // spacing between buttons
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT); // align buttons to the left
        Button btnCancel = new Button("Cancel");
        Button btnGenerate = new Button("Generate");
        buttonBox.getChildren().addAll(btnCancel,btnGenerate);
        grid.add(buttonBox, 0, 12, 2, 1); //i2=going right i3=going down
   
        
        final Text actionTarget = new Text();
        grid.add(actionTarget, 0, 13, 2, 1);
        GridPane.setHalignment(actionTarget, HPos.RIGHT);
        grid.setPrefWidth(600);
        
        //actions
        
         
        //value box which shows bill information
        VBox infoBox = new VBox(10);
        Label viewBill = new Label("Billing Information");
        viewBill.setFont(Font.font("Arial", FontWeight.BOLD,15));
       
        
        TextArea billArea = new TextArea();
        billArea.setPrefSize(500, 300);
        infoBox.getChildren().addAll(viewBill,billArea);
        
        //generate bill is cancel
        btnCancel.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                actionTarget.setFill(Color.DARKRED);
                patient.getSelectionModel().clearSelection();
                doctor.getSelectionModel().clearSelection();
                payMethod.getSelectionModel().clearSelection();
                billBox.clear();
                amountBox.clear();
                date.setValue(null);
                actionTarget.setText("Bill generator is cancelled");
            }
        });

        //once generate is clicked, values will show
        btnGenerate.setOnAction(new EventHandler<ActionEvent>()
{
            @Override
            public void handle(ActionEvent actionEvent)
            {
                String selectedPatientName = patient.getValue();
                String selectedDoctorName = doctor.getValue();
                String billID = billBox.getText();
                String selectedPayMethod = payMethod.getValue();
                LocalDate selectedDate = date.getValue();
                

                // read amount
                double amount = 0.0;
                try {
                    amount = Double.parseDouble(amountBox.getText());
                } catch (NumberFormatException e) {
                    actionTarget.setFill(Color.RED);
                    actionTarget.setText("Invalid amount entered.");
                    return;
                }
                
                
                if(selectedPatientName == null || selectedDoctorName == null || billID.isEmpty() || amount.isEmpty() || selectedDate == null) {
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
                
                

                // set success message
                actionTarget.setFill(Color.GREEN);
                actionTarget.setText("Bill generated successfully");

                // display in text area
                String msg = "\n=== Billing Information ===\n" +
                        "Patient: " + selectedPatient.getName() + "\n" + 
                        "Doctor: " + selectedDoctor.getName() + "\n" +
                        "Bill ID: " + billID + "\n" +
                        "Amount: RM" + amount + "\n" + "Payment Method: " + selectedPayMethod + "\n" +
                        "Bill generated on: " + formattedDate + "\n";
                            
                 
                   
               
                billArea.setText(msg);
                
                
                
                // write to file
                String fileLine = selectedPatient.getName() + "\n"
                                + selectedDoctor.getName() + "\n" + 
                   "Bill ID: " +  billID + "\n" + "Amount: " + amount  +"\n" + "Payment Method: " + selectedPayMethod + "\n";
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
                app.setScene(createApp.getView(app, new Admin()));
            }
        });
        
        //medHistoryBtn action
        medHistoryBtn.setOnAction(new EventHandler<ActionEvent>() {
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
        
        HBox form = new HBox(30);
        form.getChildren().addAll(grid);
        form.setAlignment(Pos.TOP_CENTER);

        HBox info = new HBox(30);
        info.getChildren().add(infoBox);
        form.setAlignment(Pos.TOP_RIGHT);
        
        HBox contentLayout = new HBox(30);
        contentLayout.setPadding(new Insets(20));
        contentLayout.getChildren().addAll(sideBar, grid,infoBox);
        contentLayout.setAlignment(Pos.CENTER_LEFT);
        
        
        
        VBox fullLayout = new VBox(10);
        fullLayout.getChildren().addAll(banner, contentLayout);
        fullLayout.setPadding(new Insets(10));
        
        return fullLayout;
        
    }
    
}
    



    



