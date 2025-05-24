package ClinicManagement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MainAppClinic extends Application {

    private Stage primaryStage;
    private Scene scene;
    private final RegisterPatient registerPatient = new RegisterPatient();
    private final RegisterDoctor registerDoctor = new RegisterDoctor();
    private final CreateAppointment createAppointment = new CreateAppointment();
    private final AddMedicalHistory addMedicalHistory = new AddMedicalHistory();
    private final GenerateBill generateBill = new GenerateBill();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Parent root = getFirstPanelView();
        scene = new Scene(root, 800, 700);
        primaryStage.setTitle("@ClinicMesra");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void setScene(Parent view) {
        scene.setRoot(view);
    }
    public Parent getFirstPanelView() {
        return registerPatient.getView(this);
    }
    public Parent getSecondPanelView() {
        return registerDoctor.getView(this);
    }
    public Parent getThirdPanelView() {
        return createAppointment.getView(this);
    }
    public Parent getFourthPanelView() {
        return addMedicalHistory.getView(this);
    }
    public Parent getFifthPanelView() {
        return generateBill.getView(this);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
