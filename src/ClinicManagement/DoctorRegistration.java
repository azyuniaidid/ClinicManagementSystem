package ClinicManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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


public class DoctorRegistration {

    public Parent getView(MainAppClinic app){

        //Clinic banner
        Image image = new Image("file:/Users/aisyahhafizar/Downloads/2.png"); //make sure file:/ is there
        ImageView bannerDoctor = new ImageView(image);
        bannerDoctor.setFitHeight(200);
        bannerDoctor.setFitWidth(1400);

        HBox banner = new HBox();
        banner.setAlignment(Pos.CENTER);
        banner.setPadding(new Insets(30));
        banner.setMaxWidth(Double.MAX_VALUE); // Let it expand fully

        banner.getChildren().add(bannerDoctor);

        //grid banner layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setPrefWidth(650);

        //sidebar buttons & actions
        Button regPatientBtn = new Button("Patient Registration");
        Button createApptBtn = new Button("Appointment Manager");
        Button medHistoryBtn = new Button("Medical History Manager");
        Button genBillBtn = new Button("Bill Generator");
        Button mainPageBtn = new Button("Main Page");


        VBox sideBar = new VBox(10);
        sideBar.setPrefWidth(180);
        sideBar.setPadding(new Insets(10));
        sideBar.setAlignment(Pos.TOP_LEFT);

        regPatientBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        createApptBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        medHistoryBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        genBillBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        mainPageBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");

        //regPatientBtn action
        regPatientBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PatientRegistration registerPatient = new PatientRegistration();
                app.setScene(registerPatient.getView(app));
            }
        });

        //createApptBtn action
        createApptBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AppointmentManager createApp = new AppointmentManager();
                app.setScene(createApp.getView(app));
            }
        });

        //medHistoryBtn action
        medHistoryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MedicalHistoryManager addMedicHistory = new MedicalHistoryManager();
                app.setScene(addMedicHistory.getView(app));
            }
        });

        //genBillBtn action
        genBillBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BillGenerator genBill = new BillGenerator();
                app.setScene(genBill.getView(app));
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

        sideBar.getChildren().addAll(regPatientBtn, createApptBtn, medHistoryBtn, genBillBtn, mainPageBtn);

        //form to register doctor
        //doctor id
        Label docIdlbl = new Label("Doctor ID:");
        docIdlbl.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docIdlbl, 0, 0);

        TextField docIdField = new TextField();
        docIdField.setPromptText("Enter Doctor ID");
        docIdField.setPrefWidth(100);
        grid.add(docIdField,0,1);

        //doctor name
        Label docNamelbl = new Label("Doctor Name:");
        docNamelbl.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(docNamelbl, 0, 2);

        TextField docNameField = new TextField();
        docNameField.setPromptText("Enter Doctor Name");
        docNameField.setPrefWidth(500);
        grid.add(docNameField,0,3);

        //doctor phone number
        Label phoneLbl = new Label("Phone Number:");
        phoneLbl.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(phoneLbl, 0, 4);

        TextField phoneField = new TextField();
        phoneField.setPrefWidth(400);
        phoneField.setPromptText("Enter Phone Number");
        grid.add(phoneField,0,5);

        //doctor specialization
        Label specializationLbl = new Label("Specialization :");
        specializationLbl.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(specializationLbl, 0, 6);

        ComboBox<String> specializationBox = new ComboBox<>();
        specializationBox.getItems().addAll("General", "Pediatric", "Cardiologist", "Orthopedic");
        specializationBox.setPromptText("Select Specialization");
        specializationBox.setPrefWidth(300);
        grid.add(specializationBox, 0, 7);

        //doctor charge per appointment
        Label chgperapptLbl = new Label("Charge per appointment:");
        chgperapptLbl.setFont(Font.font("Arial", FontWeight.BOLD,15));
        grid.add(chgperapptLbl, 0, 8);

        TextField chgperapptField = new TextField();
        chgperapptField.setMaxWidth(100);
        chgperapptField.setPromptText("RM");
        grid.add(chgperapptField,0,9);

        //regBtn & cancel button
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);

        Button regBtn = new Button("Register");
        Button cancelBtn = new Button("Cancel");
        buttonBox.getChildren().addAll(cancelBtn, regBtn);

        grid.add(buttonBox, 0, 10, 2, 1);

        //value box which shows all patient information after register
        VBox infoBox = new VBox(10);
        Label view = new Label("Doctor Information");
        view.setFont(Font.font("Arial", FontWeight.BOLD,15));

        TextArea area = new TextArea();
        area.setPrefSize(500, 300);
        infoBox.getChildren().addAll(view,area);

        //action for regBtn and cancelBtn
        final Text actionTarget = new Text();
        grid.add(actionTarget, 0, 11, 2, 1);
        GridPane.setHalignment(actionTarget, HPos.RIGHT);
        grid.setPrefWidth(600);

        regBtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e){
                System.out.println("Successfully Registered!");

                //patient is registered
                actionTarget.setFill(Color.GREEN);
                actionTarget.setText("Doctor is Registered");

                String doctorID = docIdField.getText();
                String name = docNameField.getText();
                String phoneNumber = phoneField.getText();
                String specialization = specializationBox.getValue().toString();
                double chgperappt = Double.parseDouble(chgperapptField.getText());

                String msg = "Doctor ID :  " + doctorID + "\n"
                        +"Doctor Name : " + name + "\n"
                        +"Phone Number : " + phoneNumber + "\n"
                        +"Specialization : " + specialization + "\n"
                        +"Charge per Appointment : " + chgperappt;

                area.setText(msg);

                String fileLine = doctorID + "\n" + name + "\n" + phoneNumber + "\n" + specialization + "\n" + chgperappt;

                //to text file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("doctors.txt", true))){
                    writer.write(fileLine);
                    writer.newLine();
                }catch(IOException ex){
                    System.out.println("Error writing to file: " + ex.getMessage());
                }
                Doctor doctor = new Doctor(doctorID, name, phoneNumber, specialization, chgperappt);
                System.out.println("Doctor instance has been created");
                doctor.addDoctor(doctor);
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                actionTarget.setFill(Color.DARKRED);
                docIdField.clear();
                docNameField.clear();
                phoneField.clear();
                specializationBox.getSelectionModel().clearSelection();
                chgperapptField.clear();
                area.clear();
                actionTarget.setText("Registration is Cancelled");
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
        contentLayout.getChildren().addAll(sideBar, form, info);
        contentLayout.setAlignment(Pos.CENTER_LEFT);

        VBox fullLayout = new VBox(10);
        fullLayout.getChildren().addAll(bannerDoctor, contentLayout);
        fullLayout.setPadding(new Insets(10));

        return fullLayout;

    }

}
