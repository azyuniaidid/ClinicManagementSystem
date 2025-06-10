package ClinicManagement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainAppClinic extends Application {

    private Stage primaryStage;
    private Scene scene;
    private final PatientRegistration registerPatient = new PatientRegistration();
    private final DoctorRegistration registerDoctor = new DoctorRegistration();
    private final AppointmentManager createAppointment = new AppointmentManager();
    private final MedicalHistoryManager addMedicalHistory = new MedicalHistoryManager();
    private final BillGenerator generateBill = new BillGenerator();

    private final Admin admin = new Admin();
    @Override
    public void start(Stage primaryStage) {

        admin.addPatient(new Patient("Ali", "0111113456", "P001", "Male", 21));
        admin.addDoctor(new Doctor("Abu", "D001", "0122222345", "Ortho", 32.9));
        this.primaryStage = primaryStage;
        Parent root = mainInterface();
        scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Clinic Appointment and Management System");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); //makes it big right away
        primaryStage.show();
    }

    private Parent mainInterface(){
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        // Clinic banner
        Image image = new Image("file:/Users/aisyahhafizar/Downloads/1.png");
        ImageView banner = new ImageView(image);
        banner.setFitHeight(220);
        banner.setFitWidth(1340);

        layout.getChildren().add(banner);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        Button btnPatient = new Button("Patient Registration");
        Button btnDoctor = new Button("Doctor Registration");
        Button btnAppointment = new Button("Appointment Manager");
        Button btnMedicalHistory = new Button("Medical History Manager");
        Button btnBill = new Button("Bill Generator");

        btnPatient.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");;
        btnDoctor.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        btnAppointment.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        btnMedicalHistory.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");
        btnBill.setStyle("-fx-font-size: 14px; -fx-min-width: 200px; -fx-min-height: 50px");


        btnPatient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScene(registerPatient.getView(MainAppClinic.this));
            }
        });

        btnDoctor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScene(registerDoctor.getView(MainAppClinic.this));
            }
        });

        btnAppointment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScene(createAppointment.getView(MainAppClinic.this, admin));
            }
        });

        btnMedicalHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScene(addMedicalHistory.getView(MainAppClinic.this, admin));
            }
        });

        btnBill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScene(generateBill.getView(MainAppClinic.this, new Admin()));
            }
        });


        layout.getChildren().addAll(
                btnPatient, btnDoctor, btnAppointment, btnMedicalHistory, btnBill
        );

        return layout;
    }

    public void setScene(Parent view) {
        scene.setRoot(view);
    }

    public Parent getDashboard(){
        return mainInterface();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
