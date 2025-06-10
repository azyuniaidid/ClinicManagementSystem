package ClinicManagement;

import javafx.geometry.HPos;
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

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class AppointmentManager
{
    public Parent getView(MainAppClinic app, Admin admin)
    {

        // Clinic banner
        Image image = new Image("file:/Users/aisyahhafizar/Downloads/4.png");
        ImageView banner = new ImageView(image);
        banner.setFitHeight(200);
        banner.setFitWidth(1400);

        //add banner
        HBox banners = new HBox();
        banners.setAlignment(Pos.CENTER);
        banners.setPadding(new Insets(30));
        banners.setMaxWidth(Double.MAX_VALUE); // Let it expand fully

        // Main form grid
        /*GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(30));*/

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setPrefWidth(650);

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
        IDTextField.setPrefWidth(500);

        ComboBox<String> cbPatient = new ComboBox<>();
        cbPatient.setPromptText("Select Patient");
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

                cbPatient.getItems().add(id + " - " + name);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("patients.txt not found.");
        }

        ComboBox<String> cbDoctor = new ComboBox<>();
        cbDoctor.setPromptText("Select Doctor");

        try (Scanner scanner = new Scanner(new File("doctors.txt")))
        {
            while (scanner.hasNextLine())
            {
                String id = scanner.nextLine().trim();         // D001
                if (!scanner.hasNextLine()) break;
                String name = scanner.nextLine().trim();       // Dr. Salina
                if (!scanner.hasNextLine()) break;
                String phone = scanner.nextLine().trim();      // 0159678324
                if (!scanner.hasNextLine()) break;
                String specialization = scanner.nextLine().trim(); // General
                if (!scanner.hasNextLine()) break;
                String rate = scanner.nextLine().trim();       // 97.0

                cbDoctor.getItems().add(id + " - " + name);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("doctors.txt not found.");
        }

        DatePicker dates = new DatePicker();
        dates.setPrefWidth(500);

        // Add to grid
        grid.add(appointmentID, 0, 0);
        grid.add(IDTextField, 0, 1);

        grid.add(patientName, 0, 2);
        grid.add(cbPatient, 0, 3);

        grid.add(doctorName, 0, 4);
        grid.add(cbDoctor, 0, 5);

        grid.add(date, 0, 6);
        grid.add(dates, 0, 7);

        // Buttons
        /*Button btnCreate = new Button("Create");
        Button btnCancel = new Button("Cancel");
        btnCreate.setStyle("-fx-font-size: 16px;");
        btnCancel.setStyle("-fx-font-size: 16px;");*/

        HBox buttonBox = new HBox(10); // spacing between buttons
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT); // align buttons to the left
        Button btnCreate = new Button("Register");
        Button btnCancel = new Button("Cancel");
        buttonBox.getChildren().addAll(btnCancel,btnCreate);
        grid.add(buttonBox, 0, 10, 2, 1); //i2=going right i3=going down

        /*HBox btnBox = new HBox(15);
        btnBox.setAlignment(Pos.CENTER_RIGHT);
        btnBox.setPadding(new Insets(10, 0, 0, 0));
        btnBox.getChildren().addAll(btnCancel, btnCreate);
        grid.add(btnBox, 1, 4);*/

        // Action text
        /*final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 5);
        GridPane.setMargin(actiontarget, new Insets(5, 0, 0, 0));*/

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 11, 2, 1);
        GridPane.setHalignment(actiontarget, HPos.RIGHT);
        grid.setPrefWidth(600);

        // Right-side information box
        VBox infoBox = new VBox(10);
        //infoBox.setAlignment(Pos.TOP_LEFT);
        Label view = new Label("Appointment Information");
        view.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(view, 0, 12);


        TextArea outputArea = new TextArea();
        outputArea.setPrefSize(500, 300);
        //outputArea.setEditable(false);
        infoBox.getChildren().addAll(view, outputArea);

        // Button actions
        btnCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String id = IDTextField.getText().trim();
                String selectedPatientName = cbPatient.getValue();
                String selectedDoctorName = cbDoctor.getValue();
                LocalDate localDate = dates.getValue();

                if (id.isEmpty() || selectedPatientName == null || selectedDoctorName == null || localDate == null) {
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText("Please fill all fields");
                    return;
                }

                Date appointmentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
/*
                Patient selectedPatient = null;
                for (Patient p : admin.getAllPatients()) {
                    if (p.getPatientID().equals(patientID)) {
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

                for (Doctor d : admin.getAllDoctors()) {
                    if (d.getDoctorID().equals(doctorID)) {
                        selectedDoctor = d;
                        break;
                    }
                }
*/
                /*
                Appointment appointment = new Appointment(id, appointmentDate, "Scheduled", selectedPatientName, selectedDoctorName);
                admin.addAppointment(appointment);
                admin.dataToTextFiles();

                 */

                actiontarget.setFill(Color.LIGHTGREEN);
                actiontarget.setText("Appointment saved successfully!");

                String details = "📅 Appointment Details\n"
                        + "------------------------\n"
                        + "Appointment ID : " + id + "\n"
                        + "Patient Name   : " + selectedPatientName+ "\n"
                        + "Doctor Name    : " + selectedDoctorName+ "\n"
                        + "Date           : " + localDate + "\n"
                        + "Status         : Scheduled";

                outputArea.setText(details);

                String fileLine = id + "\n" + selectedPatientName + "\n" + selectedDoctorName + "\n" + localDate + "\n" + "Scheduled" + "";

                // to text file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt", true)))
                {
                    writer.write(fileLine);
                    writer.newLine();
                }
                catch (IOException e)
                {
                    System.out.println("Error writing to file: " + e.getMessage());
                }

                System.out.println("Appointments instance has been created");

                IDTextField.clear();
                cbPatient.setValue(null);
                cbDoctor.setValue(null);
                dates.setValue(null);
            }
        });

        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                IDTextField.clear();
                cbPatient.setValue(null);
                cbDoctor.setValue(null);
                dates.setValue(null);
                outputArea.clear();
                actiontarget.setFill(Color.DARKRED);
                actiontarget.setText("Appointment creation cancelled.");
            }
        });

        // Side navigation buttons
        /*VBox sideBtns = new VBox(15);
        sideBtns.setPadding(new Insets(20));
        sideBtns.setAlignment(Pos.TOP_CENTER);*/

        VBox sideBtns = new VBox(10);
        sideBtns.setPrefWidth(180);
        sideBtns.setPadding(new Insets(10));
        sideBtns.setAlignment(Pos.TOP_LEFT);

        String btnStyle = "-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px";
        Button btnRegPatient = new Button("Patient Registration");
        Button btnRegDoctor = new Button("Doctor Registration");
        Button btnAddMed = new Button("Medical History Manager");
        Button btnGenBill = new Button("Bill Generator");
        Button btnHome = new Button("Main Page");

        btnRegPatient.setStyle(btnStyle);
        btnRegDoctor.setStyle(btnStyle);
        btnAddMed.setStyle(btnStyle);
        btnGenBill.setStyle(btnStyle);
        btnHome.setStyle(btnStyle);

        sideBtns.getChildren().addAll(btnRegPatient, btnRegDoctor, btnAddMed, btnGenBill, btnHome);

        // Button navigation logic
        btnRegPatient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PatientRegistration registerPatient = new PatientRegistration();
                app.setScene(registerPatient.getView(app));
            }
        });

        btnRegDoctor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                DoctorRegistration doctorPanel = new DoctorRegistration();
                app.setScene(doctorPanel.getView(app));
            }
        });

        btnAddMed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MedicalHistoryManager addMedicHistory = new MedicalHistoryManager();
                app.setScene(addMedicHistory.getView(app, new Admin()));
            }
        });

        btnGenBill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BillGenerator genBill = new BillGenerator();
                app.setScene(genBill.getView(app,new Admin()));
            }
        });

        btnHome.setOnAction(new EventHandler<ActionEvent>() {
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


        /*HBox contentLayout = new HBox(30);
        contentLayout.setPadding(new Insets(30));
        contentLayout.getChildren().addAll(sideBtns, grid, infoBox);*/

        HBox contentLayout = new HBox(30);
        contentLayout.setPadding(new Insets(20));
        contentLayout.getChildren().addAll(sideBtns, grid,infoBox);
        contentLayout.setAlignment(Pos.CENTER_LEFT);

        VBox fullLayout = new VBox(10);
        fullLayout.getChildren().addAll(banner, contentLayout);
        fullLayout.setPadding(new Insets(10));

        return fullLayout;
    }
}
