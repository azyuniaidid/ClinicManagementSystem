package ClinicManagement;

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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

// ... (same import and package statements)

public class AppointmentManager {

    public Parent getView(MainAppClinic app, Admin admin) {

        // Banner section
        Image image = new Image("file:C:/Users/User/Downloads/ApptManagerBan.png");
        ImageView bannerAppt = new ImageView(image);
        bannerAppt.setFitHeight(200);
        bannerAppt.setFitWidth(1340);

        HBox banner = new HBox(bannerAppt);
        banner.setAlignment(Pos.CENTER);
        banner.setPadding(new Insets(30));

        // Appointment input form
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.TOP_LEFT);
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(20));

        Label lblApptID = new Label("Appointment ID : ");
        Label lblPatient = new Label("Patient Name : ");
        Label lblDoctor = new Label("Doctor Name : ");
        Label lblDate = new Label("Date : ");

        lblApptID.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblPatient.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblDoctor.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblDate.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        TextField txtApptID = new TextField();
        txtApptID.setPromptText("Enter Appointment ID");
        txtApptID.setPrefWidth(250);

        ComboBox<String> cbPatient = new ComboBox<>();
        cbPatient.setPromptText("Select Patient");
        for (Patient p : admin.getAllPatients()) {
            cbPatient.getItems().add(p.getName());
        }

        ComboBox<String> cbDoctor = new ComboBox<>();
        cbDoctor.setPromptText("Select Doctor");
        for (Doctor d : admin.getAllDoctors()) {
            cbDoctor.getItems().add(d.getName());
        }

        DatePicker dpDate = new DatePicker();

        formGrid.add(lblApptID, 0, 0);
        formGrid.add(txtApptID, 1, 0);
        formGrid.add(lblPatient, 0, 1);
        formGrid.add(cbPatient, 1, 1);
        formGrid.add(lblDoctor, 0, 2);
        formGrid.add(cbDoctor, 1, 2);
        formGrid.add(lblDate, 0, 3);
        formGrid.add(dpDate, 1, 3);

        Button btnCreate = new Button("Create");
        Button btnCancel = new Button("Cancel");

        btnCreate.setStyle("-fx-font-size: 16px;");
        btnCancel.setStyle("-fx-font-size: 16px;");

        HBox buttonBox = new HBox(20, btnCancel, btnCreate);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        formGrid.add(buttonBox, 1, 4);

        final Text actionMessage = new Text();
        formGrid.add(actionMessage, 1, 5);
        GridPane.setMargin(actionMessage, new Insets(10, 0, 0, 0));

        // Output Section
        VBox infoBox = new VBox(15);
        infoBox.setAlignment(Pos.TOP_LEFT);
        infoBox.setPadding(new Insets(10));

        Label lblOutput = new Label("Appointment Information");
        lblOutput.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        TextArea txtOutput = new TextArea();
        txtOutput.setEditable(false);
        txtOutput.setPrefSize(400, 400);

        infoBox.getChildren().addAll(lblOutput, txtOutput);

        // Sidebar Menu
        VBox sideMenu = new VBox(15);
        sideMenu.setPadding(new Insets(20, 10, 20, 20));
        sideMenu.setPrefWidth(200);

        Button btnRegPatient = new Button("Patient Registration");
        Button btnRegDoctor = new Button("Doctor Registration");
        Button btnAddMed = new Button("Medical History Manager");
        Button btnGenBill = new Button("Bill Generator");
        Button btnHome = new Button("Main Page");

        String btnStyle = "-fx-font-size: 14px; -fx-min-width: 180px; -fx-min-height: 45px";
        btnRegPatient.setStyle(btnStyle);
        btnRegDoctor.setStyle(btnStyle);
        btnAddMed.setStyle(btnStyle);
        btnGenBill.setStyle(btnStyle);
        btnHome.setStyle(btnStyle);

        sideMenu.getChildren().addAll(btnRegPatient, btnRegDoctor, btnAddMed, btnGenBill, btnHome);

        // Button navigation
        btnRegPatient.setOnAction(e -> app.setScene(new PatientRegistration().getView(app)));
        btnRegDoctor.setOnAction(e -> app.setScene(new DoctorRegistration().getView(app)));
        btnAddMed.setOnAction(e -> app.setScene(new MedicalHistoryManager().getView(app, admin)));
        btnGenBill.setOnAction(e -> app.setScene(new BillGenerator().getView(app, admin)));
        btnHome.setOnAction(e -> app.setScene(app.getDashboard()));

        // Create button logic
        btnCreate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                String id = txtApptID.getText().trim();
                String patName = cbPatient.getValue();
                String docName = cbDoctor.getValue();
                LocalDate selectedDate = dpDate.getValue();

                if (id.isEmpty() || patName == null || docName == null || selectedDate == null) {
                    actionMessage.setFill(Color.RED);
                    actionMessage.setText("Please fill all fields");
                    return;
                }

                Date apptDate = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                Patient selectedPatient = admin.getAllPatients().stream()
                        .filter(p -> p.getName().equals(patName))
                        .findFirst().orElse(null);

                Doctor selectedDoctor = admin.getAllDoctors().stream()
                        .filter(d -> d.getName().equals(docName))
                        .findFirst().orElse(null);

                if (selectedPatient == null || selectedDoctor == null) {
                    actionMessage.setFill(Color.RED);
                    actionMessage.setText("Selected patient or doctor not found");
                    return;
                }

                Appointment appointment = new Appointment(id, apptDate, "Scheduled", selectedPatient, selectedDoctor);
                admin.addAppointment(appointment);
                admin.dataToTextFiles();

                actionMessage.setFill(Color.GREEN);
                actionMessage.setText("Appointment saved successfully!");

                txtOutput.setText("📅 Appointment Details\n"
                        + "------------------------\n"
                        + "Appointment ID : " + id + "\n"
                        + "Patient Name   : " + patName + "\n"
                        + "Doctor Name    : " + docName + "\n"
                        + "Date           : " + selectedDate + "\n"
                        + "Status         : Scheduled");

                txtApptID.clear();
                cbPatient.setValue(null);
                cbDoctor.setValue(null);
                dpDate.setValue(null);
            }
        });

        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                txtApptID.clear();
                cbPatient.setValue(null);
                cbDoctor.setValue(null);
                dpDate.setValue(null);
                txtOutput.clear();
                actionMessage.setFill(Color.DARKRED);
                actionMessage.setText("Appointment creation cancelled.");
            }
        });

        // Main layout
        HBox content = new HBox(30, sideMenu, formGrid, infoBox);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_CENTER);

        VBox root = new VBox(10, banner, content);
        root.setPadding(new Insets(10));

        return root;
    }
}
