package ClinicManagement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainAppClinic extends Application {

    private Stage primaryStage;
    private Scene scene;
    private final RegisterPatient registerPatient = new RegisterPatient();
    private final RegisterDoctorPanel registerDoctor = new RegisterDoctorPanel();
    private final CreateAppointment createAppointment = new CreateAppointment();
    private final AddMedicalHistory addMedicalHistory = new AddMedicalHistory();
    private final GenerateBill generateBill = new GenerateBill();

    private final Admin admin = new Admin();
    @Override
    public void start(Stage primaryStage) {

        admin.addPatient(new Patient("Aisyah", "0123232", "P001", "Female", 23));
        admin.addDoctor(new Doctor("Dr. Ali", "D001", "0123232", "Cardiology", 44.5 ));
        this.primaryStage = primaryStage;
        Parent root = mainInterface();
        scene = new Scene(root, 800, 700);
        primaryStage.setTitle("@ClinicMesra");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent mainInterface(){
        Label title = new Label("Clinic Management System");
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        title.setTextFill(Color.WHITE);
        title.setBackground(new Background(new BackgroundFill(Color.LIGHTSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        title.setPadding(new Insets(10));

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().add(title);

        /*
        Button btnPatient = new Button("Register Patient");
        Button btnDoctor = new Button("Register Doctor");
         */
        Button btnAppointment = new Button("Create Appointment");
        //Button btnMedicalHistory = new Button("Add Medical History");
        //Button btnBill = new Button("Generate Bill");

        //btnPatient.setPrefWidth(200);
        //btnDoctor.setPrefWidth(200);
        btnAppointment.setPrefWidth(200);
        //btnMedicalHistory.setPrefWidth(200);
       //btnBill.setPrefWidth(200);

        /*
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
*/
        btnAppointment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScene(createAppointment.getView(MainAppClinic.this, admin));
            }
        });
/*
        btnMedicalHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScene(addMedicalHistory.getView(MainAppClinic.this));
            }
        });

        btnBill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScene(generateBill.getView(MainAppClinic.this));
            }
        });

 */

        layout.getChildren().addAll(
                //btnPatient, btnDoctor,
                btnAppointment
                //, btnMedicalHistory, btnBill
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
