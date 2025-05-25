package clinicmainpanel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class RegisterDoctorPanel {
    
    public Parent getView (MainAppClinic app){
        
        //Clinic banner
        Image image = new Image("file:C:\\Users\\User\\OneDrive\\Pictures\\Screenshots\\BannerClinic.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);
        imageView.setPreserveRatio(true);
        
        //sidebar menu 
        VBox sideBar = new VBox(10);
        sideBar.setPrefWidth(200);
        
        Button regPatientBtn = new Button("Register Patient");
        Button createApptBtn = new Button("Create Appointment");
        Button medHistoryBtn = new Button("Medical History");
        Button genBillBtn = new Button("Generate Bill");
        Button mainPageBtn = new Button("Main Page");
        
        regPatientBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        createApptBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        medHistoryBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        genBillBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        mainPageBtn.setStyle("-fx-font-size: 14px; -fx-min-width: 150px;");
        
        sideBar.getChildren().addAll(regPatientBtn, createApptBtn, medHistoryBtn, genBillBtn, mainPageBtn);
        
        //form to register doctor
        VBox form = new VBox(15);
        
        Label docIdlbl = new Label("Doctor ID:");
        TextField docIdField = new TextField();
        docIdField.setMaxWidth(450);
        docIdField.setPromptText("Enter Doctor ID");
        
        Label docNamelbl = new Label("Doctor Name:");
        TextField docNameField = new TextField();
        docNameField.setMaxWidth(450);
        docNameField.setPromptText("Enter Doctor Name");
        
        Label phoneLbl = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneField.setMaxWidth(300);
        phoneField.setPromptText("Enter Phone Number");
        
        Label specializationLbl = new Label("Specialization :");
        ComboBox specializationBox = new ComboBox();
        specializationBox.getItems().addAll("General", "Pediatric", "Cardiologist", "Orthopedic");
        specializationBox.setPromptText("Select Specialization");
        
        Label chgperapptLbl = new Label("Charge per appointment:");
        TextField chgperapptField = new TextField();
        chgperapptField.setMaxWidth(100);
        chgperapptField.setPromptText("RM");
        
        Button regBtn = new Button("REGISTER");
        regBtn.setAlignment(Pos.CENTER_RIGHT);
        
        //actions 
        
        //regPatientBtn action
        regPatientBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RegisterPatient registerPatient = new RegisterPatient();
                app.setScene(registerPatient.getView(app));
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
        
        //genBillBtn action
        medHistoryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GenerateBill genBill = new GenerateBill();
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
        
        //regBtn action
        final Text actionTarget = new Text();
        regBtn.setOnAction(new EventHandler<ActionEvent>(){
           
            @Override
            public void handle(ActionEvent e){
                String doctorID = docIdField.getText();
                String name = docNameField.getText();
                String phoneNumber = phoneField.getText();
                String specialization = specializationBox.getValue().toString();
                double chgperappt = Double.parseDouble(chgperapptField.getText());
                
                Doctor doctor = new Doctor(doctorID, name, phoneNumber, specialization, chgperappt);
                doctor.addDoctor(doctor);
                
                actionTarget.setText("Doctor has been registered");
            }
        });
        
        
        form.getChildren().addAll(docIdlbl, docIdField, docNamelbl, docNameField, phoneLbl, phoneField,
                specializationLbl, specializationBox, chgperapptLbl, chgperapptField, regBtn, actionTarget);
        
        BorderPane layout = new BorderPane();
        layout.setTop(imageView);
        layout.setLeft(sideBar);
        layout.setCenter(form);
        return layout;
        
    }
    
}
